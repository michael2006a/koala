package com.epiroc.koala.common.core.vo;

import com.epiroc.koala.common.core.model.Log;
import com.epiroc.koala.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * logVo
 *
 * @author tangyi
 * @date 2019-01-05 17:07
 */
@Data
public class LogVo extends BaseEntity<LogVo> {

  private Log log;

  private String username;
}
