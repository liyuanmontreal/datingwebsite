 package com.example.datingwebapp.controller;

import com.example.datingwebapp.entity.Message;
import com.example.datingwebapp.repository.ClientRepository;
import com.example.datingwebapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MessageService messageService;

    //leave a new message to other user
    //todo:after leave message,client get notification
    @PostMapping({"/send-message"})
    public String sendMessage(@RequestParam Long pid,@Valid @ModelAttribute("message") Message message, BindingResult bindingResult, RedirectAttributes redirAttrs, Principal principal) {

        if (bindingResult.hasErrors()){
            redirAttrs.addFlashAttribute("error", "Error: send failed.");
            return "profile?id="+pid;
        }else
        {
            redirAttrs.addFlashAttribute("success", "Send successfully.");
            return messageService.sendMessage(pid,message, bindingResult, redirAttrs, principal);
        }
    }


    @PostMapping({"/like"})
    public String like(@RequestParam Long pid,@Valid @ModelAttribute("message") Message message, BindingResult bindingResult, RedirectAttributes redirAttrs, Principal principal) {

        if (bindingResult.hasErrors()){
            redirAttrs.addFlashAttribute("error", "Error: send failed.");
            return "profile?id="+pid;
        }else
        {
            redirAttrs.addFlashAttribute("success", "Send Like successfully.");
            return messageService.sendLike(pid,message, bindingResult, redirAttrs, principal);
        }
    }


    // show new messages at message/userid page
    @GetMapping("/notification")
    public ModelAndView getNewMessages(Principal principal){
        //public ModelAndView getNewMessages(@RequestParam Long id,Principal principal){
        return messageService.getAllNewNotification(principal);
    }

}

