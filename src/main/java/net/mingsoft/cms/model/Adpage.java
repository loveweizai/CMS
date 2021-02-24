package net.mingsoft.cms.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "轮播图返回结果")
public class Adpage {
	@ApiModelProperty( value = "编号" ,name = "编号")
    private Integer id;
	@ApiModelProperty( value = "名称" ,name = "名称")
    private String name;
	@ApiModelProperty( value = "图片地址" ,name = "图片地址")
    private String url;
	@ApiModelProperty( value = "跳转地址" ,name = "跳转地址")
    private String link;

    private Integer sort;
    
    private Integer showName;

    private Integer status;

    private Date createTime;

    private Date updateTime;
    
    private String searchTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getShowName() {
        return showName;
    }

    public void setShowName(Integer showName) {
        this.showName = showName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

	public String getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}
}