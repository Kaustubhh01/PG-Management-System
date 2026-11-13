package com.pgmanagement.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter
        extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

    	String path = request.getRequestURI();

    	if(path.contains("/users/login")
    	        ||
    	   path.contains("/users/register")
    	        ||
    	   path.endsWith(".html")
    	        ||
    	   path.equals("/")
    	        ||
    	   path.contains("/rooms/all")){

    	    filterChain.doFilter(
    	            request,
    	            response
    	    );

    	    return;
    	}

        String authHeader =
                request.getHeader(
                        "Authorization"
                );

        if(authHeader == null
                ||
           !authHeader.startsWith("Bearer ")){

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            return;
        }

        String token =
                authHeader.substring(7);
        
        if(!jwtUtil.validateToken(token)){

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            return;
        }
        

        String role =
                jwtUtil.extractRole(token);
        
        
        //protect admin apis
        if(path.startsWith("/admin")
                && !role.equals("ADMIN")){

            response.setStatus(
                    HttpServletResponse.SC_FORBIDDEN
            );

            return;
        }
        
        //protect room management apis
        if(
        	    (
        	        path.contains("/rooms/add")
        	        ||
        	        path.contains("/rooms/update")
        	        ||
        	        path.contains("/rooms/delete")
        	    )
        	    &&
        	    !role.equals("ADMIN")
        	){

        	    response.setStatus(
        	            HttpServletResponse.SC_FORBIDDEN
        	    );

        	    return;
        	}
        
        //protect user list apis
        if(
        	    path.contains("/users/all")
        	    &&
        	    !role.equals("ADMIN")
        	){

        	    response.setStatus(
        	            HttpServletResponse.SC_FORBIDDEN
        	    );

        	    return;
        	}
        
        //protect all bookiing apis
        if(
        	    path.contains("/bookings/all")
        	    &&
        	    !role.equals("ADMIN")
        	){

        	    response.setStatus(
        	            HttpServletResponse.SC_FORBIDDEN
        	    );

        	    return;
        	}
        
        filterChain.doFilter(
                request,
                response
        );
    }
    
    
}