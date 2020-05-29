package com.example.demo;

import com.example.demo.value.config.StaffConfig;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    StaffConfig staffConfig;

    @Value("${jasypt.encryptor.password}")
    String yan;
    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void contextLoads() {

        //加密方法

        System.out.println(stringEncryptor.encrypt("123456"));
        //解密方法
        System.out.println(yan);
        System.out.println(stringEncryptor.decrypt("McaserZcmcoz70GBojBI9032gP+jvB9cby2TmZ5JHRs2BS00jrZy73f1SCmzGSkU"));

    }


    /**
     * 验证@Value和spEL配置加载对应
     */
    @Test
   void verificationValue() {
        System.out.println("用户名称集合List："+staffConfig.getStaffNames());
        System.out.println("用户名称集合List下的第一个参数："+staffConfig.getFirstStaffName());
        System.out.println("输出map类型的所有年龄："+staffConfig.getStaffAge());
        System.out.println("输出map类型的key为one的年龄："+staffConfig.getStaffAgeOne());
        System.out.println("输出map类型的年龄如果没有展示的默认年龄："+staffConfig.getAgeWithDefaultValue());
        System.out.println("输出javaHome："+staffConfig.getJavaHome());
        System.out.println("输出当前工作目录："+staffConfig.getUserDir());
        System.out.println("jasypt加密后获取的password："+staffConfig.getPassword());

    }

}
