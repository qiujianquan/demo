package com.example.demo.okex.dto;

import lombok.Data;

@Data
public class OkexDto {


    /**
     * instrument_id : XRP-USD-200710
     * underlying_index : XRP
     * quote_currency : USD
     * tick_size : 0.0001
     * contract_val : 10
     * listing : 2020-06-26
     * delivery : 2020-07-10
     * trade_increment : 1
     * alias : this_week
     * underlying : XRP-USD
     * base_currency : XRP
     * settlement_currency : XRP
     * is_inverse : true
     * contract_val_currency : USD
     */

    private String instrument_id;
    private String underlying_index;
    private String quote_currency;
    private String tick_size;
    private String contract_val;
    private String listing;
    private String delivery;
    private String trade_increment;
    private String alias;
    private String underlying;
    private String base_currency;
    private String settlement_currency;
    private String is_inverse;
    private String contract_val_currency;


}
