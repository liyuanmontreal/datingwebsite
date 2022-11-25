package com.example.datingwebapp.controller;

import com.example.datingwebapp.entity.Client;
import com.example.datingwebapp.entity.Photo;
import com.example.datingwebapp.service.ClientService;
import com.example.datingwebapp.service.PhotoService;
import com.example.datingwebapp.service.ProfileService;
import com.example.datingwebapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private final ClientService clientService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private MessageService messageService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/index")
    public String viewIndexPage() {
        return "index";
    }

    //registration process
    @GetMapping("/register")
    public ModelAndView registerPage() {
        ModelAndView mav = new ModelAndView("signUpForm");
        Client newClient = new Client();
        mav.addObject("client", newClient);
        return mav;
    }


    @PostMapping("/process_register")
    public String processRegister(@Valid Client client, BindingResult result, Principal principal) {
        return clientService.register(client, result);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login_err")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @PreAuthorize(value="hasAnyRole('Client','ADMIN','VIP')")
    @GetMapping("/home")
    public ModelAndView ShowHomePage( Principal principal) {
        ModelAndView mav = profileService.getRecommendProfiles();
        Client client = clientService.findByEmail(principal.getName());
        mav.addObject("client", client);
        List<Photo> photos = photoService.getAllPhotos();
        mav.addObject("photos", photos );
        return mav;
    }
}
