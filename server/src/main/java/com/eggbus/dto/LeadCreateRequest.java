package com.eggbus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LeadCreateRequest {
  @NotBlank
  private String name;

  @NotBlank
  @Pattern(regexp = "^1[3-9]\\d{9}$")
  private String phone;

  @NotBlank
  private String type;

  private String message;
  private String source;
}

