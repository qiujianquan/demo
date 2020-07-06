package com.example.demo.huobi.service;

import com.example.demo.huobi.dto.HuoBiReqDto;
import com.example.demo.huobi.dto.SwapContractInfoReqDto;

public interface HuobiService {

    /**
     * 获取货币合约信息
     * @param huoBiReqDto
     */
    public void contractContractInfo(HuoBiReqDto huoBiReqDto);





    public void swapContractInfo(SwapContractInfoReqDto huoBiReqDto);
}
