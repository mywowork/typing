package com.ruoyi.news.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.news.domain.Group;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * html页面对象 page_content
 *
 * @author ruoyi
 * @date 2022-05-31
 */
public class PageContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    @NotBlank(message = "标题不能为空！",groups = javax.validation.groups.Default.class)
    private String title;

    /** 所有者ID */
    @Excel(name = "所有者ID")
    private Long owner;

    /** 内容组ID */
    @Excel(name = "内容组ID")
    private Long categoryId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 更新时间 */
    private Date updateDate;

    /** 摘要 */
    @Excel(name = "摘要")
    private String resume;

    /** 正文 */
    private String body;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setOwner(Long owner)
    {
        this.owner = owner;
    }

    public Long getOwner()
    {
        return owner;
    }
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Date getCreateDate()
    {
        return createDate;
    }
    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate()
    {
        return updateDate;
    }
    public void setResume(String resume)
    {
        this.resume = resume;
    }

    public String getResume()
    {
        return resume;
    }
    public void setBody(String body)
    {
        this.body = body;
    }

    public String getBody()
    {
        return body;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("owner", getOwner())
            .append("categoryId", getCategoryId())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("resume", getResume())
            .append("body", getBody())
            .toString();
    }
}
