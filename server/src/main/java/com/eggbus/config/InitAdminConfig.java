package com.eggbus.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.eggbus.entity.AdminUser;
import com.eggbus.mapper.AdminUserMapper;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitAdminConfig {
  @Bean
  @ConfigurationProperties(prefix = "busegg.init-admin")
  public InitAdminProperties initAdminProperties() {
    return new InitAdminProperties();
  }

  @Bean
  public CommandLineRunner initAdminUser(
      InitAdminProperties properties,
      AdminUserMapper adminUserMapper,
      PasswordEncoder passwordEncoder
  ) {
    return args -> {
      Long count = adminUserMapper.selectCount(
        new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getUsername, properties.getUsername())
      );
      if (count > 0) return;

      AdminUser user = new AdminUser();
      user.setUsername(properties.getUsername());
      user.setPassword(passwordEncoder.encode(properties.getPassword()));
      user.setNickname(properties.getNickname());
      user.setRoleCode("SUPER_ADMIN");
      user.setStatus(1);
      user.setCreatedAt(LocalDateTime.now());
      user.setUpdatedAt(LocalDateTime.now());
      user.setDeleted(0);
      adminUserMapper.insert(user);
    };
  }

  @Data
  public static class InitAdminProperties {
    private String username;
    private String password;
    private String nickname;
  }
}

