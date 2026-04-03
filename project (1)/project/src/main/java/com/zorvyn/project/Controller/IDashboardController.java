package com.zorvyn.project.Controller;

import com.zorvyn.project.Model.DashboardResponse;
import org.springframework.http.ResponseEntity;

public interface IDashboardController {

    public ResponseEntity<DashboardResponse> getSummary();
}
