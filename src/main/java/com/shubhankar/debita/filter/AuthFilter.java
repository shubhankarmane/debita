package com.shubhankar.debita.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
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
        Claims claims;

        if(authHeader != null) {
            try {
                claims = Jwts.parser().setSigningKey("example_demo").parseClaimsJws(authHeader).getBody();
                Integer userId = Integer.valueOf(claims.get("userId").toString());
                request.setAttribute("userId", userId);
            }
            catch(JwtException e) {
                response.sendError(HttpStatus.FORBIDDEN.value(), "Invalid token");
                return;
            }
        }
        else {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token missing");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
