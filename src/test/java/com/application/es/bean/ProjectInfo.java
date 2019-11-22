package com.application.es.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @NAME: ProjectInfo
 * @DESC: 项目表
 * @USER: 孤狼
 **/
public class ProjectInfo implements Serializable {
	
	/**
	 * 唯一标识
	 */
	private String projId;
	/**
	 *项目名称
	 */
	private String projName;
	/**
	 *所属计划ID
	 */
	private String planId;
	/**
	 *项目编号
	 */
	private String projNum;
	/**
	 *所属主体标识
	 */
	private String mainTag;
	/**
	 *项目概要信息
	 */
	private String projDesc;
	/**
	 *项目开始时间
	 */
	private Date startTime;
	/**
	 *项目截止时间
	 */
	private Date endTime;
	/**
	 *项目负责人
	 */
	private String projMan;
	/**
	 *项目申报人
	 */
	private String applyMan;
	/**
	 *项目申报时间
	 */
	private Date applyDate;
	/**
	 *项目状态
	 */
	private Integer status;
	
	public String getProjId() {
		return projId;
	}
	
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	public String getProjName() {
		return projName;
	}
	
	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	public String getPlanId() {
		return planId;
	}
	
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	public String getProjNum() {
		return projNum;
	}
	
	public void setProjNum(String projNum) {
		this.projNum = projNum;
	}
	
	public String getMainTag() {
		return mainTag;
	}
	
	public void setMainTag(String mainTag) {
		this.mainTag = mainTag;
	}
	
	public String getProjDesc() {
		return projDesc;
	}
	
	public void setProjDesc(String projDesc) {
		this.projDesc = projDesc;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getProjMan() {
		return projMan;
	}
	
	public void setProjMan(String projMan) {
		this.projMan = projMan;
	}
	
	public String getApplyMan() {
		return applyMan;
	}
	
	public void setApplyMan(String applyMan) {
		this.applyMan = applyMan;
	}
	
	public Date getApplyDate() {
		return applyDate;
	}
	
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
}
