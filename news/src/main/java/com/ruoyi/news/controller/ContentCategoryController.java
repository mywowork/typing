package com.ruoyi.news.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.news.domain.ContentCategory;
import com.ruoyi.news.service.IContentCategoryService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 内容分类Controller
 *
 * @author ruoyi
 * @date 2022-05-31
 */
@RestController
@RequestMapping("/system/category")
public class ContentCategoryController extends BaseController
{
    @Autowired
    private IContentCategoryService contentCategoryService;

    /**
     * 查询内容分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContentCategory contentCategory)
    {
        startPage();
        List<ContentCategory> list = contentCategoryService.selectContentCategoryList(contentCategory);

       //org.springframework.validation.ValidationUtils

        return getDataTable(list);
    }

    /**
     * 导出内容分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "内容分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ContentCategory contentCategory)
    {
        List<ContentCategory> list = contentCategoryService.selectContentCategoryList(contentCategory);
        ExcelUtil<ContentCategory> util = new ExcelUtil<ContentCategory>(ContentCategory.class);
        util.exportExcel(response, list, "内容分类数据");
    }

    /**
     * 获取内容分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(contentCategoryService.selectContentCategoryById(id));
    }

    /**
     * 新增内容分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "内容分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ContentCategory contentCategory)
    {
        return toAjax(contentCategoryService.insertContentCategory(contentCategory));
    }

    /**
     * 修改内容分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "内容分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContentCategory contentCategory)
    {
        return toAjax(contentCategoryService.updateContentCategory(contentCategory));
    }

    /**
     * 删除内容分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "内容分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(contentCategoryService.deleteContentCategoryByIds(ids));
    }
}
