/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.50-community : Database - ccgl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ccgl` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `ccgl`;

/*Table structure for table `t_good` */

DROP TABLE IF EXISTS `t_good`;

CREATE TABLE `t_good` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodnumber` char(20) DEFAULT NULL,
  `goodname` char(20) DEFAULT NULL,
  `supplierid` int(11) DEFAULT NULL,
  `goodcategoriesid` int(11) DEFAULT NULL,
  `note` char(50) DEFAULT NULL,
  `photo` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_good` (`supplierid`),
  KEY `F_t_good` (`goodcategoriesid`),
  CONSTRAINT `FK_t_good` FOREIGN KEY (`supplierid`) REFERENCES `t_supplier` (`id`),
  CONSTRAINT `F_t_good` FOREIGN KEY (`goodcategoriesid`) REFERENCES `t_goodcategories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_good` */

insert  into `t_good`(`id`,`goodnumber`,`goodname`,`supplierid`,`goodcategoriesid`,`note`,`photo`) values (1,'1','复仇者',1,1,'商品1','images/page4-img3.jpg'),(4,'2','牧马人',1,1,'商品2','images/page4-img1.jpg'),(7,'3','塞德斯',1,2,'商品3','images/page4-img2.jpg'),(9,'5','nica',1,2,'shangpin3','images/page4-img4.jpg'),(10,'4','fds',1,1,'asd','images/page4-img5.jpg'),(11,'6','asd',1,2,'asd','images/page4-img6.jpg');

/*Table structure for table `t_goodcategories` */

DROP TABLE IF EXISTS `t_goodcategories`;

CREATE TABLE `t_goodcategories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodname` char(10) DEFAULT NULL,
  `description` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `t_goodcategories` */

insert  into `t_goodcategories`(`id`,`goodname`,`description`) values (1,'鼠标','牧马人'),(2,'键盘','复仇者'),(3,'耳机','萨德斯'),(17,'正在z\'z','z\'z\'z');

/*Table structure for table `t_outstock` */

DROP TABLE IF EXISTS `t_outstock`;

CREATE TABLE `t_outstock` (
  `outstockid` int(11) NOT NULL AUTO_INCREMENT,
  `goodid` int(11) DEFAULT NULL,
  `saleprice` double DEFAULT NULL,
  `outstockdate` date DEFAULT NULL,
  `outstocknumber` int(11) DEFAULT NULL,
  `outstocknote` char(30) DEFAULT NULL,
  `quarter` char(20) DEFAULT NULL,
  PRIMARY KEY (`outstockid`),
  KEY `FHK_t_outstock` (`goodid`),
  CONSTRAINT `FHK_t_outstock` FOREIGN KEY (`goodid`) REFERENCES `t_good` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

/*Data for the table `t_outstock` */

insert  into `t_outstock`(`outstockid`,`goodid`,`saleprice`,`outstockdate`,`outstocknumber`,`outstocknote`,`quarter`) values (2,4,20,'2000-05-05',80,'嘿嘿','第二季度'),(4,1,30,'2000-01-01',30,'哈哈','第一季度'),(7,7,333,'2000-11-15',3,'塞德斯耳机','第四季度'),(93,9,22,'2000-11-05',100,'test1','第四季度'),(94,10,22,'2000-07-06',22,'test2','第三季度'),(95,11,33,'2000-04-01',400,'test3','第二季度'),(96,7,22,NULL,20,NULL,'第一季度'),(97,7,NULL,NULL,200,NULL,'第二季度'),(98,7,NULL,NULL,2000,NULL,'第三季度');

/*Table structure for table `t_stock` */

DROP TABLE IF EXISTS `t_stock`;

CREATE TABLE `t_stock` (
  `stockid` int(11) NOT NULL AUTO_INCREMENT,
  `goodid` int(11) DEFAULT NULL,
  `goodnumber` int(11) DEFAULT NULL,
  `stocknote` char(30) DEFAULT NULL,
  `outstockid` int(11) DEFAULT NULL,
  `storageid` int(11) DEFAULT NULL,
  PRIMARY KEY (`stockid`),
  KEY `FK_t_stock` (`storageid`),
  KEY `F_t_stock` (`outstockid`),
  KEY `FKq_t_stock` (`goodid`),
  CONSTRAINT `FKq_t_stock` FOREIGN KEY (`goodid`) REFERENCES `t_good` (`id`),
  CONSTRAINT `FK_t_stock` FOREIGN KEY (`storageid`) REFERENCES `t_storage` (`id`),
  CONSTRAINT `F_t_stock` FOREIGN KEY (`outstockid`) REFERENCES `t_outstock` (`outstockid`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `t_stock` */

insert  into `t_stock`(`stockid`,`goodid`,`goodnumber`,`stocknote`,`outstockid`,`storageid`) values (40,1,1,'无',4,92),(42,4,1,'TEST1',2,93),(44,7,1,'无',7,106),(45,9,2,NULL,NULL,NULL),(46,10,3,NULL,NULL,NULL),(47,11,4,NULL,NULL,NULL);

/*Table structure for table `t_storage` */

DROP TABLE IF EXISTS `t_storage`;

CREATE TABLE `t_storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodid` int(11) DEFAULT NULL,
  `inprice` double DEFAULT NULL,
  `storagedate` date DEFAULT NULL,
  `storagenumber` int(11) DEFAULT NULL,
  `storagenote` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_storage` (`goodid`),
  CONSTRAINT `FK_t_storage` FOREIGN KEY (`goodid`) REFERENCES `t_good` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

/*Data for the table `t_storage` */

insert  into `t_storage`(`id`,`goodid`,`inprice`,`storagedate`,`storagenumber`,`storagenote`) values (4,NULL,NULL,NULL,NULL,NULL),(92,1,90,'2013-11-16',1,'复仇者键盘'),(93,4,100,'2013-11-16',1,'达尔优木马人'),(106,7,333,'2013-11-15',3,'塞德斯耳机');

！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！11
/*Table structure for table `t_supplier` */

DROP TABLE IF EXISTS `t_supplier`;

CREATE TABLE `t_supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` char(10) DEFAULT NULL,
  `name` char(20) DEFAULT NULL,
  `linkman` char(20) DEFAULT NULL,
  `linkphone` char(20) DEFAULT NULL,
  `note` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `t_supplier` */

insert  into `t_supplier`(`id`,`number`,`name`,`linkman`,`linkphone`,`note`) values (1,'1','sun','小哈','123','111'),(5,'3','sanxing','小很','3','44'),(6,'5','sony','小日','6666','66666'),(7,'4','xiaomi','小米','444','555222333'),(9,'7','iphone','苹果','444','555'),(10,'8','nokia','诺基亚','111','222'),(11,'9','nicai','尼采','22','33'),(12,'10','asus','华硕','33','444'),(13,'11','hongji','宏基','44','55'),(14,'23','3','4444','4','4444444');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;


！！！！！！！！！！！！！！

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` char(20) DEFAULT NULL,
  `password` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userName`,`password`) values (1,'dyj','123'),(2,'ly','119');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
