DELIMITER $$

CREATE
    TRIGGER `before_block_update` BEFORE UPDATE ON `block` 
    FOR EACH ROW BEGIN
      SET  NEW.modified_time := NOW()  ;
    END;
$$

DELIMITER ;


DELIMITER $$

CREATE
   
    TRIGGER `before_block_insert` BEFORE INSERT
    ON `block`
    FOR EACH ROW BEGIN
	SET  NEW.creation_time := NOW()  ;
	SET  NEW.modified_time := NOW()  ;

    END$$

DELIMITER ;

DELIMITER $$


CREATE
    
    TRIGGER `before_criteria_insert` BEFORE INSERT ON `criteria` 
    FOR EACH ROW BEGIN
	SET  NEW.creation_time := NOW()  ;
	SET  NEW.modified_time := NOW()  ;
    END;
$$

DELIMITER ;

DELIMITER $$

CREATE

    TRIGGER `before_criteria_update` BEFORE UPDATE ON `criteria` 
    FOR EACH ROW BEGIN
    SET  NEW.modified_time := NOW()  ;
		
    END;
$$

DELIMITER ;


DELIMITER $$

CREATE
    
    TRIGGER `before_criteria_block_insert` BEFORE INSERT ON `criteria_block` 
    FOR EACH ROW BEGIN
	SET  NEW.creation_time := NOW()  ;
	SET  NEW.modified_time := NOW()  ;
    END;
$$

DELIMITER ;

DELIMITER $$

CREATE

    TRIGGER `before_criteria_block_update` BEFORE UPDATE ON `criteria_block` 
    FOR EACH ROW BEGIN
    SET  NEW.modified_time := NOW()  ;
		
    END;
$$

DELIMITER ;


DELIMITER $$

CREATE
    
    TRIGGER `before_destination_insert` BEFORE INSERT ON `destination` 
    FOR EACH ROW BEGIN
	SET  NEW.creation_time := NOW()  ;
	SET  NEW.modified_time := NOW()  ;
    END;
$$

DELIMITER ;

DELIMITER $$

CREATE

    TRIGGER `before_destination_update` BEFORE UPDATE ON `destination` 
    FOR EACH ROW BEGIN
    SET  NEW.modified_time := NOW()  ;
		
    END;
$$

DELIMITER ;









