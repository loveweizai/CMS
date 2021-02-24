package net.mingsoft.cms.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "公告管理返回结果")
public class Notice {
	@ApiModelProperty( value = "编号" ,name = "编号")
    private Integer id;
	@ApiModelProperty( value = "标题" ,name = "标题")
    private String title;
	@ApiModelProperty( value = "作者",name = "作者")
    private String author;

    private Byte status;

    private Date createTime;

    private Date updateTime;
    @ApiModelProperty( value = "公告次数" ,name = "公告次数")
    private Integer count;
    @ApiModelProperty( value = "来源" ,name = "来源")
    private String comeFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    private String lastUseId;
    @ApiModelProperty( value = "通知类型，0：普通通知；1：社会招聘通知，2：校园招聘通知" ,name = "通知类型")
    private Integer type;
    @ApiModelProperty( value = "内容" ,name = "内容")
    private String content;
    
    private String searchTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom == null ? null : comeFrom.trim();
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getLastUseId() {
        return lastUseId;
    }

    public void setLastUseId(String lastUseId) {
        this.lastUseId = lastUseId == null ? null : lastUseId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}
}