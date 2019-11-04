package com.epiroc.koala.gateway.mapper;

import com.epiroc.koala.common.core.persistence.CrudMapper;
import com.epiroc.koala.gateway.module.Route;
import org.apache.ibatis.annotations.Mapper;

/**
 * Route mapper
 *
 * @author tangyi
 * @date 2019/4/2 15:00
 */
@Mapper
public interface RouteMapper extends CrudMapper<Route> {

}
