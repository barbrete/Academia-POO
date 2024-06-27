CREATE DATABASE  IF NOT EXISTS `projetopoofinal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `projetopoofinal`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: projetopoofinal
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academia`
--

LOCK TABLES `academia` WRITE;
/*!40000 ALTER TABLE `academia` DISABLE KEYS */;
INSERT INTO `academia` VALUES (1,'ACADEMIA BIOTECH','AV. LEOPOLDINO DE OLVEIRA','1997-09-11 10:23:50','2024-06-19 17:44:51'),(2,'ACADEMIA EQUILIBRIO','AV. DOM LUIZ MARIA DE SANTANA','2024-06-13 02:40:52','2024-06-13 02:40:52'),(3,'ACADEMIA NOVO CORPO','EDILSON LAMARTINE MENDES','2024-06-13 02:40:52','2024-06-13 02:40:52');
/*!40000 ALTER TABLE `academia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alunopagamentomensalidade`
--

DROP TABLE IF EXISTS `alunopagamentomensalidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alunopagamentomensalidade` (
  `id_aluno_pagamento_mensalidade` int NOT NULL AUTO_INCREMENT,
  `mv_aluno_pagamento_id` int NOT NULL,
  `data_vencimento` date NOT NULL,
  `data_pagamento` date DEFAULT NULL,
  `valor_pago` double NOT NULL,
  `aluno_id` int NOT NULL,
  `modalidade` int NOT NULL,
  `data_criacao` timestamp NOT NULL,
  `data_modificacao` timestamp NOT NULL,
  PRIMARY KEY (`id_aluno_pagamento_mensalidade`),
  KEY `mv_aluno_pagamento_id` (`mv_aluno_pagamento_id`),
  KEY `aluno_id` (`aluno_id`),
  CONSTRAINT `alunopagamentomensalidade_ibfk_1` FOREIGN KEY (`mv_aluno_pagamento_id`) REFERENCES `mensalidadevigente` (`idmensalidadevigente`),
  CONSTRAINT `alunopagamentomensalidade_ibfk_2` FOREIGN KEY (`aluno_id`) REFERENCES `pessoa` (`idpessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alunopagamentomensalidade`
--

LOCK TABLES `alunopagamentomensalidade` WRITE;
/*!40000 ALTER TABLE `alunopagamentomensalidade` DISABLE KEYS */;
INSERT INTO `alunopagamentomensalidade` VALUES (3,4,'2023-05-01',NULL,0,4,1,'2024-06-27 03:41:58','2024-06-27 03:41:58'),(4,2,'2023-05-01','2023-05-01',150,3,1,'2024-06-27 03:41:58','2024-06-27 03:41:58');
/*!40000 ALTER TABLE `alunopagamentomensalidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `divisaotreino`
--

DROP TABLE IF EXISTS `divisaotreino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `divisaotreino` (
  `iddivisaotreino` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `datacriacao` varchar(45) DEFAULT NULL,
  `datamodificacao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iddivisaotreino`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `divisaotreino`
--

LOCK TABLES `divisaotreino` WRITE;
/*!40000 ALTER TABLE `divisaotreino` DISABLE KEYS */;
INSERT INTO `divisaotreino` VALUES (1,'ABC','ABC descansa 1x ABC descansa 2x','2024-06-19 12:00:00','2024-06-19 12:00:00'),(2,'BCD','BCD descansa 1x BCD sem descanso','2024-06-19 12:00:00','2024-06-19 12:00:00'),(3,'CDB','CDE descansa 1x CDE descansa 1x CDE descansa 1x','2024-06-19 12:00:00','2024-06-19 12:00:00'),(4,'DBA','E descansa 30s E descansa 1min E descansa 30s','2024-06-21 18:24:53.617037','2024-06-21 18:24:53.617037');
/*!40000 ALTER TABLE `divisaotreino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `divisaotreinomusculo`
--

DROP TABLE IF EXISTS `divisaotreinomusculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `divisaotreinomusculo` (
  `iddivisaotreinomusculo` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `datacriacao` datetime DEFAULT NULL,
  `datamodificacao` datetime DEFAULT NULL,
  `iddivisaotreino` int DEFAULT NULL,
  PRIMARY KEY (`iddivisaotreinomusculo`),
  KEY `iddivisaotreino` (`iddivisaotreino`),
  CONSTRAINT `fkey_divisaotreinomusculo` FOREIGN KEY (`iddivisaotreino`) REFERENCES `divisaotreino` (`iddivisaotreino`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `divisaotreinomusculo`
--

LOCK TABLES `divisaotreinomusculo` WRITE;
/*!40000 ALTER TABLE `divisaotreinomusculo` DISABLE KEYS */;
INSERT INTO `divisaotreinomusculo` VALUES (1,'A','PEITO, OMBRO, TRICEPS','2024-06-19 12:00:00','2024-06-19 12:00:00',1),(2,'B','COSTAS, BICEPS, ABDOMEN','2024-06-19 12:00:00','2024-06-19 12:00:00',1),(3,'C','PERNAS','2024-06-19 12:00:00','2024-06-24 21:58:42',1),(10,'B','COSTAS, BICEPS, ABDOMEN','2024-06-19 12:00:00','2024-06-24 21:58:42',2),(11,'B','COSTAS, BICEPS, ABDOMEN','2024-06-19 12:00:00','2024-06-24 21:58:42',3),(12,'C','PERNAS','2024-06-19 12:00:00','2024-06-24 21:58:42',2),(13,'A','PEITO, OMBRO, TRICEPS','2024-06-19 12:00:00','2024-06-24 21:58:42',4),(14,'D','QUADRICEPS','2024-06-19 12:00:00','2024-06-24 21:58:42',2),(15,'C','PERNAS','2024-06-19 12:00:00','2024-06-24 21:58:42',3),(16,'D','QUADRICEPS','2024-06-19 12:00:00','2024-06-19 12:00:00',3),(17,'D','QUADRICEPS','2024-06-19 12:00:00','2024-06-19 12:00:00',4),(18,'B','COSTAS, BICEPS, ABDOMEN','2024-06-19 12:00:00','2024-06-19 12:00:00',4);
/*!40000 ALTER TABLE `divisaotreinomusculo` ENABLE KEYS */;
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
-- Table structure for table `exercicioaplicacao`
--

