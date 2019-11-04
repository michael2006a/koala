package com.epiroc.koala.gateway.vo;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

/**
 * 断言信息
 *
 * @author tangyi
 * @date 2019/3/27 11:08
 */
@Data
public class RoutePredicateVo {

  /**
   * 断言对应的Name
   */
  private String name;

  /**
   * 配置的断言规则
   */
  private Map<String, String> args = new LinkedHashMap<>();
}
