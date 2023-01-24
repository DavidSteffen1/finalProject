INSERT INTO armor (armor_id, name, effectiveness, location)
VALUES (1, 'Wizard_Hat', NULL, 'head'),
(2, 'Chain_Helmet', NULL, 'head'),
(3, 'Archers_Cap', NULL, 'head');

INSERT INTO weapons (weapon_id, name, effectiveness)
VALUES (1, 'Wand', NULL),
(2, 'Axe', NULL),
(3, 'Bow', NULL);

INSERT INTO `character` (character_id, name, fighting_style)
VALUES (1, 'Gandalf', 'MAGIC'), 
(2, 'Sun_Tzu', 'WARRIOR'), 
(3, 'Robin_Hood', 'ARCHERY');

INSERT INTO character_weapon (character_id, weapon_id)
VALUES (1, 1),
(1, 2),
(2, 2),
(2, 3),
(3, 3);