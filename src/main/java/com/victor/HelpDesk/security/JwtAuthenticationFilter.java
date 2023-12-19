package com.victor.HelpDesk.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.HelpDesk.domain.dto.CredenciaisDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try{
        CredenciaisDto creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDto.class);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getSenha(), new ArrayList<>());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return authentication;
    }catch (Exception e){
        throw new RuntimeException(e);

    }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String username = ((UserSS) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.genereteToken(username);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Authorization","Bearer" + token );
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().append(json());

   }
    private CharSequence json() {
       long date = new Date().getTime();
       return "{"
               + "\"timestamp\": " + date + ", "
               + "\"status\": 401, "
               + "\"error\": \"Não autorizado\", "
               + "\"message\": \"Email ou senha inválidos\", "
               + "\"path\": \"/login\"}";

    }
}
