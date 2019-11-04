package com.epiroc.koala.exam.mapper;

import com.epiroc.koala.common.core.persistence.CrudMapper;
import com.epiroc.koala.exam.api.module.Pictures;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图片mapper
 *
 * @author tangyi
 * @date 2019/6/16 14:50
 */
@Mapper
public interface PicturesMapper extends CrudMapper<Pictures> {

}
