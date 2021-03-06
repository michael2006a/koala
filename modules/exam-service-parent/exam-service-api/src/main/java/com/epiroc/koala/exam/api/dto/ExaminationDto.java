package com.epiroc.koala.exam.api.dto;

import com.epiroc.koala.exam.api.module.Course;
import com.epiroc.koala.exam.api.module.Examination;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tangyi
 * @date 2018/11/20 22:02
 */
@Data
@NoArgsConstructor
public class ExaminationDto extends Examination {

  private Course course;

}
