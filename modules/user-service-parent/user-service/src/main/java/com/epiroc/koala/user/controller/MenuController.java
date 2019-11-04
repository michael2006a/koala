package com.epiroc.koala.user.controller;

import cn.hutool.core.collection.CollUtil;
import com.epiroc.koala.user.api.dto.MenuDto;
import com.epiroc.koala.user.api.module.Menu;
import com.epiroc.koala.user.service.MenuService;
import com.epiroc.koala.user.utils.MenuUtil;
import com.github.pagehelper.PageInfo;
import com.epiroc.koala.common.core.constant.CommonConstant;
import com.epiroc.koala.common.core.model.ResponseBean;
import com.epiroc.koala.common.core.utils.DateUtils;
import com.epiroc.koala.common.core.utils.ExcelToolUtil;
import com.epiroc.koala.common.core.utils.MapUtil;
import com.epiroc.koala.common.core.utils.PageUtil;
import com.epiroc.koala.common.core.utils.Servlets;
import com.epiroc.koala.common.core.utils.SysUtil;
import com.epiroc.koala.common.core.utils.TreeUtil;
import com.epiroc.koala.common.core.web.BaseController;
import com.epiroc.koala.common.log.annotation.Log;
import com.epiroc.koala.common.security.constant.SecurityConstant;
import com.google.common.net.HttpHeaders;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 菜单controller
 *
 * @author tangyi
 * @date 2018/8/26 22:48
 */
@Slf4j
@AllArgsConstructor
@Api("菜单信息管理")
@RestController
@RequestMapping("/v1/menu")
public class MenuController extends BaseController {

  private final MenuService menuService;

  /**
   * 返回当前用户的树形菜单集合
   *
   * @return 当前用户的树形菜单
   */
  @GetMapping(value = "userMenu")
  @ApiOperation(value = "获取当前用户的菜单列表")
  public List<MenuDto> userMenu() {
    return menuService.findUserMenu();
  }

  /**
   * 返回树形菜单集合
   *
   * @return 树形菜单集合
   */
  @GetMapping(value = "menus")
  @ApiOperation(value = "获取树形菜单列表")
  public List<MenuDto> menus() {
    // 查询所有菜单
    Menu condition = new Menu();
    condition.setApplicationCode(SysUtil.getSysCode());
    condition.setTenantCode(SysUtil.getTenantCode());
    Stream<Menu> menuStream = menuService.findAllList(condition).stream();
    if (Optional.ofNullable(menuStream).isPresent()) {
      // 转成MenuDto
      List<MenuDto> menuDtoList = menuStream.map(MenuDto::new).collect(Collectors.toList());
      // 排序、构建树形关系
      return TreeUtil
          .buildTree(CollUtil.sort(menuDtoList, Comparator.comparingInt(MenuDto::getSort)),
              CommonConstant.ROOT);
    }
    return new ArrayList<>();
  }

  /**
   * 新增菜单
   *
   * @param menu menu
   * @return ResponseBean
   * @author tangyi
   * @date 2018/8/27 16:12
   */
  @PostMapping
  @PreAuthorize("hasAuthority('sys:menu:add') or hasAnyRole('" + SecurityConstant.ROLE_ADMIN + "')")
  @ApiOperation(value = "创建菜单", notes = "创建菜单")
  @ApiImplicitParam(name = "menu", value = "角色实体menu", required = true, dataType = "Menu")
  @Log("新增菜单")
  public ResponseBean<Boolean> addMenu(@RequestBody @Valid Menu menu) {
    menu.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
    return new ResponseBean<>(menuService.insert(menu) > 0);
  }

  /**
   * 更新菜单
   *
   * @param menu menu
   * @return ResponseBean
   * @author tangyi
   * @date 2018/10/24 16:34
   */
  @PutMapping
  @PreAuthorize(
      "hasAuthority('sys:menu:edit') or hasAnyRole('" + SecurityConstant.ROLE_ADMIN + "')")
  @ApiOperation(value = "更新菜单信息", notes = "根据菜单id更新菜单的基本信息")
  @ApiImplicitParam(name = "menu", value = "角色实体menu", required = true, dataType = "Menu")
  @Log("更新菜单")
  public ResponseBean<Boolean> updateMenu(@RequestBody @Valid Menu menu) {
    menu.setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
    return new ResponseBean<>(menuService.update(menu) > 0);
  }

