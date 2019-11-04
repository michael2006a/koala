package com.epiroc.koala.exam.mapper;

import com.epiroc.koala.common.core.persistence.CrudMapper;
import com.epiroc.koala.exam.api.module.Knowledge;
import org.apache.ibatis.annotations.Mapper;

/**
 * 知识库Mapper
 *
 * @author tangyi
 * @date 2019/1/1 15:03
 */
@Mapper
public interface KnowledgeMapper extends CrudMapper<Knowledge> {

}
