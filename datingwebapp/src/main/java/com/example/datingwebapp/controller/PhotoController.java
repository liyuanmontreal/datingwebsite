package com.example.datingwebapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.datingwebapp.entity.Photo;
import com.example.datingwebapp.entity.Profile;
import com.example.datingwebapp.repository.ProfileRepository;
import com.example.datingwebapp.service.PhotoService;
import com.example.datingwebapp.service.ProfileService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PhotoController {

    @Value("${uploadDir}")
    private String uploadFolder;

    @Autowired
    private PhotoService photoService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ProfileRepository profileRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    @GetMapping("/uploadImage")
//    public ModelAndView uploadImageForm() {
//            ModelAndView mav = new ModelAndView("uploadImage");
//            Photo photo = new Photo();
//            mav.addObject("photo", photo);
//            return mav;
//        }

    @PostMapping("/showImage")
    public @ResponseBody ResponseEntity<?> createImage(@RequestParam("name") String name, Model model, HttpServletRequest request
            ,final @RequestParam("image") MultipartFile file) {
        try {
            //String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
            String profileId = request.getParameter("profile.id");
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            log.info("uploadDirectory:: " + uploadDirectory);
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            log.info("FileName: " + file.getOriginalFilename());
            if (fileName == null || fileName.contains("..")) {
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
            String[] names = name.split(",");

            Date createDate = new Date();
            log.info("Name: " + names[0]+" "+filePath);

            try {
                File dir = new File(uploadDirectory);
                if (!dir.exists()) {
                    log.info("Folder Created");
                    dir.mkdirs();
                }
                // Save the file locally
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                log.info("in catch");
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            Photo p = new Photo();
            p.setName(names[0]);
            p.setImage(imageData);
            p.setCreateDate(createDate);
//            p.setProfile(profileService.getById(Long.parseLong(profileId)));
            photoService.savePhoto(p);
            Profile profile = profileRepository.findById(Long.parseLong(profileId)).get();
            profile.setImage(p);
            profileRepository.save(profile);
            log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
            return new ResponseEntity<>("Photo Saved With File - " + fileName + "Back home", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Exception: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/showImage/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Photo> photo)
            throws ServletException, IOException {
        log.info("Id :: " + id);
        photo = photoService.getPhotoById(id);
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        response.setContentType( "image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(photo.get().getImage());
        response.getOutputStream().close();
    }



    @GetMapping("/listPhotos")
    String show(ModelMap model) {
        List<Photo> photos = photoService.getAllPhotos();
        model.addAttribute("photos", photos);
        return "listPhotos";
    }
}
