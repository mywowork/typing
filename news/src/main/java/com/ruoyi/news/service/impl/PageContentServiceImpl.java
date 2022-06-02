package com.ruoyi.news.service.impl;

import java.util.List;

import com.ruoyi.news.domain.PageContent;
import com.ruoyi.news.mapper.PageContentMapper;
import com.ruoyi.news.service.IPageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.groups.Default;

/**
 * html页面Service业务层处理
 *
 * @author ruoyi
 * @date 2022-05-31
 */
@Service
public class PageContentServiceImpl implements IPageContentService
{
    @Autowired
    private PageContentMapper pageContentMapper;

    /**
     * 查询html页面
     *
     * @param id html页面主键
     * @return html页面
     */
    @Override
    public PageContent selectPageContentById(Long id)
    {
        return pageContentMapper.selectPageContentById(id);
    }

    /**
     * 查询html页面列表
     *
     * @param pageContent html页面
     * @return html页面
     */
    @Override
    public List<PageContent> selectPageContentList(PageContent pageContent)
    {
        return pageContentMapper.selectPageContentList(pageContent);
    }

    /**
     * 新增html页面
     *
     * @param pageContent html页面
     * @return 结果
     */
    @Override
    public int insertPageContent(@Validated({Default.class}) PageContent pageContent)
    {
        return pageContentMapper.insertPageContent(pageContent);
    }

    /**
     * 修改html页面
     *
     * @param pageContent html页面
     * @return 结果
     */
    @Override
    public int updatePageContent(PageContent pageContent)
    {
        return pageContentMapper.updatePageContent(pageContent);
    }

    /**
     * 批量删除html页面
     *
     * @param ids 需要删除的html页面主键
     * @return 结果
     */
    @Override
    public int deletePageContentByIds(Long[] ids)
    {
        return pageContentMapper.deletePageContentByIds(ids);
    }

    /**
     * 删除html页面信息
     *
     * @param id html页面主键
     * @return 结果
     */
    @Override
    public int deletePageContentById(Long id)
    {
        return pageContentMapper.deletePageContentById(id);
    }
}
