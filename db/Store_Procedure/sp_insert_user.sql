CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_user`(
    IN fullName VARCHAR(45),
    IN phone VARCHAR(45),
    IN cityId INT,
    IN address VARCHAR(45),
    IN age INT,
    OUT success boolean,
    OUT message VARCHAR(255)
)
BEGIN

    SET success = false;

    IF (SELECT COUNT(*) AS count
        FROM cities AS c
        WHERE c.id = cityId) = 0
    THEN
        SET message = 'City ID not existing';
    ELSE
        IF (age > 32767)
        THEN
            SET message = 'Age invalid';
        ELSE
            INSERT INTO users(full_name, phone, city_id, address, age)
            VALUES (fullName, phone, cityId, address, age);

            SET success = true;
        END IF;
    END IF;
END