package com.example.demo.huobi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SwapContractInfoDto {


    /**
     * status : ok
     * data : [{"symbol":"BTC","contract_code":"BTC-USD","contract_size":100,"price_tick":0.1,"create_date":"20200325","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"ETH","contract_code":"ETH-USD","contract_size":10,"price_tick":0.01,"create_date":"20200403","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"EOS","contract_code":"EOS-USD","contract_size":10,"price_tick":1.0E-4,"create_date":"20200410","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"BCH","contract_code":"BCH-USD","contract_size":10,"price_tick":0.01,"create_date":"20200407","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"BSV","contract_code":"BSV-USD","contract_size":10,"price_tick":0.01,"create_date":"20200407","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"LTC","contract_code":"LTC-USD","contract_size":10,"price_tick":0.01,"create_date":"20200410","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"XRP","contract_code":"XRP-USD","contract_size":10,"price_tick":1.0E-5,"create_date":"20200410","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"ETC","contract_code":"ETC-USD","contract_size":10,"price_tick":1.0E-4,"create_date":"20200414","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"TRX","contract_code":"TRX-USD","contract_size":10,"price_tick":1.0E-6,"create_date":"20200414","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"LINK","contract_code":"LINK-USD","contract_size":10,"price_tick":1.0E-4,"create_date":"20200417","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"ADA","contract_code":"ADA-USD","contract_size":10,"price_tick":1.0E-6,"create_date":"20200424","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"DASH","contract_code":"DASH-USD","contract_size":10,"price_tick":0.01,"create_date":"20200424","contract_status":1,"settlement_date":"1594036800000"},{"symbol":"ZEC","contract_code":"ZEC-USD","contract_size":10,"price_tick":0.01,"create_date":"20200424","contract_status":1,"settlement_date":"1594036800000"}]
     * ts : 1594028121361
     */

    private String status;
    private long ts;
    private List<DataBean> data;

   @Data
    public static class DataBean {
       /**
        * symbol : BTC
        * contract_code : BTC-USD
        * contract_size : 100
        * price_tick : 0.1
        * create_date : 20200325
        * contract_status : 1
        * settlement_date : 1594036800000
        */

       private String symbol;
       private String contract_code;
       private Integer contract_size;
       private BigDecimal price_tick;
       private String create_date;
       private Integer contract_status;
       private String settlement_date;

   }
}
