-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 30 jan. 2022 à 21:14
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `iot`
--

-- --------------------------------------------------------

--
-- Structure de la table `mesure`
--

CREATE TABLE `mesure` (
  `id` int(11) NOT NULL,
  `quantitegaz` int(11) NOT NULL,
  `datemesure` varchar(20) NOT NULL,
  `seuil_id` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `mesure`
--

INSERT INTO `mesure` (`id`, `quantitegaz`, `datemesure`, `seuil_id`) VALUES
(666, 0, '2022/01/26 01:38:47', 0),
(667, 0, '2022/01/26 01:38:52', 0),
(668, 1740, '2022/01/26 01:38:57', 0),
(669, 2368, '2022/01/26 01:39:02', 0),
(670, 2494, '2022/01/26 01:39:07', 0),
(671, 2505, '2022/01/26 01:39:12', 0),
(672, 2751, '2022/01/26 01:39:17', 0),
(673, 2412, '2022/01/26 01:39:22', 0),
(674, 0, '2022/01/26 09:30:55', 0);

-- --------------------------------------------------------

--
-- Structure de la table `seuil`
--

CREATE TABLE `seuil` (
  `id` int(11) NOT NULL,
  `valeur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `seuil`
--

INSERT INTO `seuil` (`id`, `valeur`) VALUES
(63, 66),
(64, 1800),
(65, 2000),
(66, 100),
(67, 3000),
(68, 2993820),
(69, 1923),
(70, 99),
(71, 182),
(72, 123),
(73, 22),
(74, 2000),
(75, 2000),
(76, 120),
(77, 2000),
(78, 3000),
(79, 3000),
(80, 19),
(81, 2000),
(82, 350),
(83, 3500);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `salt` varchar(10000) NOT NULL,
  `password` varchar(80) NOT NULL,
  `token` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `mesure`
--
ALTER TABLE `mesure`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `seuil`
--
ALTER TABLE `seuil`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `mesure`
--
ALTER TABLE `mesure`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=675;

--
-- AUTO_INCREMENT pour la table `seuil`
--
ALTER TABLE `seuil`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
