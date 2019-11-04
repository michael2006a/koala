package com.epiroc.koala.exam.api.dto;

import com.epiroc.koala.exam.api.module.Examination;
import com.epiroc.koala.exam.api.module.ExaminationRecord;
import java.io.Serializable;
import lombok.Data;

/**
 * @author tangyi
 * @date 2019/4/30 16:54
 */
@Data
public class StartExamDto implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 考试记录信息
   */
  private ExaminationRecord examRecord;

  /**
   * 考试信息
   */
  private Examination examination;

  /**
   * 题目信息
   */
  private SubjectDto subjectDto;
}
