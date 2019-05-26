SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `CMS`
--

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

-- REMOVE IT IF NEEDED
CREATE DATABASE CMS;
USE CMS;
-- END

CREATE TABLE `articles` (
                          `id` int(11) NOT NULL,
                          `text` text NOT NULL,
                          `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `articles`
--

INSERT INTO `articles` (`id`, `text`, `title`) VALUES
(12, 'Rigel, ou Beta Orionis (β Orionis / β Ori), est une supergéante bleue, 40 000 fois plus lumineuse et à peu près 78,9 fois plus grande que le Soleil, sixième étoile la plus brillante du ciel, et la plus brillante de la constellation d\'Orion1.\r\n\r\nDe magnitude apparente 0,12, c\'est la sixième étoile la plus brillante du ciel. Elle est même légèrement plus brillante que l\'étoile α, Bételgeuse (les magnitudes sont très proches, d\'où la confusion lors du classement par Johann Bayer).\r\n\r\nRigel se situe à une distance de 790 à 950 années-lumière de la Terre, trop loin pour être connue avec précision par la mesure de sa parallaxe, même si la meilleure estimation du satellite Hipparcos donne 863 années-lumière. Il est possible d\'en déduire que la magnitude absolue de Rigel est de l\'ordre de -6,7, ce qui en fait une étoile extrêmement lumineuse.\r\n\r\nElle est de type spectral B8Ia, une supergéante bleue, 40 000 fois plus lumineuse que le Soleil dans la lumière visible. Si on ajoute la puissance rayonnée dans l\'ultraviolet, la puissance émise est 66 000 celle du SoleilTBS 2. Avec un diamètre de 58 à 74 fois celui du Soleil, Rigel s\'étendrait jusqu\'à l\'orbite de Mercure dans le système solaireSAC 1. Elle est un peu plus grande que Canopus et n\'est dépassée en taille que par Antarès et Bételgeuse parmi les étoiles de première magnitudeTBS 2.\r\n\r\nComme beaucoup de supergéantes, Rigel est légèrement variable, de façon irrégulière, de 3 à 30 % sur une période de 25 jours en moyenne. Cette variabilité proviendrait de pulsations de la surface de l\'étoile.\r\n\r\nRigel a une masse de l\'ordre de 17 fois celle du SoleilSAC 1, et elle est vouée à produire une supernova et terminera probablement sa vie sous la forme d\'un trou noir.', 'Rigel'),
 (13, 'Alpha Centauri C (en abrégé α Cen C, parfois ACC), ou en français Alpha du Centaure C, est le système planétaire le plus proche du système solaire au sein de la Voie lactée. Il se situe à 4,23 années-lumière de la Terre dans la constellation du Centaure. C\'est une des trois composantes qui forment le système Alpha Centauri avec le couple central Alpha Centauri A et B.\r\n\r\nL\'objet primaire du système est l\'étoile centrale, nommée Proxima Centauri (latin pour « [l\'étoile] du Centaure la plus proche »), en français Proxima du Centaure, ou encore simplement Proxima, car il s\'agit de l\'étoile la plus proche de la Terre après le Soleil. C\'est une naine rouge de magnitude apparente 11,05, dont le rayon est environ une fois et demi celui de Jupiter et la masse un huitième de celle du Soleil.\r\n\r\nAu moins une planète tourne autour de cette étoile, Proxima Centauri b (formellement Alpha Centauri Cb). La découverte de cet objet, effectuée grâce à des mesures de vitesse radiale de son étoile avec le spectrographe HARPS, de masse terrestre et sur une orbite tempérée, a été annoncée le 24 août 2016.\r\n\r\nLe 3 novembre 2017 est annoncée la découverte d\'au moins une voire trois ceintures de poussières autour de Proxima, ainsi que peut-être un autre compagnon planétaire à 1,6 unité astronomique de l\'étoile, grâce à des observations effectuées avec l\'Atacama Large Millimeter/submillimeter Array (ALMA).', 'Proxima Centauri');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
                          `id` int(11) NOT NULL,
                          `text` text NOT NULL,
                          `idArticle` int(11) NOT NULL,
                          `datecreation` varchar(255) NOT NULL,
                          `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `text`, `idArticle`, `datecreation`, `username`) VALUES
(126, 'Wow.', 13, '26 mai 2019 à 03:30:21', 'user@user.com'),
(127, 'Wow wow.', 12, '26 mai 2019 à 03:30:35', 'user@user.com');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
                       `id` int(11) NOT NULL,
                       `username` varchar(255) NOT NULL,
                       `password` varchar(255) NOT NULL,
                       `isAdmin` tinyint(1) NOT NULL DEFAULT '0',
                       `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `isAdmin`, `email`) VALUES
(1, 'Tim', '$argon2i$v=19$m=65536,t=10,p=1$U7xPMxnidQoM5j0qmyrjcA$RfMKH25L+v/sQA4VSPi32a/FmwbNkwiPkX+wTvMVqDA', 1, 'tim@tim.com'),
(3, 'user', '$argon2i$v=19$m=65536,t=10,p=1$xYY3qb8J//YXsOaa+zkp0w$tl9yW0AA1i0q+oFUd0C8s/ixwyokqo+4dIwZEIScygk', 0, 'user@user.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ida` (`idArticle`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `articles`
--
ALTER TABLE `articles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=128;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `ida` FOREIGN KEY (`idArticle`) REFERENCES `articles` (`id`) ON DELETE CASCADE;
