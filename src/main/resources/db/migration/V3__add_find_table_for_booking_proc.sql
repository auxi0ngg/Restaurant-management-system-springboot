DELIMITER $$

CREATE PROCEDURE findTableForBooking (
    bookingDate DATE,
    startTime TIME,
    endTime TIME,
    guests INTEGER
)
BEGIN
    SELECT t.id, t.capacity
    FROM restaurant_tables t
    WHERE t.id NOT IN
        (SELECT b.table_id FROM bookings b
        WHERE b.booking_date = bookingDate
        AND b.start_time <= endTime
        AND b.end_time > startTime)
    AND t.capacity >= guests
    ORDER BY t.capacity
    LIMIT 1;
END $$

DELIMITER ;

