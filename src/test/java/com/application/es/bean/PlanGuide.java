package com.application.es.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @NAME: PlanGuide
 * @DESC: 计划指南表
 * @USER: 孤狼
 **/
public class PlanGuide implements Serializable {
	/**
	 * 唯一标识
	 */
	private String guidId;
	/**
	 *指南名称
	 */
	private String guidName;
	/**
	 *指南简介
	 */
	private String guidDesc="2019年度西安市科技计划项目申报指南";
	/**
	 *指南年度
	 */
	private String guidYear;
	/**
	 *计划类别数量
	 */
	private Integer typeCount;
	/**
	 *计划项目数量
	 */
	private Integer itemCount;
	/**
	 *发布科室
	 */
	private String publishSect;
	/**
	 *发布人
	 */
	private String publishMan;
	/**
	 *发布时间
	 */
	private Date publishTime;
	
	public String getGuidId() {
		return guidId;
	}
	
	public void setGuidId(String guidId) {
		this.guidId = guidId;
	}
	
	public String getGuidName() {
		return guidName;
	}
	
	public void setGuidName(String guidName) {
		this.guidName = guidName;
	}
	
	public String getGuidDesc() {
		return guidDesc;
	}
	
	public void setGuidDesc(String guidDesc) {
		this.guidDesc = guidDesc;
	}
	
	public String getGuidYear() {
		return guidYear;
	}
	
	public void setGuidYear(String guidYear) {
		this.guidYear = guidYear;
	}
	
	public Integer getTypeCount() {
		return typeCount;
	}
	
	public void setTypeCount(Integer typeCount) {
		this.typeCount = typeCount;
	}
	
	public Integer getItemCount() {
		return itemCount;
	}
	
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	
	public String getPublishSect() {
		return publishSect;
	}
	
	public void setPublishSect(String publishSect) {
		this.publishSect = publishSect;
	}
	
	public String getPublishMan() {
		return publishMan;
	}
	
	public void setPublishMan(String publishMan) {
		this.publishMan = publishMan;
	}
	
	public Date getPublishTime() {
		return publishTime;
	}
	
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
}
