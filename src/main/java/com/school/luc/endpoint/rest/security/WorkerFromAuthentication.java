package com.school.luc.endpoint.rest.security;

import com.school.luc.model.Worker;
import com.school.luc.repository.WorkerRepository;
import java.util.Optional;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class WorkerFromAuthentication implements Function<Authentication, Optional<Worker>> {

  private final WorkerRepository workerRepository;

  @Override
  public Optional<Worker> apply(Authentication authentication) {
    var principal = (DefaultOidcUser) authentication.getPrincipal();
    var email = principal.getEmail();
    return workerRepository.findByEmail(email);
  }
}
