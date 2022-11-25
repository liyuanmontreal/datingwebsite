package com.example.datingwebapp.service;

import com.example.datingwebapp.entity.Client;
import com.example.datingwebapp.entity.Message;
import com.example.datingwebapp.entity.Profile;
import com.example.datingwebapp.repository.ClientRepository;
import com.example.datingwebapp.repository.MessageRepository;
import com.example.datingwebapp.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageService {

    @Autowired
    private final MessageRepository messageRepository;

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final ProfileRepository profileRepository;

    // leave message to other user
    // get senderId from principal object
    // get recipientId from page
    public String sendMessage(Long id,Message message, BindingResult bindingResult, RedirectAttributes redirAttrs, Principal principal) {
        if (bindingResult.hasErrors()) {
            //redirAttrs.addFlashAttribute("error","Error:sendfailed.");
            return "profile";
        } else {

            Client sender = clientRepository.findByEmail(principal.getName()).get();
            message.setSender(sender);

            Client recipient = clientRepository.findById(id).get();
            message.setRecipient(recipient);

            message.setCreatedTS(LocalDateTime.now());
            messageRepository.save(message);

            //redirAttrs.addFlashAttribute("success","Sendsuccessfully.");
            return "redirect:/profile?id="+recipient.getId();
        }
    }


    public String sendLike(Long id,Message message, BindingResult bindingResult, RedirectAttributes redirAttrs, Principal principal) {
        if (bindingResult.hasErrors()) {
            //redirAttrs.addFlashAttribute("error","Error:sendfailed.");
            return "error";
        } else {

            Client sender = clientRepository.findByEmail(principal.getName()).get();
            message.setSender(sender);

            Client recipient = clientRepository.findById(id).get();
            message.setRecipient(recipient);

            message.setCreatedTS(LocalDateTime.now());
            message.setType("like");           
            messageRepository.save(message);

            //redirAttrs.addFlashAttribute("success","Sendsuccessfully.");
            return "redirect:/profile?id="+recipient.getId();
        }
    }



    public Long getNewMsgNumber(Principal principal) {
        Client recipient = clientRepository.findByEmail(principal.getName()).get();
        /*long msgNo = messageRepository.findNewMsgNumberUsingJPA("delieved",recipient);
        return msgNo;*/
        Message message = new Message();
        message.setRecipient(recipient);
        message.setStatus("received");
        long msgCount = messageRepository.count(Example.of(message));
        return msgCount;

    }


    public ModelAndView getAllNewNotification(Principal principal) {
        ModelAndView mav = new ModelAndView("list-notification");
        Client recipient = clientRepository.findByEmail(principal.getName()).get();

        // using jpa
        //List<Message> list = messageRepository.findAllNewMessageUsingJPA("delieved",recipient);

        //List <Message> list = messageRepository.findAllByStatusAndRecipient("delieved" ,recipient);
        List <Message> list = messageRepository.findAllByStatusAndRecipient("delieved" ,recipient);

        //change status from delieved to todelieved
        for (Message message : list) {
            message.setStatus("received");
        }
        mav.addObject("notificationList", list);
        return mav;
    }
}
