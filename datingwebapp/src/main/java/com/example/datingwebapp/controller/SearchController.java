package com.example.datingwebapp.controller;

import com.example.datingwebapp.FormSubmission.SearchFormSubmission;
import com.example.datingwebapp.entity.Profile;
import com.example.datingwebapp.repository.ProfileRepository;
import com.example.datingwebapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SearchController {

    @Autowired
    private ProfileRepository pRepo;


    @GetMapping("/search")
    public String searchForm(Model model){
        model.addAttribute("searchFormSubmission", new SearchFormSubmission());
        return "search";
    }

    @PostMapping("/search")
    public String searchProfile(@ModelAttribute SearchFormSubmission searchFormSubmission, Model model) {
        model.addAttribute("search", searchFormSubmission);
        int maxAge = searchFormSubmission.getMaxAge();
        if(maxAge == 0){
            maxAge = 120;
        }

        int maxHeight = searchFormSubmission.getMaxHeight();
        if(maxHeight == 0){
            maxHeight = 250;
        }

        ArrayList<Profile.Gender> genders = new ArrayList<Profile.Gender>();
        if(searchFormSubmission.isFemale())
            genders.add(Profile.Gender.FEMALE);
        if(searchFormSubmission.isMale())
            genders.add(Profile.Gender.MALE);




        ArrayList<Profile> profilesByAge = pRepo.findAllByAgeIsBetween(searchFormSubmission.getMinAge(), maxAge);
        ArrayList<Profile> profilesBySex = pRepo.findAllByGenderIn(genders);
        ArrayList<Profile> profilesByCity = pRepo.findAllByCityEquals(searchFormSubmission.getCity());
        ArrayList<Profile> profilesByHeight = pRepo.findAllByHeightIsBetween(searchFormSubmission.getMinHeight(), maxHeight);

        ArrayList<Profile> results = new ArrayList<Profile>(profilesByAge);
        results.retainAll(profilesBySex);
        results.retainAll(profilesByCity);
        results.retainAll(profilesByHeight);

        model.addAttribute("results1", results);

        return "search";
    }

}
