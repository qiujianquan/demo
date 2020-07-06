package com.example.demo.okex.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class OkexConstants {

    @Value("${okex.address}")
    private String url;
    @Value("${okex.contractContractInfo}")
    private String contractContractInfo;
    @Value("${okex.swapContractInfo}")
    private String swapContractInfo;

}
