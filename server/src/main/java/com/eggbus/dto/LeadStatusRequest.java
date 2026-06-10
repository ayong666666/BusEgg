package com.eggbus.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LeadStatusRequest {
  @NotBlank
  private String status;
}

