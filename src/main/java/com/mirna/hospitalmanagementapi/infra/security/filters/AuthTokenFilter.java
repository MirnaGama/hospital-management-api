package com.mirna.hospitalmanagementapi.infra.security.filters;

import java.io.IOException;
import java.util.stream.Stream;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mirna.hospitalmanagementapi.domain.entities.auth.User;
import com.mirna.hospitalmanagementapi.domain.services.UserService;
import com.mirna.hospitalmanagementapi.domain.services.auth.jwt.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader("Authorization");
		
		if (authorizationHeader.isBlank() || !authorizationHeader.startsWith("Bearer ")) {
			throw new AuthenticationException("Authorization token is null or invalid");
		}
	    
		String token = authorizationHeader.replace("Bearer ", "").trim();
		
		String tokenSubject = tokenService.getTokenSubject(token);
		
		User authenticatedUser = (User) userService.findUserByLogin(tokenSubject);
		
		Authentication auth = new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String url = request.getRequestURI();
	    return Stream.of(excluded_urls).anyMatch(x -> url.startsWith(x));
	 }
	
	private static final String[] excluded_urls = {
			"/api/auth",
			"/v3/api-docs",
			"/swagger-ui"
    };

}
