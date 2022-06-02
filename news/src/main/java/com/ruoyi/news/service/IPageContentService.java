package com.ruoyi.news.service;

import com.ruoyi.news.domain.PageContent;

import java.util.List;

/**
 * html页面Service接口
 *
 * @author ruoyi
 * @date 2022-05-31
 */
public interface IPageContentService
{
    /**
     * 查询html页面
     *
     * @param id html页面主键
     * @return html页面
     */
    public PageContent selectPageContentById(Long id);

    /**
     * 查询html页面列表
     *
     * @param pageContent html页面
     * @return html页面集合
     */
    public List<PageContent> selectPageContentList(PageContent pageContent);

    /**
     * 新增html页面
     *
     * @param pageContent html页面
     * @return 结果
     */
    public int insertPageContent(PageContent pageContent);

    /**
     * 修改html页面
     *
     * @param pageContent html页面
     * @return 结果
     */
    public int updatePageContent(PageContent pageContent);

    /**
     * 批量删除html页面
     *
     * @param ids 需要删除的html页面主键集合
     * @return 结果
     */
    public int deletePageContentByIds(Long[] ids);

    /**
     * 删除html页面信息
     *
     * @param id html页面主键
     * @return 结果
     */
    public int deletePageContentById(Long id);
}
