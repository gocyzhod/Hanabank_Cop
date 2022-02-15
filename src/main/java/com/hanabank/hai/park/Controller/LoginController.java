package com.hanabank.hai.park.Controller;


import com.hanabank.hai.park.Dto.UserInfoDto;
import com.hanabank.hai.park.Servicepl.UserServicepl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor

public class LoginController {

    private final UserServicepl userService;

    @PostMapping("/user")
    public String signup(UserInfoDto dto){
        userService.save(dto);
        return "redirect:/login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}
