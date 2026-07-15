package com.auxiongg.restaurant.reports;

import lombok.Data;

import java.util.List;

import com.auxiongg.restaurant.bookings.dtos.TimeSlotDto;
import com.auxiongg.restaurant.menu.dtos.MenuItemDto;
import com.auxiongg.restaurant.users.customers.SimpleCustomerDto;
import com.auxiongg.restaurant.users.staff.SimpleStaffDto;

@Data
public class ReportDto {
    private List<SimpleCustomerDto> mostActiveCustomers;
    private List<SimpleStaffDto> mostActiveStaff;
    private List<MenuItemDto> mostPopularItems;
    private List<TimeSlotDto> busiestPeriods;
}
