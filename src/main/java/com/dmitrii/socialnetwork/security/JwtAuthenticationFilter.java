package com.dmitrii.socialnetwork.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  private final CustomUserDetailsService userDetailsService;

  private final RestAuthEntryPoint entryPoint;


  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  )
      throws ServletException, IOException {

    try {
      String jwt = getJwtFromRequest(request);
      if (StringUtils.hasText(jwt)) {
        if (jwtService.validateToken(jwt)) {
          String username = jwtService.extractUsername(jwt);
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          if (jwtService.validateToken(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
          }
        }
      }
      filterChain.doFilter(request, response);

    } catch (JwtException ex) {
      SecurityContextHolder.clearContext();
      entryPoint.commence(request, response,
          new InsufficientAuthenticationException("Invalid JWT: " + ex.getMessage(), ex));
    } catch (AuthenticationException ex) {
      SecurityContextHolder.clearContext();
      entryPoint.commence(request, response, ex);
    }
  }



  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
