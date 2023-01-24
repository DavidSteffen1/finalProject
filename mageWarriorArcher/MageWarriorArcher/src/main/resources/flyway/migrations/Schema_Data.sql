DROP TABLE IF EXISTS character_armor;
DROP TABLE IF EXISTS character_weapon;
DROP TABLE IF EXISTS weapons;
DROP TABLE IF EXISTS armor;
DROP TABLE IF EXISTS `character`;

CREATE TABLE `character` (
	character_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45),
    fighting_style VARCHAR(20),
    PRIMARY KEY (character_id)
    );
    
CREATE TABLE armor (
	armor_id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20),
    effectiveness INT,
    location VARCHAR(20),
    PRIMARY KEY (armor_id)
    ); 
    
CREATE TABLE weapons (
	weapon_id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20),
    effectiveness INT,
    PRIMARY KEY (weapon_id)
    );  
    
 CREATE TABLE character_armor (
	character_id INT NOT NULL,
    armor_id INT NOT NULL,
    FOREIGN KEY (character_id) REFERENCES `character`(character_id),
	FOREIGN KEY (armor_id) REFERENCES `armor`(armor_id)
    );   
    
CREATE TABLE character_weapon (
    character_id INT NOT NULL,
    weapon_id INT NOT NULL,
	FOREIGN KEY (character_id) REFERENCES `character`(character_id),
	FOREIGN KEY (weapon_id) REFERENCES weapons(weapon_id)
    );