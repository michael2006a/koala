package com.epiroc.koala.user.mapper;

import com.epiroc.koala.common.core.persistence.CrudMapper;
import com.epiroc.koala.user.api.module.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色mapper
 *
 * @author tangyi
 * @date 2018/8/26 09:33
 */
@Mapper
public interface RoleMapper extends CrudMapper<Role> {

  /**
   * 根据角色code查询
   *
   * @param role role
   * @return Role
   * @author tangyi
   * @date 2019/09/21 12:08:29
   */
  Role findByRoleCode(Role role);
}
