package com.epiroc.koala.msc.api.feign.fallback;

import com.epiroc.koala.common.core.model.ResponseBean;
import com.epiroc.koala.msc.api.dto.SmsDto;
import com.epiroc.koala.msc.api.feign.MscServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 消息中心服务断路器
 *
 * @author tangyi
 * @date 2019/07/02 16:09
 */
@Slf4j
@Component
public class MscServiceClientFallbackImpl implements MscServiceClient {

  private Throwable throwable;

  @Override
  public ResponseBean<?> sendSms(SmsDto smsDto) {
    log.error("feign 发送短信失败:{}, {}, {}", smsDto.getReceiver(), smsDto.getContent(), throwable);
    return null;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public void setThrowable(Throwable throwable) {
    this.throwable = throwable;
  }
}
