package com.pss.PSS.configs.jwt;

import java.io.Console;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pss.PSS.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = parseJwt(request);
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}catch (Exception e) {
			System.err.println(e);
		}
		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
	    String headerAuth = request.getHeader("Cookie");
//		System.out.println(headerAuth);
	    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("PSS=Authorization : Bearer ") && headerAuth.startsWith(";")) {
	      return headerAuth.substring(headerAuth.lastIndexOf("PSS=")+27, headerAuth.length());
	    }
		else{
			String[] arr = headerAuth.split("; ");
			for(int i=0;i<arr.length;i++){
				//System.out.println(arr[i].substring(arr[i].lastIndexOf("PSS=")+27,arr[i].length()));
				if(arr[i].startsWith("PSS=Authorization : Bearer ")){
					return arr[i].substring(arr[i].lastIndexOf("PSS=")+27,arr[i].length());
				}
			}
		}

	    return null;
	  }
}
