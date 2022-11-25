package com.example.datingwebapp.controller;

import com.example.datingwebapp.entity.Client;
import com.example.datingwebapp.entity.Profile;
import com.example.datingwebapp.repository.ClientRepository;
import com.example.datingwebapp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/list_clients")
    public ModelAndView listUsers() {
        ModelAndView mav = new ModelAndView("list-clients");
        List<Client> listClients = clientRepository.findAll();
        mav.addObject("client", listClients);
        List<Profile> listProfiles = profileRepository.findAll();
        mav.addObject("profile",listProfiles);
        return mav;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping("/updateClient")
    public String saveUpdateClient(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "updateForm";
        }else {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(client.getPassword());
            client.setPassword(encodedPassword);
            clientRepository.save(client);
            return "redirect:/list_clients";
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long clientId){
        ModelAndView mav = new ModelAndView("updateForm");
        Client client = clientRepository.findById(clientId).get();
        mav.addObject("client", client);
        return mav;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/deleteClient")
    public String deleteEmployee(@RequestParam Long clientId) {
        clientRepository.deleteById(clientId);
        return "redirect:/list_clients";
    }
}
