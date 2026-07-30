package com.school.luc.endpoint.rest.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final WorkerFromAuthentication workerFromAuthentication;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    var principal = (DefaultOidcUser) authentication.getPrincipal();
    var workerOpt = workerFromAuthentication.apply(authentication);
    if (workerOpt.isEmpty()) {
      response.sendRedirect("/?error=unknown_worker");
      return;
    }

    var rolesAttr = principal.getAttributes().get("roles");
    log.info("Casdoor roles attribute: {}", rolesAttr);
    log.info("Casdoor roles type: {}", rolesAttr != null ? rolesAttr.getClass().getName() : "null");

    boolean hasRole = false;
    if (rolesAttr instanceof List<?> list) {
      for (var item : list) {
        if (item instanceof String s && s.equalsIgnoreCase("org_collaborator")) {
          hasRole = true;
          break;
        }
        if (item instanceof Map<?, ?> map && map.get("name") instanceof String s
            && s.equalsIgnoreCase("org_collaborator")) {
          hasRole = true;
          break;
        }
      }
    }
    if (!hasRole) {
      log.warn("User {} lacks org_collaborator role", principal.getAttribute("email"));
      response.sendRedirect("/?error=unauthorized_role");
      return;
    }
    super.onAuthenticationSuccess(request, response, authentication);
  }
}
