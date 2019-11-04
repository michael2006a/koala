package com.epiroc.koala.exam.api.feign.factory;

import com.epiroc.koala.exam.api.feign.fallback.ExaminationServiceClientFallbackImpl;
import com.epiroc.koala.exam.api.feign.ExaminationServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author tangyi
 * @date 2019/3/26 09:49
 */
@Component
public class ExaminationServiceClientFallbackFactory implements
    FallbackFactory<ExaminationServiceClient> {

  @Override
  public ExaminationServiceClient create(Throwable throwable) {
    ExaminationServiceClientFallbackImpl examinationServiceClientFallback = new ExaminationServiceClientFallbackImpl();
    examinationServiceClientFallback.setThrowable(throwable);
    return examinationServiceClientFallback;
  }
}
