package com.kb1.containerMarket.web.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
    private String id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String provider;
    private int role_id;
    private LocalDate birthDay;
    private LocalDateTime create_date;
    private LocalDateTime update_date;

    private Role role;
}
