package com.epiroc.koala.user.mapper;

import com.epiroc.koala.common.core.persistence.CrudMapper;
import com.epiroc.koala.user.api.module.Dept;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单mapper
 *
 * @author tangyi
 * @date 2018/8/26 22:34
 */
@Mapper
public interface DeptMapper extends CrudMapper<Dept> {

}
