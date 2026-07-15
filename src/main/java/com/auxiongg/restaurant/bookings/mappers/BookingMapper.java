package com.auxiongg.restaurant.bookings.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.auxiongg.restaurant.bookings.dtos.BookingDto;
import com.auxiongg.restaurant.bookings.dtos.CreateBookingRequest;
import com.auxiongg.restaurant.bookings.entities.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toEntity(CreateBookingRequest request);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "tableId", source = "table.id")
    @Mapping(target = "bookingTime", source = "startTime")
    BookingDto toDto(Booking booking);
}
