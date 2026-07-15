package com.auxiongg.restaurant.reports;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
@Tag(name = "Reports")
public class ReportController {
    private final ReportService reportService;

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(description = "Displays Top 5 Most Active Customers, Staff," +
            " Busiest Periods and Menu Items For the Last 7 Days")
    @GetMapping
    public ResponseEntity<ReportDto> getReport() {
        var reportDto = reportService.getReport();
        return ResponseEntity.ok(reportDto);
    }
}
