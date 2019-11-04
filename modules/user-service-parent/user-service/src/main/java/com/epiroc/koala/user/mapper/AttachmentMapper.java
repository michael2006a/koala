package com.epiroc.koala.user.mapper;

import com.epiroc.koala.common.core.persistence.CrudMapper;
import com.epiroc.koala.user.api.module.Attachment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 附件mapper
 *
 * @author tangyi
 * @date 2018/10/30 20:55
 */
@Mapper
public interface AttachmentMapper extends CrudMapper<Attachment> {

}
