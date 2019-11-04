package com.epiroc.koala.common.security.exceptions;

import com.epiroc.koala.common.security.serializer.CustomOauthExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 定制OAuth2Exception
 *
 * @author tangyi
 * @date 2019/3/18 22:36
 */
@Data
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

  /**
   * 错误码
   */
  private int code;

  public CustomOauthException(String msg, int code) {
    super(msg);
    this.code = code;
  }
}
