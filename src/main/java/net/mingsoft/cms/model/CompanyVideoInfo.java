package net.mingsoft.cms.model;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 公司架构管理
 * 
 **/
@ApiModel(value = "公司视频管理返回结果")
@SuppressWarnings("serial")
public class CompanyVideoInfo implements Serializable {

	/****/
	@ApiModelProperty( value = "编号" ,name = "编号")
	private Integer id;

	/**视频名称**/
	@ApiModelProperty( value = "视频名称" ,name = "视频名称")
	private String videoName;

	/**单位编码**/
	@ApiModelProperty( value = "单位编码" ,name = "单位编码")
	private String companyCode;

	/**公司视频介绍**/
	@ApiModelProperty( value = "公司视频介绍" ,name = "公司视频介绍")
	private String videoUrl;

	/**公司视频介绍**/
	@ApiModelProperty( value = "移动端公司视频介绍" ,name = "移动端公司视频介绍")
	private String mobileVideoUrl;
	

	/**简介**/
	@ApiModelProperty( value = "简介" ,name = "简介")
	private String introduce;

	/**创建时间**/
	@ApiModelProperty( value = "创建时间" ,name = "创建时间")
	private java.util.Date createTime;

	/**最后修改时间**/
	private java.util.Date updateTime;

	/**单位名称**/
	private String companyName;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setVideoName(String videoName){
		this.videoName = videoName;
	}

	public String getVideoName(){
		return this.videoName;
	}

	public void setCompanyCode(String companyCode){
		this.companyCode = companyCode;
	}

	public String getCompanyCode(){
		return this.companyCode;
	}


	public void setVideoUrl(String videoUrl){
		this.videoUrl = videoUrl;
	}

	public String getVideoUrl(){
		return this.videoUrl;
	}

	public void setIntroduce(String introduce){
		this.introduce = introduce;
	}

	public String getIntroduce(){
		return this.introduce;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public String getMobileVideoUrl() {
		return mobileVideoUrl;
	}

	public void setMobileVideoUrl(String mobileVideoUrl) {
		this.mobileVideoUrl = mobileVideoUrl;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
