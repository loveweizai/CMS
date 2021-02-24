package net.mingsoft.cms.model;

import java.io.Serializable;


/**
 * 招聘需求表
 **/
@SuppressWarnings("serial")
public class RecInfo implements Serializable {


    /**
     * 用工单位
     **/
    private String Z0321;

    /**
     * 职位名称
     **/
    private String Z0351;

    /**
     * 招聘人数
     **/
    private Integer Z0315;

    /**
     * 岗位职责
     **/
    private String Z0342;

    /**
     * 录用条件
     **/
    private String Z0363;

    /**
     * 薪资水平
     **/
    private String Z03AD;

    /**
     * 发布状态，04：已发布
     **/
    private String Z0319;
    
    /**
     * 招聘类型，01：校园招聘，02：社会招聘
     **/
    private String Z0336;
    
    private String Z0301;//唯一标识

    public String getZ0321() {
        return Z0321;
    }

    public void setZ0321(String z0321) {
        Z0321 = z0321;
    }

    public String getZ0351() {
        return Z0351;
    }

    public void setZ0351(String z0351) {
        Z0351 = z0351;
    }

    public Integer getZ0315() {
        return Z0315;
    }

    public void setZ0315(Integer z0315) {
        Z0315 = z0315;
    }

    public String getZ0342() {
        return Z0342;
    }

    public void setZ0342(String z0342) {
        Z0342 = z0342;
    }

    public String getZ0363() {
        return Z0363;
    }

    public void setZ0363(String z0363) {
        Z0363 = z0363;
    }

    public String getZ03AD() {
        return Z03AD;
    }

    public void setZ03AD(String z03AD) {
        Z03AD = z03AD;
    }

    public String getZ0319() {
        return Z0319;
    }

    public void setZ0319(String z0319) {
        Z0319 = z0319;
    }

    @Override
    public String toString() {
        return "RecInfo{" +
                "Z0321='" + Z0321 + '\'' +
                ", Z0351='" + Z0351 + '\'' +
                ", Z0315=" + Z0315 +
                ", Z0342='" + Z0342 + '\'' +
                ", Z0363='" + Z0363 + '\'' +
                ", Z03AD='" + Z03AD + '\'' +
                ", Z0319='" + Z0319 + '\'' +
                '}';
    }

	public String getZ0336() {
		return Z0336;
	}

	public void setZ0336(String z0336) {
		Z0336 = z0336;
	}

	public String getZ0301() {
		return Z0301;
	}

	public void setZ0301(String z0301) {
		Z0301 = z0301;
	}
}
