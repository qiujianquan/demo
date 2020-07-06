package com.example.demo.okex.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OkexSwapContractInfoDto {


    /**
     * instrument_id : NEO-USDT-SWAP
     * underlying_index : NEO
     * quote_currency : USDT
     * coin : USDT
     * contract_val : 1
     * listing : 2020-02-28T11:16:48.000Z
     * delivery : 2020-07-07T08:00:00.000Z
     * size_increment : 1
     * tick_size : 0.01
     * base_currency : NEO
     * underlying : NEO-USDT
     * settlement_currency : USDT
     * is_inverse : false
     * contract_val_currency : NEO
     */

    private String instrument_id;
    private String underlying_index;
    private String quote_currency;
    private String coin;
    private String contract_val;
    private String listing;
    private String delivery;
    private String size_increment;
    private String tick_size;
    private String base_currency;
    private String underlying;
    private String settlement_currency;
    private String is_inverse;
    private String contract_val_currency;
}
