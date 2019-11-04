package com.epiroc.koala.common.security.core;

import javax.sql.DataSource;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

/**
 * jdbc客户端service
 *
 * @author tangyi
 * @date 2019/3/30 23:30
 */
public class ClientDetailsServiceImpl extends JdbcClientDetailsService {

  public ClientDetailsServiceImpl(DataSource dataSource) {
    super(dataSource);
  }

  /**
   * 重写方法
   *
   * @param clientId clientId
   * @return ClientDetails
   * @author tangyi
   * @date 2019/03/30 23:31
   */
  @Override
  public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
    return super.loadClientByClientId(clientId);
  }
}
