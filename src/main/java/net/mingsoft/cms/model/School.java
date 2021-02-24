package net.mingsoft.cms.model;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 
 * 学校表
 * 
 **/
@SuppressWarnings("serial")
public class School implements Serializable {

	/****/
	private Integer id;

	/**名称**/
	private String name;

	/**地点**/
	private String place;

	/**建立时间**/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date setupTime;

	/**创建时间**/
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

	public void setPlace(String place){
		this.place = place;
	}

	public String getPlace(){
		return this.place;
	}

	public void setSetupTime(java.util.Date setupTime){
		this.setupTime = setupTime;
	}

	public java.util.Date getSetupTime(){
		return this.setupTime;
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

}
