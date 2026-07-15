package com.auxiongg.restaurant.events;

import org.mapstruct.Mapper;

import com.auxiongg.restaurant.events.dtos.CreateEventRequest;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(CreateEventRequest createEventRequest);
    EventDto toDto(Event event);
}
