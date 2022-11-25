package com.example.datingwebapp.service;

import com.example.datingwebapp.entity.Client;
import com.example.datingwebapp.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;


@Service
@AllArgsConstructor
public class ClientService implements UserDetailsService {


    @Autowired
    private final ClientRepository clientRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    private final static String PW_ERROR = "Password must be at least 6 " +
            "characters long and must contain at least one uppercase letter, one lower case letter, and one number.";

     //@Transactional annotation is the metadata that specifies the semantics of the transactions on a method.
    @Transactional
    public String register(@Valid Client client, BindingResult result) {
        if (!client.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,100}$")) {
            FieldError pwErr = new FieldError("client", "password", PW_ERROR);
            result.addError(pwErr);
        }
        boolean exists = clientRepository.findByEmail(client.getEmail()).isPresent();
        if (exists) {
            FieldError nameTaken = new FieldError("client", "email", "Email is already taken");
            result.addError(nameTaken);
        }
        if (!client.getPassword().equals(client.getConfirmPassword())) {
            FieldError pwRepeat = new FieldError("client", "confirmPassword", "Please enter the same password");
            result.addError(pwRepeat);
        }
        if (result.hasErrors()) {
            return "signUpForm";
        }
        String encodePW = bCryptPasswordEncoder.encode(client.getPassword());
        System.out.println(encodePW);
        client.setPassword(encodePW);
        clientRepository.save(client);
        return "login";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Client findByEmail(String email){
        return this.clientRepository.findByEmail(email).get();
    }

}