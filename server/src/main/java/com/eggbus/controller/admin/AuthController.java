package com.eggbus.controller.admin;

import com.eggbus.common.Result;
import com.eggbus.dto.LoginRequest;
import com.eggbus.service.AuthService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  public Result<Map<String, Object>> login(@Valid @RequestBody LoginRequest request) {
    return Result.ok(authService.login(request));
  }

  @GetMapping("/profile")
  public Result<Map<String, Object>> profile(Principal principal) {
    return Result.ok(authService.profile(principal.getName()));
  }
}

