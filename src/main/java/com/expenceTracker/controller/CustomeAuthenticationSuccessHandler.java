package com.expenceTracker.controller;

import java.io.IOException;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.expenceTracker.entity.User;
import com.expenceTracker.service.ClientService;
import com.expenceTracker.service.UserService;
import com.expenceTracker.entity.Client;


import org.springframework.security.core.Authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@Controller
public class CustomeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    UserService userService;
    ClientService clientService;

    public CustomeAuthenticationSuccessHandler(UserService userService, ClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        User user = userService.findUserByUserName(username);
        Client client = clientService.findClientById(user.getId());
        HttpSession session = request.getSession();
        session.setAttribute("client", client);
        response.sendRedirect(request.getContextPath()+"/list");
    }
}