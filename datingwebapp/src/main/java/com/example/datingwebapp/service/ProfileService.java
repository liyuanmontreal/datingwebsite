package com.example.datingwebapp.service;

import com.example.datingwebapp.entity.Client;
import com.example.datingwebapp.entity.Message;
import com.example.datingwebapp.entity.Photo;
import com.example.datingwebapp.entity.Profile;
import com.example.datingwebapp.repository.ClientRepository;
import com.example.datingwebapp.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService {

    @Autowired
    private final ProfileRepository profileRepository;
    @Autowired
    private final ClientRepository clientRepository;

    public ModelAndView getRecommendProfiles() {
        ModelAndView mav = new ModelAndView("home");
        //List<Profile> list = profileRepository.findAll();//findTop10ByOrderByCreatedTSDesc();
        List<Profile> list = profileRepository.findTop10ByOrderByCreatedTSDesc();
        //for (Profile profile : list) {
        //String newBody = article.getBody().substring(0, Math.min(article.getBody().length(), 100)) + "...";
        //article.setBody(newBody);
        //}
        mav.addObject("profileList", list);
        return mav;
    }


    public ModelAndView getProfileById(Long profileId) {
        ModelAndView mav = new ModelAndView("profile");
        Profile profile = profileRepository.findById(profileId).get();
        mav.addObject("profile", profile);
        Message message = new Message();
        mav.addObject("message", message);
        return mav;
    }

    public Profile getById(Long profileId) {
        return profileRepository.findById(profileId).get();
    }

//first time add profile
    @Transactional
    public String saveProfile(@Valid Profile profile, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "wizard";
        }

        Client client = clientRepository.findByEmail(principal.getName()).get();
        profile.setClient(client);

        profile.setCreatedTS(LocalDateTime.now());
        Long id = profileRepository.save(profile).getId();
        return "redirect:/saved?id=" + id.toString();

    }

    //edit profile
//    @Transactional
//    public String editProfile(@Valid Profile profile, BindingResult result, Principal principal) {
//        if (result.hasErrors()) {
//            return "edit_profile";
//        }
//
//        Client client = clientRepository.findByEmail(principal.getName()).get();
//        profile.setClient(client);
//
//        profile.setCreatedTS(LocalDateTime.now());
//        Long id = profileRepository.save(profile).getId();
//        return "redirect:/viewMyProfile?id=" + id.toString();
//
//    }




    public ModelAndView  getWomanProfiles(){
        ModelAndView mav = new ModelAndView("list-profile");
        List<Profile> list = profileRepository.findByGenderOrderByCreatedTSDesc( Profile.Gender.FEMALE);
        mav.addObject("profileList", list);
        return mav;
    }

    public ModelAndView  getManProfiles(){
        ModelAndView mav = new ModelAndView("list-profile");
        List<Profile> list = profileRepository.findByGenderOrderByCreatedTSDesc(Profile.Gender.MALE);
        mav.addObject("profileList", list);
        return mav;
    }
}


