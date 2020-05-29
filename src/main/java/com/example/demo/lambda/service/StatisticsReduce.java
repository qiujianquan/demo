package com.example.demo.lambda.service;

import java.math.BigDecimal;
import java.util.List;

public interface StatisticsReduce {

   BigDecimal call(List<BigDecimal > c);

}
