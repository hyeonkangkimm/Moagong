package com.example.skunivProject.domain.users.controller;

import com.example.skunivProject.domain.users.dto.ResponseDto;
import com.example.skunivProject.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/my")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 내 계정 정보 조회
     */
    @GetMapping("/account")
    public ResponseEntity<ResponseDto.MyAccountDto> getMyAccount(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        ResponseDto.MyAccountDto response = userService.getMyAccount(userDetails.getUsername());
        return ResponseEntity.ok(response);
    }
}
