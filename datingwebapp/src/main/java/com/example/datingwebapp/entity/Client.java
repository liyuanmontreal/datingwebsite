package com.example.datingwebapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_client" )
public class Client implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum Role {
        Client,
        VIP,
        ADMIN
    }

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Need to choose your role")
    private Role role= Role.Client;

    @Column(nullable = false, unique = true)
    @Email(message = "Please enter a valid e-mail address")
    private String email;

    @Column(nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "client")
    private Profile profile;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return list;
    }



    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    //email as username

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Client(Role role,String email, String password, String passwordRepeat) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.confirmPassword = passwordRepeat;
    }

    public Client(String email, String password, String passwordRepeat) {
        this.email = email;
        this.password = password;
        this.confirmPassword = passwordRepeat;
    }

    public Client(Role role,String email, String password) {
        this.role = role;
        this.email = email;
        this.password = password;
    }
    public Client(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

