package com.eggbus.common;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
  private List<T> list;
  private Long total;
  private Long page;
  private Long pageSize;
}

