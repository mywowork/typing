package com.ruoyi.news.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.news.domain.PageContent;
import com.ruoyi.news.service.IPageContentService;
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
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * html页面Controller
 *
 * @author ruoyi
 * @date 2022-05-31
 */
@RestController
@RequestMapping("/system/content")
public class PageContentController extends BaseController
{
    @Autowired
    private IPageContentService pageContentService;

    /**
     * 查询html页面列表
     */
    @PreAuthorize("@ss.hasPermi('system:content:list')")
    @GetMapping("/list")
    public TableDataInfo list(PageContent pageContent)
    {
        startPage();
        List<PageContent> list = pageContentService.selectPageContentList(pageContent);
        return getDataTable(list);
    }

    @Anonymous
    public PageContent content(Long id){
        return  pageContentService.selectPageContentById(id);
    }
    /**
     * 导出html页面列表
     */
    @PreAuthorize("@ss.hasPermi('system:content:export')")
    @Log(title = "html页面", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PageContent pageContent)
    {
        List<PageContent> list = pageContentService.selectPageContentList(pageContent);
        ExcelUtil<PageContent> util = new ExcelUtil<PageContent>(PageContent.class);
        util.exportExcel(response, list, "html页面数据");
    }

    /**
     * 获取html页面详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:content:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pageContentService.selectPageContentById(id));
    }

    /**
     * 新增html页面
     */
    @PreAuthorize("@ss.hasPermi('system:content:add')")
    @Log(title = "html页面", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PageContent pageContent)
    {
        return toAjax(pageContentService.insertPageContent(pageContent));
    }

    /**
     * 修改html页面
     */
    @PreAuthorize("@ss.hasPermi('system:content:edit')")
    @Log(title = "html页面", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PageContent pageContent)
    {
        return toAjax(pageContentService.updatePageContent(pageContent));
    }

    /**
     * 删除html页面
     */
    @PreAuthorize("@ss.hasPermi('system:content:remove')")
    @Log(title = "html页面", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pageContentService.deletePageContentByIds(ids));
    }
}
