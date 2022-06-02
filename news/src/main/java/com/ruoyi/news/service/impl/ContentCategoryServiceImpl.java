package com.ruoyi.news.service.impl;

import java.util.List;

import com.ruoyi.news.domain.ContentCategory;
import com.ruoyi.news.mapper.ContentCategoryMapper;
import com.ruoyi.news.service.IContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 内容分类Service业务层处理
 *
 * @author ruoyi
 * @date 2022-05-31
 */
@Service
public class ContentCategoryServiceImpl implements IContentCategoryService
{
    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    /**
     * 查询内容分类
     *
     * @param id 内容分类主键
     * @return 内容分类
     */
    @Override
    public ContentCategory selectContentCategoryById(Long id)
    {
        return contentCategoryMapper.selectContentCategoryById(id);
    }

    /**
     * 查询内容分类列表
     *
     * @param contentCategory 内容分类
     * @return 内容分类
     */
    @Override
    public List<ContentCategory> selectContentCategoryList(ContentCategory contentCategory)
    {
        return contentCategoryMapper.selectContentCategoryList(contentCategory);
    }

    /**
     * 新增内容分类
     *
     * @param contentCategory 内容分类
     * @return 结果
     */
    @Override
    public int insertContentCategory(ContentCategory contentCategory)
    {
        return contentCategoryMapper.insertContentCategory(contentCategory);
    }

    /**
     * 修改内容分类
     *
     * @param contentCategory 内容分类
     * @return 结果
     */
    @Override
    public int updateContentCategory(ContentCategory contentCategory)
    {
        return contentCategoryMapper.updateContentCategory(contentCategory);
    }

    /**
     * 批量删除内容分类
     *
     * @param ids 需要删除的内容分类主键
     * @return 结果
     */
    @Override
    public int deleteContentCategoryByIds(Long[] ids)
    {
        return contentCategoryMapper.deleteContentCategoryByIds(ids);
    }

    /**
     * 删除内容分类信息
     *
     * @param id 内容分类主键
     * @return 结果
     */
    @Override
    public int deleteContentCategoryById(Long id)
    {
        return contentCategoryMapper.deleteContentCategoryById(id);
    }
}
