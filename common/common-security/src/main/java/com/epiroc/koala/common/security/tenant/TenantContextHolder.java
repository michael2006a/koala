package com.epiroc.koala.common.security.tenant;

/**
 * ThreadLocal保存租户code
 *
 * @author tangyi
 * @date 2019/5/28 20:54
 */
public class TenantContextHolder {

  private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

  public static String getTenantCode() {
    return CONTEXT.get();
  }

  public static void setTenantCode(String tenantCode) {
    CONTEXT.set(tenantCode);
  }

  public static void clear() {
    CONTEXT.remove();
  }
}
