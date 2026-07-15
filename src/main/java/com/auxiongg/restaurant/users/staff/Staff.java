package com.auxiongg.restaurant.users.staff;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.auxiongg.restaurant.users.User;

@Getter
@Setter
@Entity
@Table(name = "staff")
@Tag(name = "Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<StaffShift> shifts = new HashSet<>();

    public StaffShift getStaffShift(Long shiftId, LocalDate date) {
        for (var shift : shifts) {
            if (shift.getShift().getId().equals(shiftId) && shift.getDate() == date) {
                return shift;
            }
        }
        return null;
    }

    public void addShift(Shift shift, LocalDate date) {
            if (getStaffShift(shift.getId(), date) == null) {
            var staffShift = new StaffShift();
            staffShift.setShift(shift);
            staffShift.setDate(date);
            staffShift.setStaff(this);
            this.shifts.add(staffShift);
        }
    }

    public int calculateHoursWorked() {
        return shifts.stream()
                .map(StaffShift::getDuration)
                .reduce(0, Integer::sum);

    }

}