DROP TABLE IF EXISTS `exercicioaplicacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercicioaplicacao` (
  `idexercicioaplicacao` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  `datacriacao` timestamp NULL DEFAULT NULL,
  `datamodificacao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idexercicioaplicacao`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercicioaplicacao`
--

LOCK TABLES `exercicioaplicacao` WRITE;
/*!40000 ALTER TABLE `exercicioaplicacao` DISABLE KEYS */;
INSERT INTO `exercicioaplicacao` VALUES (1,'4x12 com rest pause','2024-06-19 15:00:00','2024-06-19 15:00:00'),(2,'4x15 bi set 2x6','2024-06-19 15:00:00','2024-06-19 15:00:00'),(3,'4x8 dropdown','2024-06-19 15:00:00','2024-06-19 15:00:00'),(4,'5x10 ','2024-06-19 15:00:00','2024-06-19 15:00:00'),(5,'3x15','2024-06-19 15:00:00','2024-06-19 15:00:00'),(6,'3x12','2024-06-19 15:00:00','2024-06-19 15:00:00'),(7,'4x10','2024-06-19 15:00:00','2024-06-19 15:00:00'),(8,'5 séries de 1 minuto','2024-06-19 15:00:00','2024-06-19 15:00:00'),(9,'3 séries de 1 minuto','2024-06-19 15:00:00','2024-06-19 15:00:00'),(10,'2x12, 3x10, 4x8','2024-06-21 20:59:21','2024-06-21 20:59:21');
/*!40000 ALTER TABLE `exercicioaplicacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensalidadevigente`
--

DROP TABLE IF EXISTS `mensalidadevigente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensalidadevigente` (
  `idmensalidadevigente` int NOT NULL AUTO_INCREMENT,
  `valor` decimal(7,2) DEFAULT NULL,
  `datainicio` date DEFAULT NULL,
  `datatermino` date DEFAULT NULL,
  `datacriacao` timestamp NULL DEFAULT NULL,
  `datamodificacao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idmensalidadevigente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensalidadevigente`
--

LOCK TABLES `mensalidadevigente` WRITE;
/*!40000 ALTER TABLE `mensalidadevigente` DISABLE KEYS */;
INSERT INTO `mensalidadevigente` VALUES (1,149.90,'2022-05-11','2022-07-14','2024-06-19 15:00:00','2024-06-26 02:58:54'),(2,130.00,'2024-06-25','2024-07-29','2024-06-19 15:00:00','2024-06-19 15:00:00'),(3,160.00,'1997-09-11','1997-09-11','2024-06-19 15:00:00','2024-06-26 03:27:42'),(4,39.99,'2026-11-11','2026-12-12','2024-06-19 15:00:00','2024-06-27 04:09:16');
/*!40000 ALTER TABLE `mensalidadevigente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento_recorrente`
--

DROP TABLE IF EXISTS `pagamento_recorrente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento_recorrente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pessoa_id` int DEFAULT NULL,
  `data` date DEFAULT NULL,
  `cartao_credito` varchar(255) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `data_inicio` date DEFAULT NULL,
  `numero_meses_autorizados` int DEFAULT NULL,
  `data_criacao` timestamp NULL DEFAULT NULL,
  `data_modificacao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pessoa_id` (`pessoa_id`),
  CONSTRAINT `pagamento_recorrente_ibfk_1` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`idpessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento_recorrente`
--

LOCK TABLES `pagamento_recorrente` WRITE;
/*!40000 ALTER TABLE `pagamento_recorrente` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamento_recorrente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamentomensalidade`
--

DROP TABLE IF EXISTS `pagamentomensalidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamentomensalidade` (
  `idPagamentoMensalidade` bigint NOT NULL AUTO_INCREMENT,
  `id_MensalidadeVigente` int DEFAULT NULL,
  `dataVencimento` date DEFAULT NULL,
  `dataPagamento` date DEFAULT NULL,
  `valorPago` decimal(7,2) DEFAULT NULL,
  `id_Pessoa` int DEFAULT NULL,
  `modalidadePagamento` int DEFAULT NULL,
  `dataCriacao` timestamp NULL DEFAULT NULL,
  `dataModificacao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idPagamentoMensalidade`),
  KEY `id_Pessoa` (`id_Pessoa`),
  KEY `id_MensalidadeVigente` (`id_MensalidadeVigente`),
  CONSTRAINT `pagamentomensalidade_ibfk_1` FOREIGN KEY (`id_Pessoa`) REFERENCES `pessoa` (`idpessoa`),
  CONSTRAINT `pagamentomensalidade_ibfk_2` FOREIGN KEY (`id_MensalidadeVigente`) REFERENCES `mensalidadevigente` (`idmensalidadevigente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamentomensalidade`
--

LOCK TABLES `pagamentomensalidade` WRITE;
/*!40000 ALTER TABLE `pagamentomensalidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamentomensalidade` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Rogerio','M','1997-09-11','Rojas','123','Administrador','2024-06-19 17:44:51','2024-06-19 17:44:51'),(2,'Barbrete','F','1966-06-06','Babs','123','Professor','2024-06-19 19:50:36','2024-06-19 19:50:36'),(3,'Eduardo','M','1966-06-06','Dudu','123','Aluno','2024-06-19 19:50:36','2024-06-19 19:50:36'),(4,'João Silva','M','1966-06-06','Joao','123','Aluno','2024-06-19 19:50:36','2024-06-19 19:50:36'),(9,'sarue','m','2002-09-11','sarue','123','Aluno','2024-06-27 04:42:36','2024-06-27 04:42:36');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treino`
--

DROP TABLE IF EXISTS `treino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treino` (
  `idtreino` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `objetivo` varchar(45) DEFAULT NULL,
  `datainicio` date DEFAULT NULL,
  `datatermino` date DEFAULT NULL,
  `datacriacao` timestamp NULL DEFAULT NULL,
  `datamodificacao` timestamp NULL DEFAULT NULL,
  `iddivisaotreino` int DEFAULT NULL,
  PRIMARY KEY (`idtreino`),
  KEY `fkey_treino_idx` (`iddivisaotreino`),
  CONSTRAINT `fkey_treino` FOREIGN KEY (`iddivisaotreino`) REFERENCES `divisaotreino` (`iddivisaotreino`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treino`
--

LOCK TABLES `treino` WRITE;
/*!40000 ALTER TABLE `treino` DISABLE KEYS */;
INSERT INTO `treino` VALUES (1,'Transformacao Fit','Emagrecimento','1997-09-11','1997-09-11','2024-06-19 15:00:00','2024-06-19 15:00:00',1),(2,'Massa Maximal','Hipertrofia Muscular','1997-09-11','1997-09-11','2024-06-19 15:00:00','2024-06-19 15:00:00',2),(3,'Flex & Flow','Flexibilidade e Mobilidade','1997-09-11','1997-09-11','2024-06-19 15:00:00','2024-06-19 15:00:00',3),(4,'Bulking Maximo','Construção de massa muscular','1997-09-11','1997-09-11','2024-06-19 15:00:00','2024-06-19 15:00:00',4),(5,'Cutting Maximo','Emagrecimento e cortes musculares','1997-09-11','1997-09-11','2024-06-19 15:00:00','2024-06-19 15:00:00',1);
/*!40000 ALTER TABLE `treino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treinoaplicacao`
--

DROP TABLE IF EXISTS `treinoaplicacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treinoaplicacao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pessoa_id` int DEFAULT NULL,
  `academia_id` int DEFAULT NULL,
  `treino_id` int DEFAULT NULL,
  `exercicio_id` int DEFAULT NULL,
  `exAplicacao_id` int DEFAULT NULL,
  `divTreino_id` int DEFAULT NULL,
  `divTreinoMusc_id` int DEFAULT NULL,
  `dataCriacao` timestamp NULL DEFAULT NULL,
  `dataModificacao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pessoa_id` (`pessoa_id`),
  KEY `academia_id` (`academia_id`),
  KEY `treino_id` (`treino_id`),
  KEY `exercicio_id` (`exercicio_id`),
  KEY `exAplicacao_id` (`exAplicacao_id`),
  KEY `divTreino_id` (`divTreino_id`),
  KEY `divTreinoMusc_id` (`divTreinoMusc_id`),
  CONSTRAINT `treinoaplicacao_ibfk_1` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`idpessoa`),
  CONSTRAINT `treinoaplicacao_ibfk_2` FOREIGN KEY (`academia_id`) REFERENCES `academia` (`idacademia`),
  CONSTRAINT `treinoaplicacao_ibfk_3` FOREIGN KEY (`treino_id`) REFERENCES `treino` (`idtreino`),
  CONSTRAINT `treinoaplicacao_ibfk_4` FOREIGN KEY (`exercicio_id`) REFERENCES `exercicio` (`idexercicio`),
  CONSTRAINT `treinoaplicacao_ibfk_5` FOREIGN KEY (`exAplicacao_id`) REFERENCES `exercicioaplicacao` (`idexercicioaplicacao`),
  CONSTRAINT `treinoaplicacao_ibfk_6` FOREIGN KEY (`divTreino_id`) REFERENCES `divisaotreino` (`iddivisaotreino`),
  CONSTRAINT `treinoaplicacao_ibfk_7` FOREIGN KEY (`divTreinoMusc_id`) REFERENCES `divisaotreinomusculo` (`iddivisaotreinomusculo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treinoaplicacao`
--

LOCK TABLES `treinoaplicacao` WRITE;
/*!40000 ALTER TABLE `treinoaplicacao` DISABLE KEYS */;
INSERT INTO `treinoaplicacao` VALUES (1,1,1,1,1,1,1,1,'2024-06-25 04:38:53','2024-06-25 04:38:53'),(2,3,3,3,3,3,3,3,'2024-06-25 04:39:04','2024-06-25 04:39:04'),(3,3,3,3,3,3,3,3,'2024-06-26 03:40:11','2024-06-26 03:50:26');
/*!40000 ALTER TABLE `treinoaplicacao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-27  2:22:42
