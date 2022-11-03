package com.kb1.containerMarket.web.dto.session;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponseDto {
    private String username;
    private String name;
    private String phone;
    private String email;
    private int role_id;
}
