package com.epiroc.koala.common.core.utils;

import com.epiroc.koala.common.core.exceptions.CommonException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 以静态变量保存Spring ApplicationContext
 *
 * @author tangyi
 * @date 2018-08-24 19:04
 */
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

  private static ApplicationContext applicationContext = null;

  /**
   * 获取applicationContext
   */
  public static ApplicationContext getApplicationContext() {
    if (applicationContext == null) {
      throw new CommonException("applicationContext为空！");
    }
    return applicationContext;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    SpringContextHolder.applicationContext = applicationContext;
  }

  /**
   * 发布事件
   */
  public static void publishEvent(ApplicationEvent event) {
    if (applicationContext == null) {
      return;
    }
    applicationContext.publishEvent(event);
  }

  @Override
  public void destroy() throws Exception {
    applicationContext = null;
  }
}
