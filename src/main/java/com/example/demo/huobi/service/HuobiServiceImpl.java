package com.example.demo.huobi.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.huobi.common.HuobiConstants;
import com.example.demo.huobi.dto.HuoBiReqDto;
import com.example.demo.huobi.dto.HuobiDto;
import com.example.demo.huobi.dto.SwapContractInfoDto;
import com.example.demo.huobi.dto.SwapContractInfoReqDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import util.okhttp.OkHttpUtils;

@Service
@AllArgsConstructor
@Slf4j
public class HuobiServiceImpl implements HuobiService {
    private final HuobiConstants huobiConstants;


    /**
     *
     * @param huoBiReqDto 入参里是合约的参数 替换一下 for循环 ？后直接拼参数
     */
    public void contractContractInfo(HuoBiReqDto huoBiReqDto){

        log.info("请求火币交割合约");
        //post-json
        String s = OkHttpUtils.httpGet(huobiConstants.getUrl()+huobiConstants.getContractContractInfo());
        log.info(s);
        HuobiDto huobiDto = JSONObject.parseObject(s, HuobiDto.class);

        log.info(huobiDto.getData().toString());
        log.info("入库就自己写吧参数就是这个 data");
    }

    /**
     *
     * @param huoBiReqDto 入参里是合约参数 替换一下吧  我就直接用默认的了
     */
    @Override
    public void swapContractInfo(SwapContractInfoReqDto huoBiReqDto) {

        log.info("请求永续火币合约");
        //post-json 请求永续火币合约
        String s = OkHttpUtils.httpGet(huobiConstants.getUrl()+huobiConstants.getSwapContractInfo());
        log.info(s);
        SwapContractInfoDto swapContractInfoDto = JSONObject.parseObject(s, SwapContractInfoDto.class);

        log.info(swapContractInfoDto.getData().toString());
        log.info("入库就自己写吧参数就是这个 data");
    }


}
