package com.example.auth.security;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.auth.security.JwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.example.auth.security.UserDetailsServiceImpl;
//import com.example.auth.service.AppUserService;
//import com.example.auth.model.AppUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.PrintWriter;


public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter   {


	private AuthenticationManager authManager;
	
	private final JwtConfig jwtConfig;

	//private UserDetailsServiceImpl userK;
	//private UserCredentials creds;

	private AppUser creds;
    
	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			//List <AppUser> svi=userK.appUserService.getAllAppUsers();
			//for(AppUser appUser: svi) {
			//	System.out.printf("drugi: %s", appUser.getRole());
			//}



			creds = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
			//System.out.printf("USO1, %s",String.valueOf(request));
			//System.out.printf("USO2 --------->, %s",creds.getRole());
			//creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					creds.getUsername(), creds.getPassword(), Collections.emptyList());
			return authManager.authenticate(authToken);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
				
		
		Long now = System.currentTimeMillis();
		String token = Jwts.builder()
			.setSubject(auth.getName())	
			.claim("authorities", auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))
			.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
			.compact();
		//response.getWriter().println("Test");
		response.addHeader(jwtConfig.getHeader(), token);
		response.addHeader("username", creds.getUsername());//username, role, natavnikId, ucenikId
		response.addHeader("rola", creds.getRole());
		//response.addHeader("natavnikId", Integer.toString(creds.getNastavnikId()));
		//esponse.addHeader("ucenikId", Integer.toString(creds.getUcenikId()));
		response.addHeader("password", creds.getPassword());
		// try {
    //     response.setContentType("application/json");
		// 		response.setCharacterEncoding("utf-8");
    //     PrintWriter out = response.getWriter();
		// 		JsonObject json = new JsonObject();
		// 		json.put("token", token);
    //     out.print(json.toString());
    // } catch (IOException e) {
    //     e.printStackTrace();
    // }
		
	}

	private static class TokenHolder {
		private String token;
		public String getToken() {
			return token;
		}
	    
	  public void setToken(String token) {
			this.token = token;
		}

		public TokenHolder(String token){
			this.token = token;
		}
	}

	private static class UserCredentials {
		private String username="";
		private String password="";
		public Integer natavnik_id=0;
		public Integer ucenik_id=0;

		private String role="";


	    
	    public String getUsername() {
			return username;
		}
	    
	    public void setUsername(String username) {
			this.username = username;
		}

	    public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public Integer getNastavnikId() {
			return natavnik_id;
		}
		public Integer getUcenikId() {
			return ucenik_id;
		}
		
	    

	}
}