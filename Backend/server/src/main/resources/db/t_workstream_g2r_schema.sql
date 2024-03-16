SELECT * FROM candb_5g.t_workstream_g2r;CREATE TABLE `t_workstream_g2r` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code_g2r` varchar(30) NOT NULL,
  `code_activite` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `resp` varchar(50) DEFAULT NULL,
  `resp_team` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ID_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
