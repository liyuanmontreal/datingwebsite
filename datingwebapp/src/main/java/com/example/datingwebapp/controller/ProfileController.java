package com.example.datingwebapp.controller;

import com.example.datingwebapp.entity.Client;
import com.example.datingwebapp.entity.Photo;
import com.example.datingwebapp.entity.Profile;

import com.example.datingwebapp.repository.ClientRepository;
import com.example.datingwebapp.repository.ProfileRepository;
import com.example.datingwebapp.service.ClientService;
import com.example.datingwebapp.service.PhotoService;
import com.example.datingwebapp.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;


@Controller
@AllArgsConstructor
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/error")
    public String error() {
        return "error";
    }


    //fill out wizard form
    @GetMapping("/wizard")
    @PreAuthorize(value = "hasRole('Client')")
    public ModelAndView getWizard() {
        ModelAndView mav = new ModelAndView("wizard");
        Profile profile = new Profile();
        mav.addObject("profile", profile);
        return mav;
    }

    @PostMapping("/saved")
    @PreAuthorize(value = "hasRole('Client')")
    public String addProfile(@Valid Profile profile, BindingResult result, Principal principal) {
        return profileService.saveProfile(profile, result, principal);
    }

    //save successfully
    @GetMapping("/saved")
    @PreAuthorize(value = "hasRole('Client')")
    public String saveSuccess(@RequestParam Long id, Model model) {
          Profile p =  profileRepository.getById(id);
          Photo image = new Photo();
        image.setProfile(p);
          model.addAttribute("photo" , image);
        return "saved";
    }

//    //edit profile, display image
    @GetMapping("/edit_profile/{id}")
    @PreAuthorize(value = "hasRole('Client')")
    public ModelAndView editProfileForm(@PathVariable(value="id") Long id){
        ModelAndView mav = new ModelAndView("edit_profile");
        Profile profile = profileRepository.findProfileById(id).get();
        mav.addObject("profile", profile);
//        Photo image = photoService.getPhotoByProfileId(id).get();
//        mav.addObject("image",image);
        return mav;
    }

    //post edit profile
    @PostMapping("/saveEditProfile")
    @PreAuthorize(value = "hasRole('Client')")
    public String saveEditProfile(@Valid @ModelAttribute("profile") Profile profile, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "edit_profile";
        }else {

            profileRepository.save(profile);
            return "redirect:/home";
        }
    }

    //view profile
//    @GetMapping("/viewMyrofile")
//    @PreAuthorize(value = "hasRole('Client')")
//    public ModelAndView viewMyProfile(){
//
//
//    }

    //client profile part

    //show all man profiles
    @GetMapping("/man")
    public ModelAndView  showManProfiles() {
        return profileService.getManProfiles();
    }

    //show all woman profiles
    @GetMapping("/woman")
    public ModelAndView  showWomanProfiles() {
        return profileService.getWomanProfiles();
    }

    //show other one's profile
    @GetMapping("/profile")
    //@PreAuthorize(value = "hasRole('ADMIN')")
    public ModelAndView showProfile(@RequestParam Long id) {
        return profileService.getProfileById(id);
    }

}


