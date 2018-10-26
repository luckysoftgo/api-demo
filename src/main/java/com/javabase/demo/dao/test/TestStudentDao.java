package com.javabase.demo.dao.test;

import com.javabase.demo.entity.test.TestStudent;
import com.application.base.core.datasource.dao.MultiStrutsBaseDao;

/**
 * TestStudent实体
 * @author 孤狼
 */
public interface TestStudentDao extends MultiStrutsBaseDao<TestStudent> {
	
	//可以填写自己需要的 DAO 方法.
	
	//要是查询 VO 对象, 则到 CustomerSQL.xml 文件中去写对应的SQL,并在此 DAO 中进行申明,在 DAOImpl中去实现就好。
	
}
