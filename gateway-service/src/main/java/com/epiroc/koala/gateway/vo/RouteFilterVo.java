package com.epiroc.koala.gateway.vo;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

/**
 * 过滤器信息
 *
 * @author tangyi
 * @date 2019/3/27 11:07
 */
@Data
public class RouteFilterVo {

  /**
   * Filter Name
   */
  private String name;

  /**
   * 对应的路由规则
   */
  private Map<String, String> args = new LinkedHashMap<>();
}
