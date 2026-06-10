package com.eggbus.dto;

import lombok.Data;

@Data
public class LeadQueryRequest {
  private Long page = 1L;
  private Long pageSize = 10L;
  private String keyword;
  private String type;
  private String status;
}

