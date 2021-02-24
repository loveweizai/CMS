package net.mingsoft.cms.model;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * 招聘会管理
 **/
@SuppressWarnings("serial")
@ApiModel(value = "招聘会管理返回结果")
public class Recruitment implements Serializable {

    /****/
	@ApiModelProperty( value = "编号" ,name = "编号")
    private Integer id;

    /**
     * 招聘会名称
     **/
	@ApiModelProperty( value = "名称" ,name = "名称")
    private String name;

    /**
     * 时间
     **/
	@ApiModelProperty( value = "时间" ,name = "时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date startDate;

    /**
     * 地点
     **/
	@ApiModelProperty( value = "地点" ,name = "地点")
    private String location;

    /**
     * 状态，0：未发布，1：已发布，2：已撤销
     **/
    private String status;

    /**
     * 标题图片
     **/
    @ApiModelProperty( value = "标题图片" ,name = "标题图片")
    private String pictureUrl;

    /**
     * 介绍
     **/
    @ApiModelProperty( value = "介绍" ,name = "介绍")
    private String introduce;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 最后修改时间
     **/
    private java.util.Date updateTime;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getStartDate() {
        return this.startDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIntroduce() {
        return this.introduce;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
