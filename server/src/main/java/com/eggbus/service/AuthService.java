package com.eggbus.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.eggbus.dto.LoginRequest;
import com.eggbus.entity.AdminUser;
import com.eggbus.mapper.AdminUserMapper;
import com.eggbus.security.JwtService;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final AdminUserMapper adminUserMapper;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public Map<String, Object> login(LoginRequest request) {
    AdminUser user = adminUserMapper.selectOne(
      new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getUsername, request.getUsername())
    );
    if (user == null || user.getStatus() == null || user.getStatus() != 1) {
      throw new IllegalArgumentException("账号或密码错误");
    }
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("账号或密码错误");
    }

    user.setLastLoginTime(LocalDateTime.now());
    adminUserMapper.updateById(user);

    String token = jwtService.generateToken(user.getId(), user.getUsername());
    return Map.of(
      "token", token,
      "user", Map.of(
        "id", user.getId(),
        "username", user.getUsername(),
        "nickname", user.getNickname(),
        "roleCode", user.getRoleCode()
      )
    );
  }

  public Map<String, Object> profile(String username) {
    AdminUser user = adminUserMapper.selectOne(
      new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getUsername, username)
    );
    if (user == null) throw new IllegalArgumentException("用户不存在");
    return Map.of(
      "id", user.getId(),
      "username", user.getUsername(),
      "nickname", user.getNickname(),
      "roleCode", user.getRoleCode()
    );
  }
}

