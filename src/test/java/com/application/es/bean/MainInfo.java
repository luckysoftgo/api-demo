package com.application.es.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @NAME: MainInfo
 * @DESC: 項目汇总表
 * @USER: 孤狼
 **/
public class MainInfo implements Serializable {
	/**
	 * 唯一标识
	 */
	private String infoId;
	/**
	 * 项目Id
	 */
	private String projId;
	/**
	 *项目名称
	 */
	private String projName;
	/**
	 * 计划id
	 */
	private String planId;
	/**
	 *计划名称
	 */
	private String planName;
	/**
	 * 唯一标识
	 */
	private String guidId;
	/**
	 *指南名称
	 */
	private String guidName;
	/**
	 *指南年度
	 */
	private String guidYear;
	/**
	 * 唯一标识
	 */
	private String applyId;
	/**
	 *申报主体类别
	 */
	private String applyType;
	/**
	 *申报主体名称
	 */
	private String applyMainName;
	/**
	 *申报主体所属区域
	 */
	private String zoneCode;
	/**
	 *行业编码
	 */
	private String businCode;
	/**
	 *行业编码明细
	 */
	private String itemCode;
	/**
	 *注册资本
	 */
	private Integer money;
	/**
	 *社会统一代码
	 */
	private String socialCode;
	/**
	 *评审是否通过
	 */
	private Integer judeStatus;
	/**
	 *立项是否通过
	 */
	private Integer approveStatus ;
	/**
	 *验收是否通过
	 */
	private Integer passStatus;
	/**
	 *已补助金额
	 */
	private Integer alreadyMoney;
	/**
	 *剩余补助金额
	 */
	private Integer lastMoney;
	/**
	 *总补助金额
	 */
	private Integer totalMoney;
	/**
	 *项目成果转化数量
	 */
	private Integer itemCount;
	/**
	 *成果转化收益数
	 */
	private Integer finalCount;
	/**
	 *生成时间
	 */
	private Date createTime;
	
	public String getInfoId() {
		return infoId;
	}
	
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	
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
	
	public String getGuidName() {
		return guidName;
	}
	
	public void setGuidName(String guidName) {
		this.guidName = guidName;
	}
	
	public String getGuidYear() {
		return guidYear;
	}
	
	public void setGuidYear(String guidYear) {
		this.guidYear = guidYear;
	}
	
	public String getApplyId() {
		return applyId;
	}
	
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	public String getApplyType() {
		return applyType;
	}
	
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	public String getApplyMainName() {
		return applyMainName;
	}
	
	public void setApplyMainName(String applyMainName) {
		this.applyMainName = applyMainName;
	}
	
	public String getZoneCode() {
		return zoneCode;
	}
	
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	
	public String getBusinCode() {
		return businCode;
	}
	
	public void setBusinCode(String businCode) {
		this.businCode = businCode;
	}
	
	public String getItemCode() {
		return itemCode;
	}
	
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public Integer getMoney() {
		return money;
	}
	
	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public String getSocialCode() {
		return socialCode;
	}
	
	public void setSocialCode(String socialCode) {
		this.socialCode = socialCode;
	}
	
	public Integer getJudeStatus() {
		return judeStatus;
	}
	
	public void setJudeStatus(Integer judeStatus) {
		this.judeStatus = judeStatus;
	}
	
	public Integer getApproveStatus() {
		return approveStatus;
	}
	
	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}
	
	public Integer getPassStatus() {
		return passStatus;
	}
	
	public void setPassStatus(Integer passStatus) {
		this.passStatus = passStatus;
	}
	
	public Integer getAlreadyMoney() {
		return alreadyMoney;
	}
	
	public void setAlreadyMoney(Integer alreadyMoney) {
		this.alreadyMoney = alreadyMoney;
	}
	
	public Integer getLastMoney() {
		return lastMoney;
	}
	
	public void setLastMoney(Integer lastMoney) {
		this.lastMoney = lastMoney;
	}
	
	public Integer getTotalMoney() {
		return totalMoney;
	}
	
	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	public Integer getItemCount() {
		return itemCount;
	}
	
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	
	public Integer getFinalCount() {
		return finalCount;
	}
	
	public void setFinalCount(Integer finalCount) {
		this.finalCount = finalCount;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
