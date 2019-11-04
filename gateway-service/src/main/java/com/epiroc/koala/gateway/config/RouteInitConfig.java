package com.epiroc.koala.gateway.config;

import com.epiroc.koala.gateway.service.RouteService;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化的时候加载路由数据
 *
 * @author tangyi
 * @date 2019/4/2 14:40
 */
@Slf4j
@AllArgsConstructor
@Configuration
public class RouteInitConfig {

  private final RouteService routeService;

  @PostConstruct
  public void initRoute() {
    routeService.refresh();
  }
}
