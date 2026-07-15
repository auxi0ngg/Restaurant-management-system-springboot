package com.auxiongg.restaurant.reports;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.auxiongg.restaurant.bookings.dtos.TimeSlotDto;
import com.auxiongg.restaurant.bookings.repositories.TimeSlotRepository;
import com.auxiongg.restaurant.menu.MenuItemRepository;
import com.auxiongg.restaurant.menu.MenuMapper;
import com.auxiongg.restaurant.users.customers.CustomerRepository;
import com.auxiongg.restaurant.users.customers.SimpleCustomerDto;
import com.auxiongg.restaurant.users.staff.SimpleStaffDto;
import com.auxiongg.restaurant.users.staff.StaffRepository;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ReportService {
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuMapper menuMapper;
    private final TimeSlotRepository timeSlotRepository;

    public ReportDto getReport() {
        var sevenDaysAgo = LocalDateTime.now().minusDays(7);
        var reportDto = new ReportDto();

        var customers = customerRepository.findMostActiveCustomersInLast7Days(
                PageRequest.of(0, 5),
                sevenDaysAgo);
        var customerResponse = customers
                .stream()
                .map(c -> new SimpleCustomerDto(
                        c.getFirstName(),
                        c.getLastName(),
                        c.getHouseNumber(),
                        c.getStreet(),
                        c.getPostcode()))
                .toList();
        reportDto.setMostActiveCustomers(customerResponse);

        var staff = staffRepository.findMostActiveStaff(
                PageRequest.of(0, 5),
                sevenDaysAgo.toLocalDate());
        var staffResponse = staff.stream().map(s -> new SimpleStaffDto(s.getFirstName(), s.getLastName(), s.calculateHoursWorked())).toList();
        reportDto.setMostActiveStaff(staffResponse);

        var menuItems = menuItemRepository.findMostPopularMenuItems(PageRequest.of(0, 5), sevenDaysAgo);
        var menuItemResponse = menuItems.stream().map(menuMapper::toDto).toList();
        reportDto.setMostPopularItems(menuItemResponse);

        var timeSlots = timeSlotRepository.getBusiestPeriods(PageRequest.of(0, 5), sevenDaysAgo);
        var timeSlotResponse = timeSlots.stream().map(t -> new TimeSlotDto(t.getStartTime())).toList();
        reportDto.setBusiestPeriods(timeSlotResponse);

        return reportDto;
    }
}
