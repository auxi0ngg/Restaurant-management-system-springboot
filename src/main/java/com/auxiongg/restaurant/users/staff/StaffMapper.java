package com.auxiongg.restaurant.users.staff;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    Staff toEntity(CreateStaffProfileRequest request);

    @Mapping(target = "hoursWorked", expression = "java(staff.calculateHoursWorked())")
    StaffDto toDto(Staff staff);

}
