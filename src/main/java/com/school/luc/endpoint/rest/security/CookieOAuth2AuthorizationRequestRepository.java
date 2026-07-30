package com.school.luc.endpoint.rest.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import org.springframework.web.util.WebUtils;

@Component
public class CookieOAuth2AuthorizationRequestRepository
    implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

  public static final String OAUTH2_AUTH_REQUEST_COOKIE_NAME = "OAUTH2_AUTH_REQUEST";
  private static final int COOKIE_EXPIRE_SECONDS = 180; // 3 minutes

  @org.springframework.beans.factory.annotation.Value("${SECURE_COOKIE:true}")
  private boolean secureCookie;

  @Override
  public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, OAUTH2_AUTH_REQUEST_COOKIE_NAME);
    if (cookie == null || cookie.getValue() == null || cookie.getValue().isBlank()) {
      return null;
    }
    return deserialize(cookie.getValue());
  }

  @Override
  public void saveAuthorizationRequest(
      OAuth2AuthorizationRequest authorizationRequest,
      HttpServletRequest request,
      HttpServletResponse response) {
    if (authorizationRequest == null) {
      deleteCookie(request, response);
      return;
    }
    String value = serialize(authorizationRequest);
    Cookie cookie = new Cookie(OAUTH2_AUTH_REQUEST_COOKIE_NAME, value);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setSecure(secureCookie);
    cookie.setMaxAge(COOKIE_EXPIRE_SECONDS);
    // SameSite=Lax is enough for top-level redirect from Casdoor
    cookie.setAttribute("SameSite", "Lax");
    response.addCookie(cookie);
  }

  @Override
  public OAuth2AuthorizationRequest removeAuthorizationRequest(
      HttpServletRequest request, HttpServletResponse response) {
    OAuth2AuthorizationRequest authRequest = loadAuthorizationRequest(request);
    deleteCookie(request, response);
    return authRequest;
  }

  private void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
    Cookie cookie = new Cookie(OAUTH2_AUTH_REQUEST_COOKIE_NAME, "");
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setSecure(secureCookie);
    cookie.setMaxAge(0);
    cookie.setAttribute("SameSite", "Lax");
    response.addCookie(cookie);
  }

  private String serialize(OAuth2AuthorizationRequest authorizationRequest) {
    byte[] bytes = SerializationUtils.serialize(authorizationRequest);
    return Base64.getUrlEncoder().encodeToString(bytes);
  }

  private OAuth2AuthorizationRequest deserialize(String value) {
    try {
      byte[] bytes = Base64.getUrlDecoder().decode(value.getBytes(StandardCharsets.UTF_8));
      return (OAuth2AuthorizationRequest) SerializationUtils.deserialize(bytes);
    } catch (Exception e) {
      return null;
    }
  }
}
