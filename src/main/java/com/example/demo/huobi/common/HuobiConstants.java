package com.example.demo.huobi.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class HuobiConstants {

    @Value("${huobi.address1}")
    private String url;
    @Value("${huobi.api.key}")
    private String key;
    @Value("${huobi.api.secret}")
    private String secret;
    @Value("${huobi.contractContractInfo}")
    private String contractContractInfo;
    @Value("${huobi.swapContractInfo}")
    private String swapContractInfo;

}
