package com.eggbus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName("admin_user")
public class AdminUser {
  @TableId(type = IdType.AUTO)
  private Long id;
  private String username;
  private String password;
  private String nickname;
  private String phone;
  private String roleCode;
  private Integer status;
  private LocalDateTime lastLoginTime;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Integer deleted;
}

