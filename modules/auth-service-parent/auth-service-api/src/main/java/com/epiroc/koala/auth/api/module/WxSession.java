package com.epiroc.koala.auth.api.module;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 封装微信登录状态
 *
 * @author tangyi
 * @date 2019/07/05 20:35
 */
@Data
@AllArgsConstructor
public class WxSession implements Serializable {

  private static final long serialVersionUID = 1L;

  private String openId;

  private String sessionKey;
}
