package com.epiroc.koala.user.service;

import com.epiroc.koala.common.core.model.Log;
import com.epiroc.koala.common.core.service.CrudService;
import com.epiroc.koala.user.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
 * 日志service
 *
 * @author tangyi
 * @date 2018/10/31 20:43
 */
@Service
public class LogService extends CrudService<LogMapper, Log> {

}
