-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 47.106.83.27    Database: sping_cloud_weibo
-- ------------------------------------------------------
-- Server version	5.7.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `focus_group`
--

DROP TABLE IF EXISTS `focus_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `focus_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `craete_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uid` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `focus_group_uid_idx` (`uid`),
  CONSTRAINT `focus_group_uid` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='关注分组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `focus_group`
--

LOCK TABLES `focus_group` WRITE;
/*!40000 ALTER TABLE `focus_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `focus_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `focus_user`
--

DROP TABLE IF EXISTS `focus_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `focus_user` (
  `uid` varchar(20) NOT NULL,
  `focus_uid` varchar(20) NOT NULL,
  `focus_group_id` int(11) NOT NULL,
  KEY `focus_user_focus_group_idx` (`focus_group_id`),
  KEY `focus_uid_idx` (`uid`),
  KEY `focus_user_focus_uid_idx` (`focus_uid`),
  CONSTRAINT `focus_uid` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `focus_user_focus_group` FOREIGN KEY (`focus_group_id`) REFERENCES `focus_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `focus_user_focus_uid` FOREIGN KEY (`focus_uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注的人表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `focus_user`
--

LOCK TABLES `focus_user` WRITE;
/*!40000 ALTER TABLE `focus_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `focus_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `head_img`
--

DROP TABLE IF EXISTS `head_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `head_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `url` varchar(150) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_current_head` varchar(1) NOT NULL,
  `is_del` varchar(1) NOT NULL DEFAULT 'N',
  `uid` varchar(20) NOT NULL,
  `album_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url_UNIQUE` (`url`),
  KEY `head_img_uid_idx` (`uid`),
  KEY `head_img_album_id_idx` (`album_id`),
  CONSTRAINT `head_img_album_id` FOREIGN KEY (`album_id`) REFERENCES `photo_album` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `head_img_uid` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `head_img`
--

LOCK TABLES `head_img` WRITE;
/*!40000 ALTER TABLE `head_img` DISABLE KEYS */;
INSERT INTO `head_img` VALUES (4,'我萌吗？','/r/eadb2ec4-16e7-450a-a6b8-14667b9184ca/picture/909d107a-c0e9-4712-b9d6-c447691c1910.jpg','2018-07-10 10:33:05','Y','N','111',16);
/*!40000 ALTER TABLE `head_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hot_list`
--

DROP TABLE IF EXISTS `hot_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hot_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hot_value` int(11) NOT NULL DEFAULT '0',
  `msg_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `message_id_UNIQUE` (`msg_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `hot_list_msgId` FOREIGN KEY (`msg_id`) REFERENCES `message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hot_list`
--

LOCK TABLES `hot_list` WRITE;
/*!40000 ALTER TABLE `hot_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `hot_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localtion`
--

DROP TABLE IF EXISTS `localtion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localtion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `address_UNIQUE` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='所在地';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localtion`
--

LOCK TABLES `localtion` WRITE;
/*!40000 ALTER TABLE `localtion` DISABLE KEYS */;
INSERT INTO `localtion` VALUES (3,'上海市'),(23,'云南省'),(28,'内蒙古自治区'),(1,'北京市'),(27,'台湾省'),(8,'吉林省'),(21,'四川省'),(2,'天津市'),(31,'宁夏回族自治区'),(12,'安徽省'),(15,'山东省'),(6,'山西省'),(19,'广东省'),(29,'广西壮族自治区'),(32,'新疆维吾尔自治区'),(10,'江苏省'),(14,'江西省'),(5,'河北省'),(16,'河南省'),(11,'浙江省'),(20,'海南省'),(17,'湖北省'),(18,'湖南省'),(34,'澳门特别行政区'),(25,'甘肃省'),(13,'福建省'),(30,'西藏自治区'),(22,'贵州省'),(7,'辽宁省'),(4,'重庆市'),(24,'陕西省'),(26,'青海省'),(33,'香港特别行政区'),(9,'黑龙江省');
/*!40000 ALTER TABLE `localtion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(80) DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `content` text NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `forwarding_count` int(11) NOT NULL DEFAULT '0',
  `comments_count` int(11) NOT NULL DEFAULT '0',
  `likes_count` int(11) NOT NULL DEFAULT '0',
  `isDel` varchar(1) DEFAULT 'N',
  `isPublic` varchar(1) DEFAULT 'Y',
  `uid` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `msg_uid_idx` (`uid`),
  KEY `msg_topicID_idx` (`topic_id`),
  CONSTRAINT `msg_topicID` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `msg_uid` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='微博表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (19,'222',NULL,'111的非领导和过得好功夫的欧服活动共发多个偶读工会经费里的广佛我韩国佛的广佛韩国佛的hi个佛规或负个佛广佛是国非共和国佛得更好成佛女国或房东佛得更好福东否定和佛得更好夫定黑风洞fog分DOI挂号费东莞发给房东官方DOI规划佛我挂号费GV恢复复合弓发给奋斗过后岛国对方过后岛国活动覆盖活动费过佛我官方的GIF大概花费多','2018-07-10 10:35:17',0,0,0,'N','Y','111'),(20,'test',NULL,'sds fg岛国跟换 就很快距离距离图佛恢复的施工符合规范化跟换佛恢复恢复还是符合三个复活币规范化个和规范化发给跟换算法跟换岛国都是给对方重复下功夫传递给房东恢复鸿博股份红南京很快命苦已经根据南方姑娘返回案发后任务的岛国丰东股份的话不给发货不过库军副国日复合肥打个比方','2018-07-10 12:10:09',0,0,0,'N','Y','111'),(21,'似懂非懂',NULL,'856356','2018-07-10 12:13:36',0,0,0,'N','Y','111'),(22,'发给',NULL,'班级 吃个饭更符合规范合格后跟换算法化工股份和发给购房计划跟换','2018-07-10 12:18:13',0,0,0,'N','Y','111'),(23,'哈哈',NULL,'地产股吃饭','2018-07-10 12:19:44',0,0,0,'N','Y','111'),(24,'53453',NULL,'543543','2018-07-10 12:25:43',0,0,0,'N','Y','111'),(25,'工具',NULL,'上大学法国队','2018-07-10 12:32:12',0,0,0,'N','Y','111'),(26,'更符合规范化',NULL,'的flighjfdolijhofdfg付款抛光翡翠价格反对票回家 法国红酒购房计划购房计划返回','2018-07-10 14:37:47',0,0,0,'N','Y','111'),(27,'VBGV你',NULL,'非共和国','2018-07-10 14:39:08',0,0,0,'N','Y','111'),(28,'VBGV你',NULL,'非共和国','2018-07-10 14:39:38',0,0,1,'N','Y','111'),(29,'5635',NULL,'相册GV发红包购房计划一个科目房管局还有顾客弗敢加也化工库好几款复合弓扩扩','2018-07-10 15:35:16',0,0,0,'N','Y','111'),(30,'多个风格',NULL,'的十分投入的股份','2018-07-10 15:38:24',0,0,0,'N','Y','111');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_collection`
--

DROP TABLE IF EXISTS `message_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_collection` (
  `uid` varchar(20) NOT NULL,
  `msg_id` int(11) NOT NULL,
  `collection_status` varchar(1) NOT NULL DEFAULT 'Y',
  KEY `coll_messid_idx` (`msg_id`),
  KEY `coll_uid_idx` (`uid`),
  CONSTRAINT `coll_messid` FOREIGN KEY (`msg_id`) REFERENCES `message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `coll_uid` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的收藏夹';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_collection`
--

LOCK TABLES `message_collection` WRITE;
/*!40000 ALTER TABLE `message_collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_comments`
--

DROP TABLE IF EXISTS `message_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(80) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uid` varchar(20) NOT NULL,
  `msg_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `coomem_messid_idx` (`msg_id`),
  KEY `coomem_uid_idx` (`uid`),
  CONSTRAINT `coomem_messid` FOREIGN KEY (`msg_id`) REFERENCES `message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `coomem_uid` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='微博消息评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_comments`
--

LOCK TABLES `message_comments` WRITE;
/*!40000 ALTER TABLE `message_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_comments` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER tri_insert_message_comments
	AFTER INSERT ON message_comments
	FOR EACH ROW
BEGIN
     update message
		set message.comments_count = message.comments_count + 1
		where message.id = new.msg_id ;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `message_forwarding`
--

DROP TABLE IF EXISTS `message_forwarding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_forwarding` (
  `uid` varchar(20) NOT NULL,
  `msg_id` int(11) NOT NULL,
  KEY `forwarding_message_idx` (`msg_id`),
  KEY `forwarding_user_idx` (`uid`),
  CONSTRAINT `forwarding_message` FOREIGN KEY (`msg_id`) REFERENCES `message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `forwarding_user` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微博消息转发';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_forwarding`
--

LOCK TABLES `message_forwarding` WRITE;
/*!40000 ALTER TABLE `message_forwarding` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_forwarding` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER tri_insert_message_forwarding
	AFTER INSERT ON message_forwarding
	FOR EACH ROW
BEGIN
     update message
		set message.forwarding_count = message.forwarding_count + 1
		where message.id = new.msg_id ;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `message_like`
--

DROP TABLE IF EXISTS `message_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_like` (
  `uid` varchar(20) NOT NULL,
  `msg_id` int(11) NOT NULL,
  `like_status` varchar(1) NOT NULL DEFAULT 'Y',
  KEY `like_msg_idx` (`msg_id`),
  KEY `like_uid_idx` (`uid`),
  CONSTRAINT `like_msgid` FOREIGN KEY (`msg_id`) REFERENCES `message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `like_uid` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微博点赞';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_like`
--

LOCK TABLES `message_like` WRITE;
/*!40000 ALTER TABLE `message_like` DISABLE KEYS */;
INSERT INTO `message_like` VALUES ('111',28,'Y');
/*!40000 ALTER TABLE `message_like` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER tri_insert_message_like
	AFTER INSERT ON message_like
	FOR EACH ROW
BEGIN
     update message
		set message.likes_count = message.likes_count + 1
		where message.id = new.msg_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `photo_album`
--

DROP TABLE IF EXISTS `photo_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photo_album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `craete_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_del` varchar(1) DEFAULT 'N',
  `uid` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PA_USER_idx` (`uid`),
  CONSTRAINT `PA_USER` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='相册';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo_album`
--

LOCK TABLES `photo_album` WRITE;
/*!40000 ALTER TABLE `photo_album` DISABLE KEYS */;
INSERT INTO `photo_album` VALUES (16,'头像专辑','2018-07-10 10:33:05','N','111'),(17,'微博相册','2018-07-10 10:33:05','N','111');
/*!40000 ALTER TABLE `photo_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `url` varchar(150) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_del` varchar(1) NOT NULL DEFAULT 'N',
  `album_id` int(11) NOT NULL,
  `msg_id` int(11) DEFAULT NULL,
  `uid` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url_UNIQUE` (`url`),
  KEY `pic_blu_idx` (`album_id`),
  KEY `pic_message_idx` (`msg_id`),
  KEY `pic_uid_idx` (`uid`),
  CONSTRAINT `pic_blu` FOREIGN KEY (`album_id`) REFERENCES `photo_album` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pic_message` FOREIGN KEY (`msg_id`) REFERENCES `message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pic_uid` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` VALUES (8,'1444242383.jpg','/r/eadb2ec4-16e7-450a-a6b8-14667b9184ca/picture/0a6b098b-7000-4de4-b0ec-0eac8df4bc5e.jpg','2018-07-10 10:35:18','N',17,19,'111'),(9,'20141022033043207.jpg','/r/eadb2ec4-16e7-450a-a6b8-14667b9184ca/picture/2bf1f9fc-4e78-4fcb-906f-7bcdff7e34bf.jpg','2018-07-10 10:35:19','N',17,19,'111'),(10,'default.jpg','/r/eadb2ec4-16e7-450a-a6b8-14667b9184ca/picture/f6bafcfe-5aa7-47d7-bf71-cc96f1fe069f.jpg','2018-07-10 12:19:45','N',17,23,'111');
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_file_path`
--

DROP TABLE IF EXISTS `user_file_path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_file_path` (
  `uuid` varchar(45) NOT NULL,
  `uid` varchar(20) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `uid_UNIQUE` (`uid`),
  CONSTRAINT `user_file_path_uid` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_file_path`
--

LOCK TABLES `user_file_path` WRITE;
/*!40000 ALTER TABLE `user_file_path` DISABLE KEYS */;
INSERT INTO `user_file_path` VALUES ('eadb2ec4-16e7-450a-a6b8-14667b9184ca','111');
/*!40000 ALTER TABLE `user_file_path` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `url` varchar(150) NOT NULL,
  `local_path` varchar(150) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message_id` int(11) NOT NULL,
  `uid` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `local_path_UNIQUE` (`local_path`),
  UNIQUE KEY `url_UNIQUE` (`url`),
  KEY `video_message_idx` (`message_id`),
  KEY `video_user_idx` (`uid`),
  CONSTRAINT `video_message` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `video_user` FOREIGN KEY (`uid`) REFERENCES `weibo_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weibo_user`
--

DROP TABLE IF EXISTS `weibo_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weibo_user` (
  `uid` varchar(20) NOT NULL,
  `nick_name` varchar(35) DEFAULT NULL,
  `real_name` varchar(5) DEFAULT NULL,
  `_password` varchar(45) NOT NULL,
  `sex` varchar(3) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(25) DEFAULT NULL,
  `tel` varchar(12) DEFAULT NULL,
  `qq` varchar(15) DEFAULT NULL,
  `blog_id` varchar(45) DEFAULT NULL,
  `signature` varchar(60) DEFAULT NULL,
  `introduce` varchar(120) DEFAULT NULL,
  `localtion_id` int(11) DEFAULT NULL,
  `lv` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`uid`),
  KEY `user_localtion_idx` (`localtion_id`),
  CONSTRAINT `user_localtion` FOREIGN KEY (`localtion_id`) REFERENCES `localtion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weibo_user`
--

LOCK TABLES `weibo_user` WRITE;
/*!40000 ALTER TABLE `weibo_user` DISABLE KEYS */;
INSERT INTO `weibo_user` VALUES ('111','得分低谷',NULL,'aY1RoZ2KEhzlgUmde3AWaA==',NULL,NULL,'2018-07-10 10:33:04',NULL,NULL,NULL,NULL,NULL,'一句话介绍下自己吧，让别人更了解你',NULL,0);
/*!40000 ALTER TABLE `weibo_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sping_cloud_weibo'
--

--
-- Dumping routines for database 'sping_cloud_weibo'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-11  8:54:46
