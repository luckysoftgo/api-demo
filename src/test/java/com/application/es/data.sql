
DROP table if exists level_statistics_data;
CREATE TABLE level_statistics_data (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长的主键',
  disabled int(10) NOT NULL DEFAULT 0 COMMENT '删除标志:1删除,0正常使用',

  projectId varchar(50) NOT NULL DEFAULT '' COMMENT '项目ID',
  projectName varchar(50) NOT NULL DEFAULT '' COMMENT '项目名称',
  planCategory varchar(50) NOT NULL DEFAULT '' COMMENT '计划类别',
  planCategoryName varchar(50) NOT NULL DEFAULT '' COMMENT '计划类别名称',
  planItemId varchar(50) NOT NULL DEFAULT '' COMMENT '计划项目ID',
  planItemName varchar(50) NOT NULL DEFAULT '' COMMENT '计划项目名称',
  guideId varchar(50) NOT NULL DEFAULT '' COMMENT '所属指南ID',
  guideName varchar(50) NOT NULL DEFAULT '' COMMENT '所属指南名称',
  guideYear int(10) NOT NULL DEFAULT 0 COMMENT '所属指南年度',
  declaratSubId varchar(50) NOT NULL DEFAULT '' COMMENT '申报主体ID',
  declaratSubType varchar(50) NOT NULL DEFAULT '' COMMENT '申报主体类别',
  declaratSubName varchar(50) NOT NULL DEFAULT '' COMMENT '申报主体名称',
  declaratSubArea varchar(50) NOT NULL DEFAULT '' COMMENT '申报主体所属区域',
  industCode int(10) NOT NULL DEFAULT 0 COMMENT '行业编码',
  industCategory varchar(50) NOT NULL DEFAULT '' COMMENT '行业大类',
  registerDate dateTime NOT NULL DEFAULT now() COMMENT '注册日期',
  registerYears int(10) NOT NULL DEFAULT 0 COMMENT '注册年限',
  registCapital double(10,2) NOT NULL DEFAULT 0.0 COMMENT '注册资本额',
  reviewTag varchar(50) NOT NULL DEFAULT '' COMMENT '评审是否通过',
  reviewNum int(10) NOT NULL DEFAULT 0 COMMENT '评审是否通过',
  approvedTag varchar(50) NOT NULL DEFAULT '' COMMENT '立项是否通过',
  approvedNum int(10)  NOT NULL DEFAULT 0 COMMENT '立项是否通过',
  passedTag varchar(50) NOT NULL DEFAULT '' COMMENT '验收是否通过',
  passedNum int(10) NOT NULL DEFAULT 0 COMMENT '验收是否通过',
  subsidiedAmt double(10,2) NOT NULL DEFAULT 0.0 COMMENT '已补助金额(万元)',
  allowanceAmt double(10,2) NOT NULL DEFAULT 0.0 COMMENT '剩余补助金额',
  totalAmt double(10,2) NOT NULL DEFAULT 0.0 COMMENT '总补助金额',
  projectConvertCount int(10) NOT NULL DEFAULT 0 COMMENT '项目成果转化数量',
  resultConvertAmt int(10) NOT NULL DEFAULT 0 COMMENT '成果转化收益(万元)',

  create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='综合展示表信息';


-- 查詢列.
SELECT GROUP_CONCAT(COLUMN_NAME SEPARATOR ",") FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'cboard' AND TABLE_NAME = 'level_statistics_data';


-- 1.level_collect_data
select a.guideName as guideName,SUM(a.resultConvertAmt) resultConvertAmt ,SUM(a.totalAmt) totalAmt ,SUM(a.projectConvertCount) projectConvertCount FROM cboard.level_statistics_data a GROUP BY a.guideName ;

--2.
select SUM(a.resultConvertAmt) resultConvertAmt ,SUM(a.totalAmt) totalAmt ,SUM(a.projectConvertCount) projectConvertCount FROM ( select a.guideName as guideName,SUM(a.resultConvertAmt) resultConvertAmt ,SUM(a.totalAmt) totalAmt ,SUM(a.projectConvertCount) projectConvertCount FROM cboard.level_statistics_data a GROUP BY a.guideName ) a ;

--3.level_type_data
select a.declaratSubType,a.guideName, COUNT(a.declaratSubName) as subNameCount FROM cboard.level_statistics_data a GROUP BY a.declaratSubType,a.guideName ;

-- 4.
select a.declaratSubType,a.planCategoryName, COUNT(a.declaratSubName) as subNameCount FROM cboard.level_statistics_data a GROUP BY a.declaratSubType,a.planCategoryName ;

-- 5
CREATE TABLE level_guide_data AS
SELECT b.guideName, b.enterprise,a.space,c.school
FROM (SELECT guideName,sum(1) enterprise FROM level_statistics_data  WHERE declaratSubType='企业'  GROUP BY guideName ) b
LEFT JOIN (SELECT guideName, sum(1) space FROM level_statistics_data  WHERE declaratSubType='研究所'  GROUP BY guideName) a
on b.guideName =a.guideName
LEFT JOIN (SELECT guideName, sum(1) school FROM level_statistics_data  WHERE declaratSubType='高等院校'  GROUP BY guideName) c
on b.guideName =c.guideName

-- 6
CREATE TABLE level_category_data AS
SELECT b.planCategoryName, b.enterprise,a.space,c.school
FROM (SELECT planCategoryName,sum(1) enterprise FROM level_statistics_data  WHERE declaratSubType='企业'  GROUP BY planCategoryName ) b
LEFT JOIN (SELECT planCategoryName, sum(1) space FROM level_statistics_data  WHERE declaratSubType='研究所'  GROUP BY planCategoryName) a
on b.planCategoryName =a.planCategoryName
LEFT JOIN (SELECT planCategoryName, sum(1) school FROM level_statistics_data  WHERE declaratSubType='高等院校'  GROUP BY planCategoryName) c
on b.planCategoryName =c.planCategoryName

-- 7
CREATE TABLE level_planItem_data AS
SELECT b.planItemName, b.enterprise,a.space,c.school
FROM (SELECT planItemName,sum(1) enterprise FROM level_statistics_data  WHERE declaratSubType='企业'  GROUP BY planItemName ) b
LEFT JOIN (SELECT planItemName, sum(1) space FROM level_statistics_data  WHERE declaratSubType='研究所'  GROUP BY planItemName) a
on b.planItemName =a.planItemName
LEFT JOIN (SELECT planItemName, sum(1) school FROM level_statistics_data  WHERE declaratSubType='高等院校'  GROUP BY planItemName) c
on b.planItemName =c.planItemName

-- 8
CREATE TABLE level_planItemJedge_data AS
SELECT b.planItemName,d.planCount, b.reviewNum,a.approvedNum,c.passedNum
FROM (SELECT planItemName,sum(reviewNum) reviewNum FROM level_statistics_data  WHERE reviewNum='1'  GROUP BY planItemName ) b
LEFT JOIN (SELECT planItemName, sum(approvedNum) approvedNum FROM level_statistics_data  WHERE approvedNum='1'  GROUP BY planItemName) a
on b.planItemName =a.planItemName
LEFT JOIN (SELECT planItemName, sum(passedNum) passedNum FROM level_statistics_data  WHERE passedNum='1'  GROUP BY planItemName) c
on b.planItemName =c.planItemName
LEFT JOIN (select COUNT(planItemName) planCount,planItemName FROM level_statistics_data GROUP BY planItemName ) d
on b.planItemName =d.planItemName

-- 9
CREATE TABLE level_categorycount_data AS
select t.industCategory,count(t.subsidiedAmt) subsidiedAmt,count(t.allowanceAmt) allowanceAmt,count(t.totalAmt) totalAmt,count(t.projectConvertCount) projectConvertCount,count(t.resultConvertAmt) resultConvertAmt from level_statistics_data t GROUP BY t.industCategory ;
CREATE TABLE level_categoryamt_data AS
select t.industCategory,sum(t.subsidiedAmt) subsidiedAmt,sum(t.allowanceAmt) allowanceAmt,sum(t.totalAmt) totalAmt,sum(t.projectConvertCount) projectConvertCount,sum(t.resultConvertAmt) resultConvertAmt from level_statistics_data t GROUP BY t.industCategory ;




















