package com.example.demo.lambda.util;

import com.example.demo.lambda.constants.StatisticsType;
import com.example.demo.lambda.service.Calculation;
import com.example.demo.lambda.service.StatisticsReduce;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;


/**
 * @author SeasonSoy

 */
@Component
@NoArgsConstructor
public final class MatheUtil {

    /**
     * 统计Map
     */
    private static final Map<StatisticsType, StatisticsReduce> STATISTICS_MAP = new HashMap<>();

    private static final Map<StatisticsType, Calculation> CALCULATION_MAP = new HashMap<>();

    /**
     * 数学统计值，返回一种统计结果
     *
     * @param type 统计类型
     * @param c    集合，封装类型为BigDecimal类型
     * @return BigDecimal 统计结果
     * @throws NullPointerException if collection {@code c} is null
     * @author SeasonSoy
     */
    public static BigDecimal call(StatisticsType type, List<BigDecimal> c) {

        List<BigDecimal> bdList = new ArrayList<>(c.size());
        for (Number number : c) {
            if (number instanceof BigDecimal) {
                bdList.add((BigDecimal) number);
            } else {
                bdList.add(BigDecimal.valueOf(number.doubleValue()));
            }
        }
        return STATISTICS_MAP.get(type).call(bdList);
    }



    public static BigDecimal call(StatisticsType type, BigDecimal a,BigDecimal b ) {

        return CALCULATION_MAP.get(type).call(a,b);
    }






    ////////////////////////////////// 实现 //////////////////////////////////////
    // 策略池初始化
    static {

        // 求均值
        STATISTICS_MAP.put(StatisticsType.AVERAGE, c -> {


            BigDecimal sum = c.stream().reduce((price, total) -> total.add(price)).orElse(BigDecimal.valueOf(0));
            return sum.divide(BigDecimal.valueOf(c.size()), 2, BigDecimal.ROUND_HALF_UP);
        });
        // 求最大值
        STATISTICS_MAP.put(StatisticsType.MAX, c -> getSortedIndexedBd(c, c.size() - 1));
        // 求最小值
        STATISTICS_MAP.put(StatisticsType.MIN, c -> getSortedIndexedBd(c, 0));
        // 求和
        STATISTICS_MAP.put(StatisticsType.SUM, c ->
                c.stream().reduce((price, total) -> total.add(price)).orElse(BigDecimal.valueOf(0)));



        // 求两个数相加
        CALCULATION_MAP.put(StatisticsType.ADD, (a,b) -> a.add(b));
        // 求两个数相减
        CALCULATION_MAP.put(StatisticsType.SUB, (a, b) ->a.subtract(b));
        // 求最小值
        CALCULATION_MAP.put(StatisticsType.MUL,  (a, b) ->a.multiply(b));
        // 求除数
        CALCULATION_MAP.put(StatisticsType.DIV,(a, b)  -> a.divide(b));


    }

    /**
     * 将集合升序排序，取出目标索引位置的数
     *
     * @param c     集合
     * @param index 索引位置
     * @return Bd
     * @throws NullPointerException if collection {@code c} is null
     * @author SeasonSoy
     */
    private static BigDecimal getSortedIndexedBd(List<BigDecimal> c, int index) {

        BigDecimal bd;
        Collections.sort(c);
        bd = c.size() == 0 ? null : c.get(index);
        return bd;
    }



}

