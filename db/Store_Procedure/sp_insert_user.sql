CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_user`(
    IN fullName varchar(45),
    IN phone varchar(45),
    IN cityId int,
    IN address varchar(45),
    IN age int,
    IN dob datetime,
    OUT success tinyint(1),
    OUT message varchar(255))
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
            INSERT INTO users(full_name, phone, city_id, address, age, dob)
            VALUES (fullName, phone, cityId, address, age, dob);

            SET message = 'Add new user successful';
            SET success = true;
        END IF;
    END IF;
END