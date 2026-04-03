package com.zorvyn.project.Controller;

import com.zorvyn.project.Dto.LoginRequest;
import com.zorvyn.project.Dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthController {

    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest);

    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest);
}
