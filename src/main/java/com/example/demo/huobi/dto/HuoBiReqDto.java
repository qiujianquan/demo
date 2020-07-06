package com.example.demo.huobi.dto;

import lombok.Data;

@Data
public class HuoBiReqDto {
    private String symbol;
    private String contract_type;
    private String contract_code;
}
