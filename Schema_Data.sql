DROP TABLE IF EXISTS character_armor;
DROP TABLE IF EXISTS character_weapon;
DROP TABLE IF EXISTS weapons;
DROP TABLE IF EXISTS armor;
DROP TABLE IF EXISTS `character`;

CREATE TABLE `character` (
	character_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45),
    fighting_style ENUM('MAGIC', 'WARRIOR', 'ARCHERY'),
    PRIMARY KEY (character_id)
    );
    
CREATE TABLE armor (
	armor_id INT NOT NULL AUTO_INCREMENT,
	armor_name VARCHAR(20),
    armor_effectiveness INT,
    armor_location ENUM('HEAD', 'CHEST', 'FEET'),
    PRIMARY KEY (armor_id)
    ); 
    
CREATE TABLE weapons (
	weapon_id INT NOT NULL AUTO_INCREMENT,
	weapon_name VARCHAR(20),
    weapon_effectiveness INT,
    PRIMARY KEY (weapon_id)
    );  
    
 CREATE TABLE character_armor (
	character_id INT NOT NULL,
    head_armor_id INT,
    body_armor_id INT,
	feet_armor_id INT,
    FOREIGN KEY (character_id) REFERENCES `character`(character_id)
    );   
    
CREATE TABLE character_weapon (
    character_id INT NOT NULL,
    weapon_id INT NOT NULL,
	FOREIGN KEY (character_id) REFERENCES `character`(character_id),
	FOREIGN KEY (weapon_id) REFERENCES weapons(weapon_id)
    );