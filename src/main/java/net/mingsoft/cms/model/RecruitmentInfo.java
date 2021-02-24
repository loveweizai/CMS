package net.mingsoft.cms.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 招聘信息表
 **/
@SuppressWarnings("serial")
@ApiModel(value = "招聘信息返回结果")
public class RecruitmentInfo implements Serializable {

    /****/
	@ApiModelProperty( value = "编号" ,name = "编号")
    private Integer id;

    /**
     * 单位ID
     **/
	@ApiModelProperty( value = "单位ID" ,name = "单位ID")
    private String companyCode;

    /**
     * 招聘职位名称
     **/
	@ApiModelProperty( value = "职位名称" ,name = "职位名称")
    private String position;

    /**
     * 岗位职责
     **/
	@ApiModelProperty( value = "岗位职责" ,name = "岗位职责")
    private String duty;

    /**
     * 入职条件
     **/
	@ApiModelProperty( value = "入职条件" ,name = "入职条件")
    private String conditionText;

    /**
     * 薪资待遇
     **/
	@ApiModelProperty( value = "薪资待遇" ,name = "薪资待遇")
    private String salary;

    /**
     * 招聘人数
     **/
	@ApiModelProperty( value = "招聘人数" ,name = "招聘人数")
    private Integer number;

    /**
     * 发布状态，04：已发布，05：已撤销
     **/
	@ApiModelProperty( value = "发布状态，04：已发布，05：已撤销" ,name = "发布状态")
    private String status;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 最后修改时间
     **/
    private java.util.Date updateTime;
    
    /**
     * 招聘类型
     **/
	@ApiModelProperty( value = "招聘类型 01：校园招聘，02：社会招聘" ,name = "招聘类型")
    private String recruitmentType;
	
	private String recruitmentCode;//唯一标识


    public String getRecruitmentType() {
		return recruitmentType;
	}

	public void setRecruitmentType(String recruitmentType) {
		this.recruitmentType = recruitmentType;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDuty() {
        return this.duty;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return this.salary;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getConditionText() {
        return conditionText;
    }

    public void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

	public String getRecruitmentCode() {
		return recruitmentCode;
	}

	public void setRecruitmentCode(String recruitmentCode) {
		this.recruitmentCode = recruitmentCode;
	}
}
