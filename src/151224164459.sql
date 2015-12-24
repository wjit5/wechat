/*
MySQL Backup
Source Server Version: 5.7.10
Source Database: weixin
Date: 2015/12/24 16:44:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `picture`
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `id` int(11) NOT NULL,
  `picid` varchar(30) NOT NULL,
  `picurl` varchar(100) NOT NULL,
  `picdes` varchar(100) DEFAULT NULL,
  `flag` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `prize`
-- ----------------------------
DROP TABLE IF EXISTS `prize`;
CREATE TABLE `prize` (
  `id` int(11) NOT NULL,
  `pri_id` varchar(50) NOT NULL,
  `pri_name` varchar(50) DEFAULT NULL,
  `pri_amount` int(11) DEFAULT NULL,
  `pri_des` varchar(50) DEFAULT NULL,
  `pri_flag` varchar(50) DEFAULT NULL,
  `pri_price` varchar(50) DEFAULT NULL,
  `pri_value` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `picture` VALUES ('1','1','www.baidu.com','描述','测试');
INSERT INTO `prize` VALUES ('1','1','鼠标垫','100','参与奖','11','100','value'), ('2','2','鼠标','100','一等奖','22','300','value'), ('3','3','数据线','100','三等奖','33','300','value'), ('4','4','奖品4','100','二等奖','33','300','value'), ('5','5','奖品5','100','二等奖5','5','300','value'), ('6','6','奖品6','100','二等奖6','6','300','value'), ('7','7','奖品7','100','二等奖7','7','300','value'), ('8','8','奖品8','100','二等奖8','8','300','value'), ('9','9','奖品9','100','二等奖9','9','300','value'), ('10','10','奖品10','100','二等奖10','10','300','value');
