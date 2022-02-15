package com.hanabank.hai.park.Service;


import com.hanabank.hai.park.Entity.UserInfo;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface Service {

    public Long save(UserInfo infoDto);

    public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException;

}
