/*
MySQL Backup
Source Server Version: 8.0.26
Source Database: myblog
Date: 2024/5/15 09:01:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '文章标题',
  `author_id` int DEFAULT NULL COMMENT '作者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `content_id` int DEFAULT NULL COMMENT '内容ID',
  `cover_image` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '博客封面图片',
  `category_id` int DEFAULT NULL COMMENT '分类ID',
  `tag_id` int DEFAULT NULL COMMENT '标签ID',
  `weight` int DEFAULT '0' COMMENT '权重',
  `views_count` int DEFAULT NULL COMMENT '浏览数量',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容简介',
  `status_code` int DEFAULT NULL,
  `type` int DEFAULT '0' COMMENT '文章类型',
  `original_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '转载文章  原始地址',
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `article_content`
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `article_tag`
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `tag_id` int NOT NULL,
  `author_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '账号',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '账户密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '头像图片地址',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '简介',
  `creat_time` datetime DEFAULT NULL COMMENT '用户创建时间',
  `last_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电子邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `article` VALUES ('1','看完这篇，别人的开源项目结构应该能看懂了','2081681409','2022-05-07 16:17:31','2022-05-07 16:17:31','1',NULL,'1','1','1','3','近来，和不少初学Spring或Spring Boot的小伙伴私信交流了关于项目目录结构划分和代码分层的问题。\r\n\r\n很多小伙伴表示网上下载下来的开源项目看不懂，项目结构和代码分层看得很蒙，不知道应该以一个什么样的思路去学习和吸收别人的项目。\r\n\r\n好，今天熬夜肝了这篇文章，和大家一起来交流探讨一下，不足之处也请小伙伴们批评指正。','0',NULL,NULL), ('2','解决小程序 app.js中的onLaunch中的数据 在page的onLoade中接收不到的问题','2081681409','2022-05-07 16:10:12','2022-05-07 16:10:12','1',NULL,'1','1','1','0','由于工作的安排，最近在改我们公司的一个小程序，在此期间碰到了一个坑，遇到的问题是，实现登录时，app.js中 onLaunch 实现数据请求拿到后端返回的参数后写入 Storage ，然后在首页 ','0',NULL,NULL), ('3','Java Selenium (爬虫) 控制已打开的浏览器','2081681409','2023-08-03 15:40:16','2023-08-03 15:40:16','1',NULL,'1','1','1','8',NULL,'0',NULL,NULL);
INSERT INTO `article_content` VALUES ('1','<!doctype html>\n<html>\n<head>\n<meta charset=\'UTF-8\'><meta name=\'viewport\' content=\'width=device-width initial-scale=1\'>\n<title>看完这篇，别人的开源项目结构应该能看懂了</title>\n</head>\n<body>\n<blockquote><p>转载于：<a href=\'https://mp.weixin.qq.com/s/5Ar5B9Ah2BdO8i9YjMQ7Qg\' target=\'_blank\' class=\'url\'>https://mp.weixin.qq.com/s/5Ar5B9Ah2BdO8i9YjMQ7Qg</a></p>\n</blockquote>\n<h2 id=\'我为什么要写这篇\'>我为什么要写这篇</h2>\n<p>近来，和不少初学Spring或Spring Boot的小伙伴私信交流了关于项目目录结构划分和代码分层的问题。</p>\n<p>很多小伙伴表示网上下载下来的开源项目看不懂，项目结构和代码分层看得很蒙，不知道应该以一个什么样的思路去学习和吸收别人的项目。</p>\n<p>好，今天熬夜肝了这篇文章，和大家一起来交流探讨一下，不足之处也请小伙伴们批评指正。</p>\n<hr />\n<h2 id=\'先看看阿里是怎么约定的\'>先看看阿里是怎么约定的</h2>\n<p>我印象中，以前在看《阿里巴巴Java开发手册》时，好像有关于工程结构和应用分层相关的内容，于是我回翻了一下，果然有：</p>\n<p><img src=\"http://imag.dragonlucky.cn/image-20220225105149209.png\" referrerpolicy=\"no-referrer\" alt=\"image-20220225105149209\"></p>\n<p>它这里面讲的内容大概就是：关于一个正常的企业项目里一种<strong>通用的项目结构和代码层级划分</strong>的指导意见。</p>\n<p>按这本书上说的，一般分为如下几层：</p>\n<ul>\n<li>开放接口层</li>\n<li>终端显示层</li>\n<li>Web 层</li>\n<li>Service 层</li>\n<li>Manager 层</li>\n<li>DAO 层</li>\n<li>外部接口或第三方平台</li>\n\n</ul>\n<p>由于书中的篇幅关系，它这地方讲得比较笼统了，估计初学者看了还是会懵，所以接下来<strong>结合实际项目代码结构</strong>，来唠一唠具体的项目结构和代码分层。</p>\n<hr />\n<h2 id=\'通常的项目结构\'>通常的项目结构</h2>\n<blockquote><p><strong>首先说在前面的是</strong>：这东西并没有一套通用的标准，不同公司或者团队的使用习惯和规范也不尽相同。</p>\n</blockquote>\n<p>我们就以当下非常火热的Spring Boot典型项目结构为例，创建出来的项目应该总体分为三大层：</p>\n<p><img src=\"http://imag.dragonlucky.cn/image-20220225105259462.png\" referrerpolicy=\"no-referrer\" alt=\"image-20220225105259462\"></p>\n<ul>\n<li><code>项目根目录/src/main/java</code>：放置项目Java源代码</li>\n<li><code>项目根目录/src/main/resources</code>：放置项目静态资源和配置文件</li>\n<li><code>项目根目录/src/test/java</code>：放置项目测试用例代码</li>\n\n</ul>\n<p>而位于<code>/src/main/java</code>目录下的Java源代码的组织结构大家比较关心，这地方也只能给出一个通常典型的结构，毕竟不同项目和团队实践不一样，稍许有区别，但整体安排应该差不多。而且如果是<strong>多模块</strong>的项目的话，下面的结构应该只对应其中一个模块，其他模块的代码组织也大致差不多。</p>\n<p><img src=\"http://imag.dragonlucky.cn/image-20220225105325539.png\" referrerpolicy=\"no-referrer\" alt=\"image-20220225105325539\"></p>\n<p>各个目录详细介绍：</p>\n<pre><code>|_annotation：放置项目自定义注解\n|_aspect：放置切面代码\n|_config：放置配置类\n|_constant：放置常量、枚举等定义\n   |__consist：存放常量定义\n   |__enums：存放枚举定义\n|_controller：放置控制器代码\n|_filter：放置一些过滤、拦截相关的代码\n|_mapper：放置数据访问层代码接口\n|_model：放置数据模型代码\n   |__entity：放置数据库实体对象定义\n   |__dto：存放数据传输对象定义\n   |__vo：存放显示层对象定义\n|_service：放置具体的业务逻辑代码（接口和实现分离）\n   |__intf：存放业务逻辑接口定义\n   |__impl：存放业务逻辑实际实现\n|_utils：放置工具类和辅助代码\n</code></pre>\n<p>然后接下来<code>/src/main/resources</code>目录，里面主要存放静态配置文件和页面静态资源等东西：</p>\n<pre><code class=\'language-yaml\' lang=\'yaml\'>|_mapper：存放mybatis的XML映射文件（如果是mybatis项目）\n|_static：存放网页静态资源，比如下面的js/css/img\n   |__js：\n   |__css：\n   |__img：\n   |__font：\n   |__等等\n|_template：存放网页模板，比如thymeleaf/freemarker模板等\n   |__header\n   |__sidebar\n   |__bottom\n   |__XXX.html等等\n|_application.yml       基本配置文件\n|_application-dev.yml   开发环境配置文件\n|_application-test.yml  测试环境配置文件\n|_application-prod.yml  生产环境配置文件\n</code></pre>\n<p>当然，这地方估计有一个<strong>很多人都会纠结的</strong>关于<code>DTO/VO/DO</code>等<strong>数据模型定义</strong>的区分。</p>\n<p>这在《阿里巴巴Java开发手册》中倒是做了一个所谓的严格区分，那本书上是这样去定义的：</p>\n<ul>\n<li><code>DO（Data Object）</code>：与数据库表结构一一对应，通过DAO层向上传输数据源对象。</li>\n<li><code>DTO（Data Transfer Object）</code>：数据传输对象，Service或Manager向外传输的对象。</li>\n<li><code>BO（Business Object）</code>：业务对象。由Service层输出的封装业务逻辑的对象。</li>\n<li><code>AO（Application Object）</code>：应用对象。在Web层与Service层之间抽象的复用对象模型，极为贴近展示层，复用度不高。</li>\n<li><code>VO（View Object）</code>：显示层对象，通常是Web向模板渲染引擎层传输的对象。</li>\n<li><code>Query</code>：数据查询对象，各层接收上层的查询请求。注意超过2个参数的查询封装，禁止使用Map类来传输。</li>\n\n</ul>\n<p>老实讲，看到这么多对象的定义，我也是很蒙的。实际项目开发时，我觉得没有必要刻意照搬去定义这么多层对象，这样后续做对象转换工作都能烦skr人。</p>\n<p>出于简单起见，我个人觉得，只要保证业务逻辑层<code>Service</code>和数据库<code>DAO</code>层的操作对象严格划分出来，确保互相不渗透，不混用，问题应该就不大。</p>\n<p>比如在我上面举例的这个项目的代码结构中，<code>Service</code>层处理的对象都定义在了<code>dto</code>包里，而<code>DAO</code>层处理的对象都放在了<code>entity</code>包里了。</p>\n<hr />\n<h2 id=\'项目结构划分总结\'>项目结构划分总结</h2>\n<p>如果从一个用户访问一个网站的情况来看，对应着上面的项目代码结构来分析，可以贯穿整个代码分层：</p>\n<p><img src=\"http://imag.dragonlucky.cn/image-20220225105416880.png\" referrerpolicy=\"no-referrer\" alt=\"image-20220225105416880\"></p>\n<p>对应代码目录的流转逻辑就是：</p>\n<p><img src=\"http://imag.dragonlucky.cn/image-20220225105548609.png\" referrerpolicy=\"no-referrer\" alt=\"image-20220225105548609\"></p>\n<p>我想，应该看得比较清楚了吧。</p>\n<p><strong>所以，以后每当我们拿到一个新的项目到手时，只要按照这个思路去看别人项目的代码，应该基本都是能理得顺的</strong>。</p>\n<hr />\n<h2 id=\'一些注意事项\'>一些注意事项</h2>\n<p>1、<code>Contorller</code>层参数传递建议不要使用<code>HashMap</code>，建议使用数据模型定义</p>\n<p>2、<code>Controller</code>层里可以做参数校验、异常抛出等操作，但建议不要放太多业务逻辑，业务逻辑尽量放到<code>Service</code>层代码中去做</p>\n<p>3、<code>Service</code>层做实际业务逻辑，可以按照功能模块做好定义和区分，相互可以调用</p>\n<p>4、功能模块<code>Service</code>之间引用时，建议不要渗透到<code>DAO</code>层（或者<code>mapper</code>层），基于<code>Service</code>层进行调用和复用比较合理</p>\n<p>5、业务逻辑层<code>Service</code>和数据库<code>DAO</code>层的操作对象不要混用。<code>Controller</code>层的数据对象不要直接渗透到<code>DAO</code>层（或者<code>mapper</code>层）；同理数据表实体对象<code>Entity</code>也不要直接传到<code>Controller</code>层进行输出或展示。</p>\n</body>\n</html>'), ('8','啥年份多少发发大发大搜非活动阿富汗ID哈 发的哈哈覅殴打hi佛的还 发货嗲话覅'), ('9','啥年份多少发发大发大搜非活动阿富汗ID哈 发的哈哈覅殴打hi佛的还 发货嗲话覅'), ('10','啥年份多少发发大发大搜非活动阿富汗ID哈 发的哈哈覅殴打hi佛的还 发货嗲话覅'), ('12','saofdisoahfdisahofdsiafhdnsairehuigirjerknefeopksajrfewjfgomklEJGKML'), ('13','saofdisoahfdisahofdsiafhdnsairehuigirjerknefeopksajrfewjfgomklEJGKML');
INSERT INTO `article_tag` VALUES ('1','1','1',NULL), ('2','1','2',NULL), ('3','2','3',NULL), ('4','9','1',NULL), ('5','10','2',NULL), ('6','10','2',NULL);
INSERT INTO `category` VALUES ('1','踩坑');
INSERT INTO `tag` VALUES ('1','Java','2023-08-03 15:03:37',NULL), ('2','Python','2023-08-03 16:46:23',NULL), ('3','Go','2023-08-03 16:46:26',NULL), ('4','Vue','2023-08-03 15:13:51',NULL), ('5','Jquery','2023-08-03 15:14:03',NULL), ('8','工具','2024-04-17 15:43:38','2081681409');
INSERT INTO `user` VALUES ('-1726779390','18855060689',NULL,'3a9a088790e71a30979f1ba245e58bc3','/static/image/avatar.png',NULL,'2022-02-23 21:42:55','2022-03-03 14:05:41',NULL), ('12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL), ('2081681409','13282802580','王成龙','ec81ca20de72ef69a5049e077dd6bea3','/static/image/avatar.png',NULL,'2022-02-21 11:58:56','2024-04-18 10:24:53',NULL), ('1519915628349243393','18855073373','王小龙','ec81ca20de72ef69a5049e077dd6bea3','/static/image/avatar.png','社恐患者，后端开发工程师','2022-04-29 01:44:59','2023-07-31 16:18:55',NULL);
