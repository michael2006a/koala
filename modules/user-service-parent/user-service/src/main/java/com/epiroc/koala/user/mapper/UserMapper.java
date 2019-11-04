package com.epiroc.koala.user.mapper;

import com.epiroc.koala.common.core.persistence.CrudMapper;
import com.epiroc.koala.common.core.vo.UserVo;
import com.epiroc.koala.user.api.module.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户mapper接口
 *
 * @author tangyi
 * @date 2018-08-25 15:27
 */
@Mapper
public interface UserMapper extends CrudMapper<User> {

  /**
   * 查询用户数量
   *
   * @param userVo userVo
   * @return Integer
   */
  Integer userCount(UserVo userVo);
}
