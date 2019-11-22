package com.application.es.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @NAME: GuideFile
 * @DESC: 指南文件表
 * @USER: 孤狼
 **/
public class GuideFile implements Serializable {
	
	/**
	 * 唯一标识
	 */
	private String fileId;
	/**
	 *所属指南ID
	 */
	private String guidId;
	/**
	 *文件路径
	 */
	private String filePath;
	/**
	 *版本号
	 */
	private String version;
	/**
	 *启停状态
	 */
	private Integer status;
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
	
	public String getFileId() {
		return fileId;
	}
	
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	public String getGuidId() {
		return guidId;
	}
	
	public void setGuidId(String guidId) {
		this.guidId = guidId;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
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
}
