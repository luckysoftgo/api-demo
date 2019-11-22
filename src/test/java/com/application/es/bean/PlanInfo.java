package com.application.es.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @NAME: PlanInfo
 * @DESC: 计划表
 * @USER: 孤狼
 **/
public class PlanInfo implements Serializable {
	
	/**
	 * 唯一标识
	 */
	private String planId;
	/**
	 *计划名称
	 */
	private String planName;
	/**
	 *所属指南ID
	 */
	private String guidId;
	/**
	 *pid
	 */
	private String pId;
	/**
	 *简介
	 */
	private String desc;
	/**
	 *创建科室
	 */
	private String createSect;
	/**
	 *创建人
	 */
	private String createMan;
	/**
	 *创建时间
	 */
	private Date createTime;
	/**
	 *是否叶子节点
	 */
	private Integer leaf;
	
	public String getPlanId() {
		return planId;
	}
	
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	public String getPlanName() {
		return planName;
	}
	
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	public String getGuidId() {
		return guidId;
	}
	
	public void setGuidId(String guidId) {
		this.guidId = guidId;
	}
	
	public String getpId() {
		return pId;
	}
	
	public void setpId(String pId) {
		this.pId = pId;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getCreateSect() {
		return createSect;
	}
	
	public void setCreateSect(String createSect) {
		this.createSect = createSect;
	}
	
	public String getCreateMan() {
		return createMan;
	}
	
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getLeaf() {
		return leaf;
	}
	
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}
}
