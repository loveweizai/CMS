package net.mingsoft.cms.model;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 
 * 公司架构管理
 * 
 **/
@ApiModel(value = "公司架构管理返回结果")
@SuppressWarnings("serial")
public class CompanyStructure implements Serializable {

	/****/
	@ApiModelProperty( value = "编号" ,name = "编号")
	private Integer id;

	/**单位名称**/
	@ApiModelProperty( value = "单位名称" ,name = "单位名称")
	private String name;

	/**单位编码**/
	@ApiModelProperty( value = "单位编码" ,name = "单位编码")
	private String code;

	/**公司地址**/
	@ApiModelProperty( value = "公司地址" ,name = "公司地址")
	private String address;

	/**形象照**/
	@ApiModelProperty( value = "形象照" ,name = "形象照")
	private String imageUrl;

	/**公司视频介绍**/
	@ApiModelProperty( value = "公司视频介绍" ,name = "公司视频介绍")
	private String videoUrl;

	/**公司视频介绍**/
	@ApiModelProperty( value = "移动端公司视频介绍" ,name = "移动端公司视频介绍")
	private String mobileVideoUrl;
	

	/**简介**/
	@ApiModelProperty( value = "简介" ,name = "简介")
	private String introduce;

	/**详情**/
	@ApiModelProperty( value = "详情" ,name = "详情")
	private String detail;

	/**创建时间**/
	@ApiModelProperty( value = "创建时间" ,name = "创建时间")
	private java.util.Date createTime;

	/**最后修改时间**/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;

	private Integer isWeb;//1.是，0.不是

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return this.code;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return this.address;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return this.imageUrl;
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

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return this.detail;
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

	public Integer getIsWeb() {
		return isWeb;
	}

	public void setIsWeb(Integer isWeb) {
		this.isWeb = isWeb;
	}
}
