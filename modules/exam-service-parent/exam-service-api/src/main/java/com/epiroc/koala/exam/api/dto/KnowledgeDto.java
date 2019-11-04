package com.epiroc.koala.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.epiroc.koala.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * @author tangyi
 * @date 2019/1/1 22:04
 */
@Data
public class KnowledgeDto extends BaseEntity<KnowledgeDto> {

  /**
   * 知识名称
   */
  private String knowledgeName;

  /**
   * 知识描述
   */
  private String knowledgeDesc;

  /**
   * 附件ID
   */
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Long attachmentId;

  /**
   * 状态
   */
  private String status;

  /**
   * 附件名称
   */
  private String attachName;

  /**
   * 附件大小
   */
  private String attachSize;
}