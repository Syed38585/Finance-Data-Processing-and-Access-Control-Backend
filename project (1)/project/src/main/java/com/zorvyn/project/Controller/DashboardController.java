package com.zorvyn.project.Controller;

import com.zorvyn.project.Model.DashboardResponse;
import com.zorvyn.project.Service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController implements IDashboardController {

    private final DashboardService service;

    /**
     * Fetches dashboard summary data.
     *
     * This endpoint returns aggregated financial information such as total income,
     * total expenses, net balance, category-wise totals, recent transactions,
     * and monthly trends.
     *
     * Accessible by users with VIEWER, ANALYST, or ADMIN roles.
     * Returns HTTP 200 with dashboard data on success.
     */
    @PreAuthorize("hasAnyRole('VIEWER','ANALYST','ADMIN')")
    @GetMapping("/summary")
    public ResponseEntity<DashboardResponse> getSummary() {
        DashboardResponse response = service.getDashboardSummary();
        return ResponseEntity.ok(response);
    }
}