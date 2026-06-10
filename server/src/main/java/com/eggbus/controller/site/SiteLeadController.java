package com.eggbus.controller.site;

import com.eggbus.common.Result;
import com.eggbus.dto.LeadCreateRequest;
import com.eggbus.service.LeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/site/leads")
public class SiteLeadController {
  private final LeadService leadService;

  @PostMapping
  public Result<Void> create(@Valid @RequestBody LeadCreateRequest request) {
    leadService.create(request);
    return Result.ok();
  }
}

