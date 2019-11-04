package com.epiroc.koala.auth.service;

import com.epiroc.koala.auth.api.module.OauthClientDetails;
import com.epiroc.koala.auth.mapper.OauthClientDetailsMapper;
import com.epiroc.koala.common.core.service.CrudService;
import org.springframework.stereotype.Service;

/**
 * Oauth2客户端Service
 *
 * @author tangyi
 * @date 2019/3/30 16:48
 */
@Service
public class OauthClientDetailsService extends
    CrudService<OauthClientDetailsMapper, OauthClientDetails> {

}
