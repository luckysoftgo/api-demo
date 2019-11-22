package com.application.es.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @NAME: BasicAppInfo
 * @DESC: 申报主体基本表
 * @USER: 孤狼
 **/
public class BasicAppInfo implements Serializable {
	/**
	 * 唯一标识
	 */
	private String applyId;
	/**
	 *社会统一代码
	 */
	private String socialCode;
	/**
	 *申报主体名称
	 */
	private String applyMainName;
	/**
	 *地址
	 */
	private String address;
	/**
	 *经营范围
	 */
	private String busiScope;
	/**
	 *注册日期
	 */
	private Date regTime;
	/**
	 *法定代表人姓名
	 */
	private String legalName;
	/**
	 *法人身份证号
	 */
	private String legalNo;
	/**
	 *行业一级分类
	 */
	private String firstType;
	/**
	 *行业二级分类
	 */
	private String secondType;
	/**
	 *申报主体类别
	 */
	private String applyType;
	/**
	 *注册资本
	 */
	private Integer money;
	/**
	 *创建时间
	 */
	private Date createTime;
	/**
	 *创建人
	 */
	private String creater;
	/**
	 *更新时间
	 */
	private Date updateTime;
	/**
	 *更新人
	 */
	private String updater;
	/**
	 *删除标记
	 */
	private Integer delete;
	/**
	 *备注
	 */
	private String remark;
	
	public String getApplyId() {
		return applyId;
	}
	
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	public String getSocialCode() {
		return socialCode;
	}
	
	public void setSocialCode(String socialCode) {
		this.socialCode = socialCode;
	}
	
	public String getApplyMainName() {
		return applyMainName;
	}
	
	public void setApplyMainName(String applyMainName) {
		this.applyMainName = applyMainName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getBusiScope() {
		return busiScope;
	}
	
	public void setBusiScope(String busiScope) {
		this.busiScope = busiScope;
	}
	
	public Date getRegTime() {
		return regTime;
	}
	
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
	public String getLegalName() {
		return legalName;
	}
	
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	
	public String getLegalNo() {
		return legalNo;
	}
	
	public void setLegalNo(String legalNo) {
		this.legalNo = legalNo;
	}
	
	public String getFirstType() {
		return firstType;
	}
	
	public void setFirstType(String firstType) {
		this.firstType = firstType;
	}
	
	public String getSecondType() {
		return secondType;
	}
	
	public void setSecondType(String secondType) {
		this.secondType = secondType;
	}
	
	public String getApplyType() {
		return applyType;
	}
	
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	public Integer getMoney() {
		return money;
	}
	
	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getCreater() {
		return creater;
	}
	
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getUpdater() {
		return updater;
	}
	
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
	public Integer getDelete() {
		return delete;
	}
	
	public void setDelete(Integer delete) {
		this.delete = delete;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
