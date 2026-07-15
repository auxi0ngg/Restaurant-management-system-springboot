package com.auxiongg.restaurant.users.staff;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.auxiongg.restaurant.auth.AuthService;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    private final ShiftRepository shiftRepository;
    private final AuthService authService;

    public StaffDto createStaff(CreateStaffProfileRequest request) {
        var staff = staffMapper.toEntity(request);
        var user = authService.getCurrentUser();

        staff.setUser(user);
        staffRepository.save(staff);
        return staffMapper.toDto(staff);
    }

    public StaffDto addShift(Long shiftId, Long id, LocalDate date) {
        var staff = staffRepository.findById(id).orElse(null);
        if (staff == null) {
            throw new StaffNotFoundException();
        }

        var shift = shiftRepository.findById(shiftId).orElse(null);
        if (shift == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shift not found");
        }

        staff.addShift(shift, date);

        staffRepository.save(staff);

        return staffMapper.toDto(staff);
    }

    public StaffDto me() {
        var user = authService.getCurrentUser();

        var staff = staffRepository.findById(user.getId()).orElse(null);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found");
        }

        return staffMapper.toDto(staff);
    }
}
