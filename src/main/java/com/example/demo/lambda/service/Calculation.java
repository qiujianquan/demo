package com.example.demo.lambda.service;

import java.math.BigDecimal;

public interface Calculation {
  BigDecimal call(BigDecimal a, BigDecimal b);
}
