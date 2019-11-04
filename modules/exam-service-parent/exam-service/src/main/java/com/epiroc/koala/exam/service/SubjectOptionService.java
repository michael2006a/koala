package com.epiroc.koala.exam.service;

import com.epiroc.koala.common.core.service.CrudService;
import com.epiroc.koala.exam.api.module.SubjectOption;
import com.epiroc.koala.exam.mapper.SubjectOptionMapper;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 选择题选项service
 *
 * @author tangyi
 * @date 2019/6/16 15:01
 */
@Service
public class SubjectOptionService extends CrudService<SubjectOptionMapper, SubjectOption> {

  /**
   * 查找题目
   *
   * @param subjectOption subjectOption
   * @return SubjectOption
   * @author tangyi
   * @date 2019/6/16 15:01
   */
  @Override
  public SubjectOption get(SubjectOption subjectOption) {
    return super.get(subjectOption);
  }

  /**
   * 根据题目ID查找
   *
   * @param subjectOption subjectOption
   * @return List
   * @author tangyi
   * @date 2019/6/16 15:01
   */
  public List<SubjectOption> getBySubjectChoicesId(SubjectOption subjectOption) {
    return this.dao.getBySubjectChoicesId(subjectOption);
  }

  /**
   * 新增
   *
   * @param subjectOption subjectOption
   * @return int
   * @author tangyi
   * @date 2019/6/16 15:01
   */
  @Override
  @Transactional
  public int insert(SubjectOption subjectOption) {
    return super.insert(subjectOption);
  }

  /**
   * 批量保存
   *
   * @param subjectOptionList subjectOptionList
   * @return int
   * @author tangyi
   * @date 2019/6/16 15:01
   */
  @Transactional
  public int insertBatch(List<SubjectOption> subjectOptionList) {
    return this.dao.insertBatch(subjectOptionList);
  }

  /**
   * 更新题目
   *
   * @param subjectOption subjectOption
   * @return int
   * @author tangyi
   * @date 2019/6/16 15:01
   */
  @Override
  @Transactional
  public int update(SubjectOption subjectOption) {
    return super.update(subjectOption);
  }

  /**
   * 删除题目
   *
   * @param subjectOption subjectOption
   * @return int
   * @author tangyi
   * @date 2019/6/16 15:01
   */
  @Override
  @Transactional
  public int delete(SubjectOption subjectOption) {
    return super.delete(subjectOption);
  }

  /**
   * 根据选择题ID删除
   *
   * @param subjectOption subjectOption
   * @return int
   * @author tangyi
   * @date 2019/06/16 21:56
   */
  @Transactional
  public int deleteBySubjectChoicesId(SubjectOption subjectOption) {
    return this.dao.deleteBySubjectChoicesId(subjectOption);
  }

  /**
   * 批量删除题目
   *
   * @param ids ids
   * @return int
   * @author tangyi
   * @date 2019/6/16 15:01
   */
  @Override
  @Transactional
  public int deleteAll(Long[] ids) {
    return super.deleteAll(ids);
  }

  /**
   * 物理批量删除
   *
   * @param ids ids
   * @return int
   * @author tangyi
   * @date 2019/06/16 22:40
   */
  @Transactional
  public int physicalDeleteAll(Long[] ids) {
    return this.dao.physicalDeleteAll(ids);
  }
}
