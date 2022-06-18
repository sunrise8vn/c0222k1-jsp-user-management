CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_search_user_by_date`(
    IN inDate VARCHAR(10),
    OUT success BOOLEAN,
    OUT message VARCHAR(255)
)
BEGIN
    SET success = false;

    if (SELECT DATE(inDate))
    THEN
        SELECT
            u.id,
            u.full_name,
            u.phone,
            u.address,
            u.dob,
            c.name
        FROM users AS u
        JOIN cities AS c
        ON u.city_id = c.id
        WHERE
            DATE(u.dob) = inDate;

        SET success = true;
    ELSE
        SET message = 'Date DOB invalid';
    END IF;
END