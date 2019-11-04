package com.epiroc.koala.user.mapper;

import com.epiroc.koala.common.core.persistence.CrudMapper;
import com.epiroc.koala.user.api.module.UserStudent;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户学生Mapper
 *
 * @author tangyi
 * @date 2019/07/09 15:57
 */
@Mapper
public interface UserStudentMapper extends CrudMapper<UserStudent> {

  /**
   * 根据userId查询
   *
   * @param userId userId
   * @return List
   */
  List<UserStudent> getByUserId(String userId);

  /**
   * 根据studentId查询
   *
   * @param studentId studentId
   * @return UserStudent
   */
  UserStudent getByStudentId(String studentId);

  /**
   * 根据用户id删除
   *
   * @param userId userId
   * @return int
   */
  int deleteByUserId(String userId);

  /**
   * 根据学生id删除
   *
   * @param studentId studentId
   * @return int
   */
  int deleteByStudentId(String studentId);
}
