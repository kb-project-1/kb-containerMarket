package com.kb1.containerMarket.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CMRespDto<T> {
    String message;
    T data;
}
