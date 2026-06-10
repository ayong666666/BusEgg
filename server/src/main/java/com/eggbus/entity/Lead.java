package com.eggbus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName("leads")
public class Lead {
  @TableId(type = IdType.AUTO)
  private Long id;
  private String name;
  private String phone;
  private String type;
  private String message;
  private String source;
  private String status;
  private String remark;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Integer deleted;
}
