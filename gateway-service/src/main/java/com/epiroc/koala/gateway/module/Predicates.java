package com.epiroc.koala.gateway.module;

import com.epiroc.koala.common.core.persistence.BaseEntity;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 断言
 *
 * @author tangyi
 * @date 2019-08-16 17:51
 */
@Data
public class Predicates extends BaseEntity<Predicates> {

  /**
   * 路由ID
   */
  @NotBlank(message = "路由ID不能为空")
  private String routeId;

  /**
   * 断言名称
   */
  @NotBlank(message = "predicates name不能为空")
  private String name;

  /**
   * 断言参数
   */
  private String args;
}
