package com.epiroc.koala.exam.config;

import com.epiroc.koala.common.core.constant.MqConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MQ配置
 *
 * @author tangyi
 * @date 2019/5/3 14:53
 */
@Configuration
public class RabbitSubmitExaminationConfig {

  /**
   * 提交考试队列
   *
   * @return Queue
   */
  @Bean
  public Queue submitExaminationQueue() {
    return new Queue(MqConstant.SUBMIT_EXAMINATION_QUEUE);
  }
}
