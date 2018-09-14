嘉诚财务合同编码管理系统-JCFC
编码规则：JRGFSQ+业务员号+年份后2位+顺序号
编码成都：10位

字段说明
项目名称 xmmc 
合同编码 htbm
业务经理 ywjl
集成编码 jcbm
软件编码 rjbm
工程编码 gcbm
服务编码 fwbm
技术开发 jskf
其它类型 qtlx
优选
合同号

CREATE TABLE `jcfc_contract` (
  `id` varchar(36) NOT NULL COMMENT 'id',
  `sqrq` date NOT NULL COMMENT '申请日期',
  `xxmc` varchar(255) NOT NULL COMMENT '项目名称',
  `htbm` varchar(32) COMMENT '合同编码',
  `ywjl` varchar(10) NOT NULL COMMENT '业务经理',
  `jcje` varchar(32) COMMENT '集成金额',
  `rjje` varchar(32) COMMENT '软件金额',
  `gcje` varchar(32) COMMENT '工程金额',
  `fwje` varchar(32) COMMENT '服务金额',
  `jsje` varchar(32) COMMENT '技术金额',
  `qtje` varchar(32) COMMENT '其它金额',
  `hjje` varchar(32) COMMENT '合计金额',
  `jcbm` varchar(32) COMMENT '集成编码',
  `rjbm` varchar(32) COMMENT '软件编码',
  `gcbm` varchar(32) COMMENT '工程编码',
  `fwbm` varchar(32) COMMENT '服务编码',
  `jsbm` varchar(32) COMMENT '技术编码',
  `qtbm` varchar(32) COMMENT '其它编码',
  `yx` varchar(32) NOT NULL COMMENT '优选',
  `hth` varchar(32) NOT NULL COMMENT '合同号',
  `create_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人登录名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人登录名称',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  `sys_org_code` varchar(50) DEFAULT NULL COMMENT '所属部门',
  `sys_company_code` varchar(50) DEFAULT NULL COMMENT '所属公司',
  `bpm_status` varchar(32) DEFAULT '1' COMMENT '流程状态',
  `delete_flag` int(2) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jcfc_contract_unique` (`hth`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合同号';

问题
1、生成规则
2、输入的是金额？
3、项目号什么意思？

项目 project
编码 code
名称 name
合同 contract
业务 business
经理 manager
集成 integrate
软件 soft
工程 engineering
服务 service
技术 technology
其他 other
优选 Optimization