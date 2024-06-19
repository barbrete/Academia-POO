-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: projetofinalpoo
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `academia`
--

DROP TABLE IF EXISTS `academia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `academia` (
  `idacademia` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `datacriacao` timestamp NULL DEFAULT NULL,
  `datamodificacao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idacademia`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academia`
--

LOCK TABLES `academia` WRITE;
/*!40000 ALTER TABLE `academia` DISABLE KEYS */;
INSERT INTO `academia` VALUES (1,'ACADEMIA BIOTECH','AV. LEOPOLDINO DE OLVEIRA','1997-09-11 10:23:50','2024-06-19 17:44:51'),(2,'saruê','android','1997-09-11 10:23:50','1997-08-12 10:30:33'),(4,'ACADEMIA EQUILIBRIO','AV. DOM LUIZ MARIA DE SANTANA','2024-06-13 02:40:52','2024-06-13 02:40:52'),(5,'Soja','Milho','2024-06-19 17:36:11','2024-06-19 17:36:11');
/*!40000 ALTER TABLE `academia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercicio`
--

DROP TABLE IF EXISTS `exercicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercicio` (
  `idexercicio` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `datacriacao` timestamp NULL DEFAULT NULL,
  `datamodificacao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idexercicio`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercicio`
--

LOCK TABLES `exercicio` WRITE;
/*!40000 ALTER TABLE `exercicio` DISABLE KEYS */;
INSERT INTO `exercicio` VALUES (1,'Supino Reto','Exercício de musculação focado no peitoral, realizado em um banco plano com uma barra ou halteres.','2024-06-19 15:00:00','2024-06-19 15:00:00'),(2,'Agachamento Livre','Exercício composto que trabalha os músculos das pernas e glúteos, realizado com uma barra nas costas.','2024-06-19 15:00:00','2024-06-19 15:00:00'),(3,'Levantamento Terra','Exercício de força que trabalha a parte inferior e superior do corpo, levantando uma barra do chão até a altura dos quadris.','2024-06-19 15:00:00','2024-06-19 15:00:00'),(4,'Rosca Bíceps','Exercício para desenvolvimento dos bíceps, realizado com halteres ou barra.','2024-06-19 15:00:00','2024-06-19 15:00:00'),(5,'Desenvolvimento de Ombros','Exercício para os músculos dos ombros, realizado com barra ou halteres, sentado ou em pé.','2024-06-19 15:00:00','2024-06-19 15:00:00'),(6,'Leg Press','Exercício para as pernas, realizado em uma máquina onde se empurra uma plataforma com os pés.','2024-06-19 15:00:00','2024-06-19 15:00:00'),(7,'Pulldown (Puxada)','Exercício para as costas, realizado em uma máquina de puxada, puxando uma barra de cima para baixo.','2024-06-19 15:00:00','2024-06-19 15:00:00'),(8,'Abdominal Crunch','Exercício para os músculos abdominais, realizado deitado, levantando a parte superior do tronco em direção aos joelhos.','2024-06-19 15:00:00','2024-06-19 15:00:00'),(9,'Flexão de Braço','Exercício de peso corporal que trabalha peitoral, ombros e tríceps, realizado no chão.','2024-06-19 15:00:00','2024-06-19 15:00:00'),(10,'Cadeira Extensora','Exercício para os quadríceps, realizado em uma máquina específica, estendendo os joelhos.','2024-06-19 15:00:00','2024-06-19 15:00:00');
/*!40000 ALTER TABLE `exercicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `idpessoa` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL,
  `datanascimento` date DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  `tipousuario` varchar(45) DEFAULT NULL,
  `datacriacao` timestamp NULL DEFAULT NULL,
  `datamodificacao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idpessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Rogerio','M','1997-09-11','Rojas','123','Administrador','2024-06-19 17:44:51','2024-06-19 17:44:51'),(2,'Barbrete','F','1966-06-06','Babs','123','Professor','2024-06-19 19:50:36','2024-06-19 19:50:36'),(3,'Eduardo','M','1966-06-06','Dudu','123','Aluno','2024-06-19 19:50:36','2024-06-19 19:50:36');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-19 17:42:09
