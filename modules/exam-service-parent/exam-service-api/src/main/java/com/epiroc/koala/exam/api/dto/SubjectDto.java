package com.epiroc.koala.exam.api.dto;

import com.epiroc.koala.exam.api.module.Answer;
import com.epiroc.koala.exam.api.module.SubjectOption;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.epiroc.koala.common.core.persistence.BaseEntity;
import java.util.List;
import lombok.Data;

/**
 * @author tangyi
 * @date 2019/1/9 20:58
 */
@Data
public class SubjectDto extends BaseEntity<SubjectDto> {

  /**
   * 考试ID
   */
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Long examinationId;

  /**
   * 考试记录ID
   */
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Long examinationRecordId;

  /**
   * 题目分类ID
   */
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Long categoryId;

  /**
   * 题目名称
   */
  private String subjectName;

  /**
   * 题目类型
   */
  private Integer type;

  /**
   * 选择题类型
   */
  private Integer choicesType;

  /**
   * 分值
   */
  private Integer score;


  /**
   * 解析
   */
  private String analysis;

  /**
   * 难度等级
   */
  private Integer level;

  /**
   * 答题
   */
  private Answer answer;

  /**
   * 选项列表
   */
  private List<SubjectOption> options;
}
