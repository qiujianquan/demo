package com.example.demo.value.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


import java.util.List;
import java.util.Map;

@Getter
@Configuration
@PropertySource(name = "application.yml", value = "classpath:application.yml")
@ConfigurationProperties(prefix = "staff")
public class StaffConfig {
    //使用SpEL读取staff.properties配置文件
    @Value("#{'${staff.names}'.split(',')}")
    private List<String> staffNames;
    //使用SpEL读取staff.names集合的第一个参数
    @Value ("#{'${staff.names}'.split(',')[0]}")
    private String firstStaffName;
    //使用SpEL读取staff.age并转换成map形式的键值对
    @Value ("#{${staff.age}}")
    private Map<String, Integer> staffAge;
    //使用SpEL读取staff.age并转换成map形式的键值对的key获取对应的value
    @Value ("#{${staff.age}.one}")
    private String staffAgeOne;
    //使用SpEL读取staff.age并转换成map形式的键值对的key获取对应的value如果没有内容赋予默认值0
    @Value ("#{${staff.age}['zero'] ?: 0}")
    private Integer ageWithDefaultValue;

    //使用SpEL读取java环境变量
    @Value ("#{systemProperties['java.home']}")
    private String javaHome;
    //使用SpEL读取工作目录
    @Value ("#{systemProperties['user.dir']}")
    private String userDir;






}
