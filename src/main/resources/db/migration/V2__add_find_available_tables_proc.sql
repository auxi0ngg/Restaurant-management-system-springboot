DELIMITER $$

CREATE PROCEDURE findAvailableTables (
    startTime TIME,
    endTime TIME,
    bookingDate DATE
)
BEGIN
    SELECT t.id, t.capacity
    FROM restaurant_tables t
    WHERE t.id NOT IN
          (SELECT b.table_id FROM bookings b
           WHERE b.booking_date = bookingDate
             AND b.start_time < endTime
           AND b.end_time > startTime);
END $$

DELIMITER ;
