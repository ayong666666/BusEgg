package com.eggbus.controller.admin;

import com.eggbus.common.Result;
import com.eggbus.service.LeadService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/dashboard")
public class DashboardController {
  private final LeadService leadService;

  @GetMapping("/stats")
  public Result<Map<String, Long>> stats() {
    return Result.ok(leadService.stats());
  }
}

