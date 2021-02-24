package net.mingsoft.cms.model;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * 项目工程管理
 * 
 **/
@SuppressWarnings("serial")
@ApiModel(value = "项目工程管理返回结果")
public class Engineering implements Serializable {

	/****/
	@ApiModelProperty( value = "编号" ,name = "编号")
	private Integer id;

	/**工程名称**/
	@ApiModelProperty( value = "工程名称" ,name = "工程名称")
	private String engineerName;

	/**工程地点**/
	@ApiModelProperty( value = "工程地点" ,name = "工程地点")
	private String location;

	/**所属国家**/
	@ApiModelProperty( value = "所属国家" ,name = "所属国家")
	private String country;

	/**所属省份**/
	@ApiModelProperty( value = "所属省份" ,name = "所属省份")
	private String provinceId;

	/**所属市**/
	@ApiModelProperty( value = "所属市" ,name = "所属市")
	private String cityId;

	/**所属区**/
	@ApiModelProperty( value = "所属区" ,name = "所属区")
	private String areaId;

	/**中标时间**/
	@ApiModelProperty( value = "中标时间" ,name = "中标时间")
	private java.util.Date winningTime;

	/**工程概况**/
	@ApiModelProperty( value = "工程概况" ,name = "工程概况")
	private String survey;

	/**工程时间起**/
	@ApiModelProperty( value = "工程时间起" ,name = "工程时间起")
	private java.util.Date startDate;

	/**工程时间止**/
	@ApiModelProperty( value = "工程时间止" ,name = "工程时间止")
	private java.util.Date endDate;

	/**工程履约单位**/
	@ApiModelProperty( value = "工程履约单位" ,name = "工程履约单位")
	private String unit;

	/**创建时间**/
	@ApiModelProperty( value = "创建时间" ,name = "创建时间")
	private java.util.Date createTime;

	/**最后修改时间**/
	private java.util.Date updateTime;

	/**所属省份**/
	@ApiModelProperty( value = "编号" ,name = "编号")
	private String provinceName;

	/**所属市**/
	@ApiModelProperty( value = "市" ,name = "市")
	private String cityName;

	/**所属区**/
	@ApiModelProperty( value = "区" ,name = "区")
	private String areaName;
	
	@ApiModelProperty( value = "工程编号" ,name = "工程编号")
	private String engineerNo;
	
	@ApiModelProperty( value = "国家编号" ,name = "国家编号")
	private String countryId;
	
	private String lastDate;


	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return this.location;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return this.country;
	}

	public void setProvinceId(String provinceId){
		this.provinceId = provinceId;
	}

	public String getProvinceId(){
		return this.provinceId;
	}

	public void setCityId(String cityId){
		this.cityId = cityId;
	}

	public String getCityId(){
		return this.cityId;
	}

	public void setAreaId(String areaId){
		this.areaId = areaId;
	}

	public String getAreaId(){
		return this.areaId;
	}

	public void setWinningTime(java.util.Date winningTime){
		this.winningTime = winningTime;
	}

	public java.util.Date getWinningTime(){
		return this.winningTime;
	}

	public void setSurvey(String survey){
		this.survey = survey;
	}

	public String getSurvey(){
		return this.survey;
	}

	public void setStartDate(java.util.Date startDate){
		this.startDate = startDate;
	}

	public java.util.Date getStartDate(){
		return this.startDate;
	}

	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}

	public java.util.Date getEndDate(){
		return this.endDate;
	}

	public void setUnit(String unit){
		this.unit = unit;
	}

	public String getUnit(){
		return this.unit;
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

	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getEngineerNo() {
		return engineerNo;
	}

	public void setEngineerNo(String engineerNo) {
		this.engineerNo = engineerNo;
	}

}
