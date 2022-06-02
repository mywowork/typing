package com.ruoyi.news.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 内容分类对象 content_category
 *
 * @author ruoyi
 * @date 2022-05-31
 */
public class ContentCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 分类名 */
    @Excel(name = "分类名")
    private String name;

    /** 内容处理策略 */
    @Excel(name = "内容处理策略")
    private Long handleId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setHandleId(Long handleId)
    {
        this.handleId = handleId;
    }

    public Long getHandleId()
    {
        return handleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("handleId", getHandleId())
            .append("remark", getRemark())
            .toString();
    }
}
