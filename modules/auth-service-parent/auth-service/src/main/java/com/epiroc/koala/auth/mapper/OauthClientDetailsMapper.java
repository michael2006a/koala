package com.epiroc.koala.auth.mapper;

import com.epiroc.koala.auth.api.module.OauthClientDetails;
import com.epiroc.koala.common.core.persistence.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Oauth2客户端mapper
 *
 * @author tangyi
 * @date 2019/3/30 16:39
 */
@Mapper
public interface OauthClientDetailsMapper extends CrudMapper<OauthClientDetails> {

}
