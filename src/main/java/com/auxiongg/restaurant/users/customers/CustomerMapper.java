package com.auxiongg.restaurant.users.customers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CreateCustomerProfileRequest request);

    CustomerDto toDto(Customer customer);
}
