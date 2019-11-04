package com.epiroc.koala.user.api.module;

import com.epiroc.koala.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 角色菜单关联
 *
 * @author tangyi
 * @date 2018/8/26 22:24
 */
@Data
public class RoleMenu extends BaseEntity<RoleMenu> {

  private Long roleId;

  private Long menuId;
}
