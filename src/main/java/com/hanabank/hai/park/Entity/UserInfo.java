package com.hanabank.hai.park.Entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Builder

public class UserInfo {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "email" , unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "auth")
    private String auth;


    public Collection<? extends GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> roles = new HashSet<>();

        for ( String role : auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }

        return roles;

    }

    public boolean isAccountNonExpired() {
        return true;
    }
    public boolean isAccountNonLocked(){
        return true;
    }
    public boolean isCredentialsNonExpired(){
        return true;
    }
    public boolean isEnabled(){
        return true;
    }


}
