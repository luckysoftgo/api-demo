use mysql;

-- ----------------------------
-- sql for create database.
-- ----------------------------
DROP DATABASE if exists test1;
CREATE DATABASE test1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- sql for use database.
-- ----------------------------
use test1;

-- ----------------------------
-- Table structure for test_teacher
-- ----------------------------
DROP table if exists test_teacher;
CREATE TABLE test_teacher (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长的主键',
  disabled tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) NOT NULL DEFAULT '' COMMENT '系统生成的一个随机码',

  info_desc text COMMENT '描述信息',
  create_user varchar(20) DEFAULT '' COMMENT '创建者',
  create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  update_user varchar(20) DEFAULT '' COMMENT '更新者',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',


  name varchar(45) NOT NULL DEFAULT '' COMMENT '老师的名字',
  mobile varchar(20) NOT NULL DEFAULT '' COMMENT '联系方式',
  gender tinyint(1) NOT NULL DEFAULT 1 COMMENT '1男,0女',
  age smallint NOT NULL DEFAULT 0 COMMENT '年龄',
  major varchar(10) NOT NULL DEFAULT '' COMMENT '教授专业',
  score double NOT NULL DEFAULT 0.0 COMMENT '得分',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='老师管理表';

-- ----------------------------
-- Table structure for test_student
-- ----------------------------
DROP table if exists test_student;
CREATE TABLE test_student (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长的主键',
  uuid varchar(45) NOT NULL DEFAULT '' COMMENT '系统生成的一个随机码',

  info_desc text COMMENT '描述信息',
  create_user varchar(20) DEFAULT '' COMMENT '创建者',
  create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  update_user varchar(20) DEFAULT '' COMMENT '更新者',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  name varchar(45) NOT NULL DEFAULT '' COMMENT '学生的名字',
  mobile varchar(20) NOT NULL DEFAULT '' COMMENT '联系方式',
  gender tinyint(1) NOT NULL DEFAULT 1 COMMENT '1男,0女',
  age smallint(9) NOT NULL DEFAULT 0 COMMENT '年龄',
  total_money decimal(5,2) NOT NULL DEFAULT 0.0 COMMENT '总学费',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生管理表';


-- ----------------------------
-- sql for create database.
-- ----------------------------
DROP DATABASE if exists test2;
CREATE DATABASE test2 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- sql for use database.
-- ----------------------------
use test2;

-- ----------------------------
-- Table structure for test_school
-- ----------------------------
DROP table if exists test_school;
CREATE TABLE test_school (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长的主键',
  disabled tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',

  create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  
  name varchar(45) NOT NULL DEFAULT '' COMMENT '学校名字',
  address varchar(100) NOT NULL DEFAULT '' COMMENT '学校地址',
  phone varchar(100) NOT NULL DEFAULT '' COMMENT '联系方式',
  area varchar(10) NOT NULL DEFAULT '' COMMENT '地区',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校管理表';

-- ----------------------------
-- Table structure for test_score
-- ----------------------------
DROP table if exists test_score;
CREATE TABLE test_score (
  score_id varchar(20) NOT NULL COMMENT '课程的的ID',
  uuid varchar(45) NOT NULL DEFAULT '' COMMENT '系统生成的一个随机码',
  create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',

  name varchar(45) NOT NULL DEFAULT '' COMMENT '课程名字',
  score int(10) NOT NULL DEFAULT 0  COMMENT '课程得分',
  PRIMARY KEY (score_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程管理表';

