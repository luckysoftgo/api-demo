-- ----------------------------
-- Table structure for pay_account
-- ----------------------------

drop table if exists pay_account;

create table pay_account (
  pay_id int(11) NOT NULL AUTO_INCREMENT COMMENT '支付账号id',
  partner varchar(32) default null comment '支付合作id,商户id，差不多是支付平台的账号或id',
  app_id varchar(32) default null comment '应用id',
  public_key varchar(1204) default null comment '支付平台公钥(签名校验使用)，sign_type只有单一key时public_key与private_key相等，比如sign_type=md5(友店支付除外)的情况',
  private_key varchar(2048) default null comment '应用私钥(生成签名)',
  notify_url varchar(1024) default null comment '异步回调地址',
  return_url varchar(1024) default null comment '同步回调地址',
  seller varchar(256) default null comment '收款账号, 针对支付宝',
  sign_type varchar(16) default null comment '签名类型',
  input_charset varchar(16) default null comment '枚举值，字符编码 utf-8,gbk等等',
  pay_type char(16) default null comment '支付类型,alipay：支付宝，wxpay：微信, youdianpay: 友店微信,此处开发者自定义对应com.egzosn.pay.demo.entity.paytype枚举值',
  msg_type char(8) default null comment '消息类型，text,xml,json',
  keystore_path varchar(256) default null comment '请求证书地址，请使用绝对路径',
  store_password varchar(256) default null comment '证书对应的密码',
  is_test tinyint(1) not null default 0 comment '是否为测试环境',

  create_user varchar(20) DEFAULT '' COMMENT '创建者',
  create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  update_user varchar(20) DEFAULT '' COMMENT '更新者',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

  primary key (pay_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付账户设置表';