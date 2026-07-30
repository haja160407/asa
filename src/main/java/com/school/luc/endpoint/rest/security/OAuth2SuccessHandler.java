package com.school.luc.endpoint.rest.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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

    var roles = principal.getAttributes().get("roles");
    boolean hasRole =
        roles instanceof List<?> list
            && list.stream()
                .filter(item -> item instanceof Map<?, ?> map && map.get("name") instanceof String)
                .map(item -> (Map<?, ?>) item)
                .map(map -> ((String) map.get("name")).toLowerCase(Locale.ROOT))
                .anyMatch("org_collaborator"::equals);
    if (!hasRole) {
      response.sendRedirect("/?error=unauthorized_role");
      return;
    }
    super.onAuthenticationSuccess(request, response, authentication);
  }
}
