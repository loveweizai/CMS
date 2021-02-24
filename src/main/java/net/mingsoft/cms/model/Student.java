package net.mingsoft.cms.model;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 
 * 学生表
 * 
 **/
@SuppressWarnings("serial")
public class Student implements Serializable {

	/****/
	private Integer id;

	/**姓名**/
	private String name;

	/**年龄**/
	private Integer age;

	/**性别**/
	private String sex;

	/**头像**/
	private String portrait;

	/**学校Id**/
	private String schoolId;

	/**学校名称**/
	private String schoolName;

	/**专业**/
	private String major;

	/**职位**/
	private String position;

	/**心得**/
	private String motto;

	/**简介**/
	private String introduction;

	/**创建时间**/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;

	/**最后修改时间**/
	private java.util.Date updateTime;



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

	public void setAge(Integer age){
		this.age = age;
	}

	public Integer getAge(){
		return this.age;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

	public String getSex(){
		return this.sex;
	}

	public void setPortrait(String portrait){
		this.portrait = portrait;
	}

	public String getPortrait(){
		return this.portrait;
	}

	public void setMajor(String major){
		this.major = major;
	}

	public String getMajor(){
		return this.major;
	}

	public void setPosition(String position){
		this.position = position;
	}

	public String getPosition(){
		return this.position;
	}

	public void setMotto(String motto){
		this.motto = motto;
	}

	public String getMotto(){
		return this.motto;
	}

	public void setIntroduction(String introduction){
		this.introduction = introduction;
	}

	public String getIntroduction(){
		return this.introduction;
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

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
