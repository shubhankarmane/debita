package com.shubhankar.debita.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null) {
            Claims claims = Jwts.parser().setSigningKey("example_demo").parseClaimsJws(authHeader).getBody();
//            System.out.println("claims = " + claims);

            Integer userId = Integer.valueOf(claims.get("userId").toString());
            request.setAttribute("userId", userId);
        }
        else {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
