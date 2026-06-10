package com.eggbus.controller.admin;

import com.eggbus.common.PageResult;
import com.eggbus.common.Result;
import com.eggbus.dto.LeadQueryRequest;
import com.eggbus.dto.LeadRemarkRequest;
import com.eggbus.dto.LeadStatusRequest;
import com.eggbus.entity.Lead;
import com.eggbus.service.LeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/leads")
public class LeadController {
  private final LeadService leadService;

  @GetMapping
  public Result<PageResult<Lead>> page(LeadQueryRequest query) {
    return Result.ok(leadService.page(query));
  }

  @PutMapping("/{id}/status")
  public Result<Void> updateStatus(@PathVariable Long id, @Valid @RequestBody LeadStatusRequest request) {
    leadService.updateStatus(id, request.getStatus());
    return Result.ok();
  }

  @PutMapping("/{id}/remark")
  public Result<Void> updateRemark(@PathVariable Long id, @RequestBody LeadRemarkRequest request) {
    leadService.updateRemark(id, request.getRemark());
    return Result.ok();
  }
}

