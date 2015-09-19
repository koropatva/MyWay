DELIMITER $$


DROP TRIGGER /*!50032 IF EXISTS */ `before_block_update`$$
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



