package com.example.demo.huobi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class HuobiDto {


    /**
     * status : ok
     * data : [{"symbol":"BTC","contract_code":"BTC200710","contract_type":"this_week","contract_size":100,"price_tick":0.01,"delivery_date":"20200710","create_date":"20200626","contract_status":1},{"symbol":"BTC","contract_code":"BTC200717","contract_type":"next_week","contract_size":100,"price_tick":0.01,"delivery_date":"20200717","create_date":"20200703","contract_status":1},{"symbol":"BTC","contract_code":"BTC200925","contract_type":"quarter","contract_size":100,"price_tick":0.01,"delivery_date":"20200925","create_date":"20200612","contract_status":1},{"symbol":"BTC","contract_code":"BTC201225","contract_type":"next_quarter","contract_size":100,"price_tick":0.01,"delivery_date":"20201225","create_date":"20200605","contract_status":1},{"symbol":"ETH","contract_code":"ETH200710","contract_type":"this_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200710","create_date":"20200626","contract_status":1},{"symbol":"ETH","contract_code":"ETH200717","contract_type":"next_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200717","create_date":"20200703","contract_status":1},{"symbol":"ETH","contract_code":"ETH200925","contract_type":"quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20200925","create_date":"20200612","contract_status":1},{"symbol":"ETH","contract_code":"ETH201225","contract_type":"next_quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20201225","create_date":"20200605","contract_status":1},{"symbol":"EOS","contract_code":"EOS200710","contract_type":"this_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200710","create_date":"20200626","contract_status":1},{"symbol":"EOS","contract_code":"EOS200717","contract_type":"next_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200717","create_date":"20200703","contract_status":1},{"symbol":"EOS","contract_code":"EOS200925","contract_type":"quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20200925","create_date":"20200612","contract_status":1},{"symbol":"EOS","contract_code":"EOS201225","contract_type":"next_quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20201225","create_date":"20200605","contract_status":1},{"symbol":"BCH","contract_code":"BCH200710","contract_type":"this_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200710","create_date":"20200626","contract_status":1},{"symbol":"BCH","contract_code":"BCH200717","contract_type":"next_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200717","create_date":"20200703","contract_status":1},{"symbol":"BCH","contract_code":"BCH200925","contract_type":"quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20200925","create_date":"20200612","contract_status":1},{"symbol":"BCH","contract_code":"BCH201225","contract_type":"next_quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20201225","create_date":"20200605","contract_status":1},{"symbol":"BSV","contract_code":"BSV200710","contract_type":"this_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200710","create_date":"20200626","contract_status":1},{"symbol":"BSV","contract_code":"BSV200717","contract_type":"next_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200717","create_date":"20200703","contract_status":1},{"symbol":"BSV","contract_code":"BSV200925","contract_type":"quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20200925","create_date":"20200612","contract_status":1},{"symbol":"BSV","contract_code":"BSV201225","contract_type":"next_quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20201225","create_date":"20200605","contract_status":1},{"symbol":"LTC","contract_code":"LTC200710","contract_type":"this_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200710","create_date":"20200626","contract_status":1},{"symbol":"LTC","contract_code":"LTC200717","contract_type":"next_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200717","create_date":"20200703","contract_status":1},{"symbol":"LTC","contract_code":"LTC200925","contract_type":"quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20200925","create_date":"20200612","contract_status":1},{"symbol":"LTC","contract_code":"LTC201225","contract_type":"next_quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20201225","create_date":"20200605","contract_status":1},{"symbol":"XRP","contract_code":"XRP200710","contract_type":"this_week","contract_size":10,"price_tick":1.0E-4,"delivery_date":"20200710","create_date":"20200626","contract_status":1},{"symbol":"XRP","contract_code":"XRP200717","contract_type":"next_week","contract_size":10,"price_tick":1.0E-4,"delivery_date":"20200717","create_date":"20200703","contract_status":1},{"symbol":"XRP","contract_code":"XRP200925","contract_type":"quarter","contract_size":10,"price_tick":1.0E-4,"delivery_date":"20200925","create_date":"20200612","contract_status":1},{"symbol":"XRP","contract_code":"XRP201225","contract_type":"next_quarter","contract_size":10,"price_tick":1.0E-4,"delivery_date":"20201225","create_date":"20200605","contract_status":1},{"symbol":"ETC","contract_code":"ETC200710","contract_type":"this_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200710","create_date":"20200626","contract_status":1},{"symbol":"ETC","contract_code":"ETC200717","contract_type":"next_week","contract_size":10,"price_tick":0.001,"delivery_date":"20200717","create_date":"20200703","contract_status":1},{"symbol":"ETC","contract_code":"ETC200925","contract_type":"quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20200925","create_date":"20200612","contract_status":1},{"symbol":"ETC","contract_code":"ETC201225","contract_type":"next_quarter","contract_size":10,"price_tick":0.001,"delivery_date":"20201225","create_date":"20200605","contract_status":1},{"symbol":"TRX","contract_code":"TRX200710","contract_type":"this_week","contract_size":10,"price_tick":1.0E-5,"delivery_date":"20200710","create_date":"20200626","contract_status":1},{"symbol":"TRX","contract_code":"TRX200717","contract_type":"next_week","contract_size":10,"price_tick":1.0E-5,"delivery_date":"20200717","create_date":"20200703","contract_status":1},{"symbol":"TRX","contract_code":"TRX200925","contract_type":"quarter","contract_size":10,"price_tick":1.0E-5,"delivery_date":"20200925","create_date":"20200612","contract_status":1},{"symbol":"TRX","contract_code":"TRX201225","contract_type":"next_quarter","contract_size":10,"price_tick":1.0E-5,"delivery_date":"20201225","create_date":"20200605","contract_status":1}]
     * ts : 1594023818888
     */

    private String status;
    private long ts;
    private List<DataBean> data;


    @Data
    public static class DataBean {
        /**
         * symbol : BTC
         * contract_code : BTC200710
         * contract_type : this_week
         * contract_size : 100
         * price_tick : 0.01
         * delivery_date : 20200710
         * create_date : 20200626
         * contract_status : 1
         */

        private String symbol;
        private String contract_code;
        private String contract_type;
        private BigDecimal contract_size;
        private BigDecimal price_tick;
        private String delivery_date;
        private String create_date;
        private Integer contract_status;
    }
}
