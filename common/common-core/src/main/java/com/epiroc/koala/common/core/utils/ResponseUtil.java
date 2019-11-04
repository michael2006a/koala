package com.epiroc.koala.common.core.utils;

import com.epiroc.koala.common.core.model.ResponseBean;

/**
 * @author tangyi
 * @date 2019-10-08 12:03
 */
public class ResponseUtil {

  /**
   * 是否成功
   *
   * @param responseBean responseBean
   * @return boolean
   */
  public static boolean isSuccess(ResponseBean<?> responseBean) {
    return responseBean != null && responseBean.getStatus() == ResponseBean.SUCCESS;
  }
}
