package com.javabase.demo.entity.test;

import com.application.base.utils.common.BaseEntity;


/**
 * TestScore实体
 * @author 孤狼
 */
public class TestScore extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String TABLE_NAME = "test_score";
	
	/**课程的的ID*/
	private String scoreId;
	/**课程的的ID 对应的静态变量值*/
	public static final String FIELD_SCORE_ID = "scoreId";
	/**课程名字*/
	private String name;
	/**课程名字 对应的静态变量值*/
	public static final String FIELD_NAME = "name";
	/**课程得分*/
	private Integer score;
	/**课程得分 对应的静态变量值*/
	public static final String FIELD_SCORE = "score";

	public TestScore () {
		super();
	}
	
	public TestScore (String scoreId ,String name ,Integer score ) {
		super();
		 this.scoreId = scoreId;
		 this.name = name;
		 this.score = score;
	}
	
	public String getScoreId() {
		return scoreId;
	}
	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}
