package com.epiroc.koala.exam.controller;

import cn.hutool.core.collection.CollUtil;
import com.epiroc.koala.common.core.constant.CommonConstant;
import com.epiroc.koala.common.core.model.ResponseBean;
import com.epiroc.koala.common.core.utils.SysUtil;
import com.epiroc.koala.common.core.utils.TreeUtil;
import com.epiroc.koala.common.core.web.BaseController;
import com.epiroc.koala.common.log.annotation.Log;
import com.epiroc.koala.common.security.constant.SecurityConstant;
import com.epiroc.koala.exam.api.constants.ExamSubjectConstant;
import com.epiroc.koala.exam.api.dto.SubjectCategoryDto;
import com.epiroc.koala.exam.api.module.SubjectCategory;
import com.epiroc.koala.exam.service.SubjectCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 题目分类controller
 *
 * @author tangyi
 * @date 2018/12/4 21:57
 */
@AllArgsConstructor
@Api("题库分类信息管理")
@RestController
@RequestMapping("/v1/subjectCategory")
public class SubjectCategoryController extends BaseController {

  private final SubjectCategoryService categoryService;

  /**
   * 返回树形分类集合
   *
   * @return List
   * @author tangyi
   * @date 2018/12/04 22:03
   */
  @GetMapping(value = "categories")
  @ApiOperation(value = "获取分类列表")
  public List<SubjectCategoryDto> menus() {
    SubjectCategory subjectCategory = new SubjectCategory();
    subjectCategory.setTenantCode(SysUtil.getTenantCode());
    // 查询所有分类
    List<SubjectCategory> subjectCategoryList = categoryService.findList(subjectCategory);
    if (CollectionUtils.isNotEmpty(subjectCategoryList)) {
      // 转成dto
      List<SubjectCategoryDto> subjectCategorySetTreeList = subjectCategoryList.stream()
          .map(SubjectCategoryDto::new).distinct().collect(Collectors.toList());
      // 排序、组装树形结构
      return TreeUtil.buildTree(CollUtil
              .sort(subjectCategorySetTreeList, Comparator.comparingInt(SubjectCategoryDto::getSort)),
          CommonConstant.ROOT);
    }
    return new ArrayList<>();
  }

  /**
   * 根据ID获取
   *
   * @param id id
   * @return ResponseBean
   * @author tangyi
   * @date 2018/12/04 21:59
   */
  @GetMapping("/{id}")
  @ApiOperation(value = "获取分类信息", notes = "根据分类id获取分类详细信息")
  @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long", paramType = "path")
  public ResponseBean<SubjectCategory> subjectCategory(@PathVariable Long id) {
    SubjectCategory subjectCategory = new SubjectCategory();
    subjectCategory.setId(id);
    return new ResponseBean<>(categoryService.get(subjectCategory));
  }

  /**
   * 新增分类
   *
   * @param subjectCategory subjectCategory
   * @return ResponseBean
   * @author tangyi
   * @date 2018/12/04 22:00
   */
  @PostMapping
  @PreAuthorize(
      "hasAuthority('exam:subject:category:add') or hasAnyRole('" + SecurityConstant.ROLE_ADMIN
          + "')")
  @ApiOperation(value = "创建分类", notes = "创建分类")
  @ApiImplicitParam(name = "subjectCategory", value = "分类实体subjectCategory", required = true, dataType = "SubjectCategory")
  @Log("新增题目分类")
  public ResponseBean<Boolean> addSubjectCategory(
      @RequestBody @Valid SubjectCategory subjectCategory) {
    subjectCategory
        .setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
    subjectCategory.setType(ExamSubjectConstant.PUBLIC_CATEGORY);
    return new ResponseBean<>(categoryService.insert(subjectCategory) > 0);
  }

  /**
   * 更新分类
   *
   * @param subjectCategory subjectCategory
   * @return ResponseBean
   * @author tangyi
   * @date 2018/12/04 22:01
   */
  @PutMapping
  @PreAuthorize(
      "hasAuthority('exam:subject:category:edit') or hasAnyRole('" + SecurityConstant.ROLE_ADMIN
          + "')")
  @ApiOperation(value = "更新分类信息", notes = "根据分类id更新分类的基本信息")
  @ApiImplicitParam(name = "subjectCategory", value = "分类实体subjectCategory", required = true, dataType = "SubjectCategory")
  @Log("更新题目分类")
  public ResponseBean<Boolean> updateSubjectCategory(
      @RequestBody @Valid SubjectCategory subjectCategory) {
    subjectCategory
        .setCommonValue(SysUtil.getUser(), SysUtil.getSysCode(), SysUtil.getTenantCode());
    return new ResponseBean<>(categoryService.update(subjectCategory) > 0);
  }

  /**
   * 根据ID删除
   *
   * @param id id
   * @return ResponseBean
   * @author tangyi
   * @date 2018/12/04 22:02
   */
  @DeleteMapping("/{id}")
  @PreAuthorize(
      "hasAuthority('exam:subject:category:del') or hasAnyRole('" + SecurityConstant.ROLE_ADMIN
          + "')")
  @ApiOperation(value = "删除分类", notes = "根据ID删除分类")
  @ApiImplicitParam(name = "id", value = "分类ID", required = true, paramType = "path")
  @Log("删除题目分类")
  public ResponseBean<Boolean> deleteSubjectCategory(@PathVariable Long id) {
    SubjectCategory subjectCategory = new SubjectCategory();
    subjectCategory.setId(id);
    return new ResponseBean<>(categoryService.delete(subjectCategory) > 0);
  }
}
