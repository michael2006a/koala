package com.epiroc.koala.common.log;

import com.epiroc.koala.common.log.event.LogListener;
import com.epiroc.koala.common.log.aspect.LogAspect;
import com.epiroc.koala.user.api.feign.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author tangyi
 * @date 2019/3/12 23:51
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class LogAutoConfiguration {

  @Autowired
  private UserServiceClient userServiceClient;

  @Bean
  public LogListener sysLogListener() {
    return new LogListener(userServiceClient);
  }

  @Bean
  public LogAspect sysLogAspect() {
    return new LogAspect();
  }
}
