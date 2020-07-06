package com.example.demo.okex.service;

import com.alibaba.fastjson.JSONObject;

import com.example.demo.okex.common.OkexConstants;
import com.example.demo.okex.dto.OkexDto;
import com.example.demo.okex.dto.OkexSwapContractInfoDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import util.okhttp.OkHttpUtils;

@Service
@AllArgsConstructor
@Slf4j
public class OkexServiceImpl implements OkexService {
    private final OkexConstants okexConstants;


    /**
     *
     * @param
     */
    public void contractContractInfo(){

        log.info("请求ok交割合约");
        //post-json
        String s = OkHttpUtils.httpGet(okexConstants.getUrl()+okexConstants.getContractContractInfo());
        log.info(s);
        OkexDto okexDto = JSONObject.parseObject(s, OkexDto.class);

        log.info(okexDto.toString());
        log.info("入库就自己写吧参数就是这个 data");
    }

    /**
     *
     * @param
     */
    @Override
    public void swapContractInfo() {

        log.info("请求永续OK合约");
        //post-json 请求永续合约
        String s = OkHttpUtils.httpGet(okexConstants.getUrl()+okexConstants.getSwapContractInfo());
        log.info(s);
        OkexSwapContractInfoDto swapContractInfoDto = JSONObject.parseObject(s, OkexSwapContractInfoDto.class);

        log.info(swapContractInfoDto.toString());
        log.info("入库就自己写吧参数就是这个 data");
    }


}
