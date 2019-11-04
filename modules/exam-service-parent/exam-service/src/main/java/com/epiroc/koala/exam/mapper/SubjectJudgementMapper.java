package com.epiroc.koala.exam.mapper;

import com.epiroc.koala.common.core.persistence.CrudMapper;
import com.epiroc.koala.exam.api.module.SubjectJudgement;
import org.apache.ibatis.annotations.Mapper;

/**
 * 判断题Mapper
 *
 * @author tangyi
 * @date 2019-07-16 13:00
 */
@Mapper
public interface SubjectJudgementMapper extends CrudMapper<SubjectJudgement> {

}
