package com.eggbus.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eggbus.common.PageResult;
import com.eggbus.dto.LeadCreateRequest;
import com.eggbus.dto.LeadQueryRequest;
import com.eggbus.entity.Lead;
import com.eggbus.mapper.LeadMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class LeadService {
  private static final List<String> ALLOWED_STATUS = List.of("未处理", "跟进中", "已合作", "无效");

  private final LeadMapper leadMapper;

  public void create(LeadCreateRequest request) {
    Lead lead = new Lead();
    lead.setName(request.getName());
    lead.setPhone(request.getPhone());
    lead.setType(request.getType());
    lead.setMessage(request.getMessage());
    lead.setSource(StringUtils.hasText(request.getSource()) ? request.getSource() : "website");
    lead.setStatus("未处理");
    lead.setCreatedAt(LocalDateTime.now());
    lead.setUpdatedAt(LocalDateTime.now());
    lead.setDeleted(0);
    leadMapper.insert(lead);
  }

  public PageResult<Lead> page(LeadQueryRequest query) {
    Page<Lead> page = new Page<>(
      query.getPage() == null ? 1L : query.getPage(),
      query.getPageSize() == null ? 10L : query.getPageSize()
    );

    LambdaQueryWrapper<Lead> wrapper = buildWrapper(query);
    wrapper.orderByDesc(Lead::getCreatedAt);

    Page<Lead> result = leadMapper.selectPage(page, wrapper);
    return new PageResult<>(result.getRecords(), result.getTotal(), result.getCurrent(), result.getSize());
  }

  public void updateStatus(Long id, String status) {
    if (!ALLOWED_STATUS.contains(status)) {
      throw new IllegalArgumentException("处理状态不正确");
    }
    Lead lead = requireLead(id);
    lead.setStatus(status);
    lead.setUpdatedAt(LocalDateTime.now());
    leadMapper.updateById(lead);
  }

  public void updateRemark(Long id, String remark) {
    Lead lead = requireLead(id);
    lead.setRemark(remark);
    lead.setUpdatedAt(LocalDateTime.now());
    leadMapper.updateById(lead);
  }

  public Map<String, Long> stats() {
    LocalDate today = LocalDate.now();
    Long todayCount = leadMapper.selectCount(new LambdaQueryWrapper<Lead>()
      .ge(Lead::getCreatedAt, today.atStartOfDay())
      .lt(Lead::getCreatedAt, today.plusDays(1).atStartOfDay()));
    Long pendingCount = leadMapper.selectCount(new LambdaQueryWrapper<Lead>().eq(Lead::getStatus, "未处理"));
    Long cooperatedCount = leadMapper.selectCount(new LambdaQueryWrapper<Lead>().eq(Lead::getStatus, "已合作"));
    Long totalCount = leadMapper.selectCount(new LambdaQueryWrapper<>());

    return Map.of(
      "todayCount", todayCount,
      "pendingCount", pendingCount,
      "cooperatedCount", cooperatedCount,
      "totalCount", totalCount
    );
  }

  private LambdaQueryWrapper<Lead> buildWrapper(LeadQueryRequest query) {
    LambdaQueryWrapper<Lead> wrapper = new LambdaQueryWrapper<>();
    if (StringUtils.hasText(query.getKeyword())) {
      wrapper.and(item -> item
        .like(Lead::getName, query.getKeyword())
        .or()
        .like(Lead::getPhone, query.getKeyword()));
    }
    if (StringUtils.hasText(query.getType())) {
      wrapper.eq(Lead::getType, query.getType());
    }
    if (StringUtils.hasText(query.getStatus())) {
      wrapper.eq(Lead::getStatus, query.getStatus());
    }
    return wrapper;
  }

  private Lead requireLead(Long id) {
    Lead lead = leadMapper.selectById(id);
    if (lead == null) throw new IllegalArgumentException("留言不存在");
    return lead;
  }
}