  /**
   * 根据id删除
   *
   * @param id id
   * @return ResponseBean
   * @author tangyi
   * @date 2018/8/27 16:19
   */
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('sys:menu:del') or hasAnyRole('" + SecurityConstant.ROLE_ADMIN + "')")
  @ApiOperation(value = "删除菜单", notes = "根据ID删除菜单")
  @ApiImplicitParam(name = "id", value = "菜单ID", required = true, paramType = "path")
  @Log("删除菜单")
  public ResponseBean<Boolean> deleteMenu(@PathVariable Long id) {
    Menu menu = new Menu();
    menu.setId(id);
    return new ResponseBean<>(menuService.delete(menu) > 0);
  }

  /**
   * 根据id查询菜单
   *
   * @return Menu
   * @author tangyi
   * @date 2018/8/27 16:11
   */
  @GetMapping("/{id}")
  @ApiOperation(value = "获取菜单信息", notes = "根据菜单id获取菜单详细信息")
  @ApiImplicitParam(name = "id", value = "菜单ID", required = true, dataType = "Long", paramType = "path")
  public Menu menu(@PathVariable Long id) {
    Menu menu = new Menu();
    menu.setId(id);
    return menuService.get(menu);
  }

  /**
   * 获取菜单分页列表
   *
   * @param pageNum pageNum
   * @param pageSize pageSize
   * @param sort sort
   * @param order order
   * @param menu menu
   * @return PageInfo
   * @author tangyi
   * @date 2018/8/26 23:17
   */
  @GetMapping("menuList")
  @ApiOperation(value = "获取菜单列表")
  @ApiImplicitParams({
      @ApiImplicitParam(name = CommonConstant.PAGE_NUM, value = "分页页码", defaultValue = CommonConstant.PAGE_NUM_DEFAULT, dataType = "String"),
      @ApiImplicitParam(name = CommonConstant.PAGE_SIZE, value = "分页大小", defaultValue = CommonConstant.PAGE_SIZE_DEFAULT, dataType = "String"),
      @ApiImplicitParam(name = CommonConstant.SORT, value = "排序字段", defaultValue = CommonConstant.PAGE_SORT_DEFAULT, dataType = "String"),
      @ApiImplicitParam(name = CommonConstant.ORDER, value = "排序方向", defaultValue = CommonConstant.PAGE_ORDER_DEFAULT, dataType = "String"),
      @ApiImplicitParam(name = "Menu", value = "菜单信息", dataType = "Menu")})
  public PageInfo<Menu> menuList(
      @RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) String pageNum,
      @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) String pageSize,
      @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
      @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
      Menu menu) {
    // 租户标识过滤条件
    menu.setTenantCode(SysUtil.getTenantCode());
    return menuService.findPage(PageUtil.pageInfo(pageNum, pageSize, sort, order), menu);
  }

  /**
   * 根据角色查找菜单
   *
   * @param role 角色标识
   * @param tenantCode 租户标识
   * @return ResponseBean
   * @author tangyi
   * @date 2018/8/27 15:58
   */
  @GetMapping("findMenuByRole/{role}")
  @ApiOperation(value = "根据角色查找菜单", notes = "根据角色id获取角色菜单")
  @ApiImplicitParam(name = "role", value = "角色名称", required = true, dataType = "String", paramType = "path")
  public ResponseBean<List<Menu>> findMenuByRole(@PathVariable String role,
      @RequestParam @NotBlank String tenantCode) {
    return new ResponseBean<>(menuService.findMenuByRole(role, tenantCode));
  }

  /**
   * 查询所有菜单
   *
   * @param tenantCode 租户标识
   * @return ResponseBean
   * @author tangyi
   * @date 2019/04/26 11:50
   */
  @GetMapping("findAllMenu")
  @ApiOperation(value = "查询所有菜单", notes = "查询所有菜单")
  public ResponseBean<List<Menu>> findAllMenu(@RequestParam @NotBlank String tenantCode) {
    Menu menu = new Menu();
    menu.setTenantCode(tenantCode);
    menu.setApplicationCode(SysUtil.getSysCode());
    return new ResponseBean<>(menuService.findAllList(menu));
  }

  /**
   * 根据角色查找菜单
   *
   * @param roleCode 角色code
   * @return 属性集合
   */
  @GetMapping("roleTree/{roleCode}")
  @ApiOperation(value = "根据角色查找菜单", notes = "根据角色code获取角色菜单")
  @ApiImplicitParam(name = "roleCode", value = "角色code", required = true, dataType = "String", paramType = "path")
  public List<String> roleTree(@PathVariable String roleCode) {
    // 根据角色查找菜单
    Stream<Menu> menuStream = menuService.findMenuByRole(roleCode, SysUtil.getTenantCode())
        .stream();
    // 获取菜单ID
    if (Optional.ofNullable(menuStream).isPresent()) {
      return menuStream.map(menu -> menu.getId().toString()).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  /**
   * 导出菜单
   *
   * @param ids ids
   * @author tangyi
   * @date 2018/11/28 12:46
   */
  @PostMapping("export")
  @PreAuthorize(
      "hasAuthority('sys:menu:export') or hasAnyRole('" + SecurityConstant.ROLE_ADMIN + "')")
  @ApiOperation(value = "导出菜单", notes = "根据菜单id导出菜单")
  @ApiImplicitParam(name = "ids", value = "菜单ID", required = true, dataType = "Long")
  @Log("导出菜单")
  public void exportMenu(@RequestBody Long[] ids, HttpServletRequest request,
      HttpServletResponse response) {
    String tenantCode = SysUtil.getTenantCode();
    try {
      // 配置response
      response.setCharacterEncoding("utf-8");
      response.setContentType("multipart/form-data");
      response.setHeader(HttpHeaders.CONTENT_DISPOSITION, Servlets.getDownName(request,
          "菜单信息" + DateUtils.localDateMillisToString(LocalDateTime.now()) + ".xlsx"));
      List<Menu> menus;
      // 导出当前租户下的所有菜单
      if (ArrayUtils.isEmpty(ids)) {
        Menu menu = new Menu();
        menu.setTenantCode(tenantCode);
        menus = menuService.findList(menu);
      } else {    // 导出选中
        menus = menuService.findListById(ids);
      }
      ExcelToolUtil.exportExcel(request.getInputStream(), response.getOutputStream(),
          MapUtil.java2Map(menus),
          MenuUtil.getMenuMap());
    } catch (Exception e) {
      log.error("导出菜单数据失败！", e);
    }
  }

  /**
   * 导入数据
   *
   * @param file file
   * @return ResponseBean
   * @author tangyi
   * @date 2018/11/28 12:51
   */
  @PostMapping("import")
  @PreAuthorize(
      "hasAuthority('sys:menu:import') or hasAnyRole('" + SecurityConstant.ROLE_ADMIN + "')")
  @ApiOperation(value = "导入菜单", notes = "导入菜单")
  @Log("导入菜单")
  public ResponseBean<Boolean> importMenu(
      @ApiParam(value = "要上传的文件", required = true) MultipartFile file) {
    try {
      log.debug("开始导入菜单数据");
      Stream<Menu> menuStream = MapUtil
          .map2Java(Menu.class,
              ExcelToolUtil.importExcel(file.getInputStream(), MenuUtil.getMenuMap()))
          .stream();
      if (Optional.ofNullable(menuStream).isPresent()) {
        menuStream.forEach(menu -> {
          if (menuService.update(menu) < 1) {
            menuService.insert(menu);
          }
        });
      }
      return new ResponseBean<>(Boolean.TRUE);
    } catch (Exception e) {
      log.error("导入菜单数据失败！", e);
    }
    return new ResponseBean<>(Boolean.FALSE);
  }
}