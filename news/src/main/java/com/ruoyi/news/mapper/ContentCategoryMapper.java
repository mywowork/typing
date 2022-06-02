package com.ruoyi.news.mapper;

import com.ruoyi.news.domain.ContentCategory;

import java.util.List;

/**
 * 内容分类Mapper接口
 *
 * @author ruoyi
 * @date 2022-05-31
 */
public interface ContentCategoryMapper
{
    /**
     * 查询内容分类
     *
     * @param id 内容分类主键
     * @return 内容分类
     */
    public ContentCategory selectContentCategoryById(Long id);

    /**
     * 查询内容分类列表
     *
     * @param contentCategory 内容分类
     * @return 内容分类集合
     */
    public List<ContentCategory> selectContentCategoryList(ContentCategory contentCategory);

    /**
     * 新增内容分类
     *
     * @param contentCategory 内容分类
     * @return 结果
     */
    public int insertContentCategory(ContentCategory contentCategory);

    /**
     * 修改内容分类
     *
     * @param contentCategory 内容分类
     * @return 结果
     */
    public int updateContentCategory(ContentCategory contentCategory);

    /**
     * 删除内容分类
     *
     * @param id 内容分类主键
     * @return 结果
     */
    public int deleteContentCategoryById(Long id);

    /**
     * 批量删除内容分类
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContentCategoryByIds(Long[] ids);
}
