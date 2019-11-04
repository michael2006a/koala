package com.epiroc.koala.exam.service;

import com.epiroc.koala.common.core.service.CrudService;
import com.epiroc.koala.exam.api.module.ExaminationAuth;
import com.epiroc.koala.exam.mapper.ExaminationAuthMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考试权限Service
 *
 * @author tangyi
 * @date 2019-07-16 14:04
 */
@Slf4j
@AllArgsConstructor
@Service
public class ExaminationAuthService extends CrudService<ExaminationAuthMapper, ExaminationAuth> {

  /**
   * 根据考生ID查询
   *
   * @param examinationAuth examinationAuth
   * @return List<ExaminationAuth>
   * @author tangyi
   * @date 2019-07-16 14:06
   */
  public List<ExaminationAuth> finListByUserId(ExaminationAuth examinationAuth) {
    return null;
  }

  /**
   * 根据考试ID查询
   *
   * @param examinationAuth examinationAuth
   * @return List<ExaminationAuth>ß
   * @author tangyi
   * @date 2019-07-16 14:07
   */
  public List<ExaminationAuth> finListByExaminationId(ExaminationAuth examinationAuth) {
    return null;
  }

  /**
   * 根据考试ID删除
   *
   * @param examinationAuth examinationAuth
   * @return int
   * @author tangyi
   * @date 2019-07-16 14:08
   */
  @Transactional
  public int deleteByExaminationId(ExaminationAuth examinationAuth) {
    return -1;
  }

  /**
   * 根据考生ID删除
   *
   * @param examinationAuth examinationAuth
   * @return int
   * @author tangyi
   * @date 2019-07-16 14:09
   */
  @Transactional
  public int deleteByUserId(ExaminationAuth examinationAuth) {
    return -1;
  }
}

