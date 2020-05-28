package com.example.demo.validatedandvalid.entity;

import com.example.demo.validatedandvalid.service.GroupA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class Student  {
    @NotBlank(message = "用户名不能为空")
    private String name;
    //只在分组为IGroupB的情况下进行验证
    @Min(value = 18, message = "年龄不能小于18岁", groups = {GroupA.class})
    private Integer age;
    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "手机号格式错误")
    private String phone;

}
