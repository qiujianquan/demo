package com.example.demo.validatedandvalid.Controller;

import com.example.demo.validatedandvalid.entity.Student;
import com.example.demo.validatedandvalid.service.GroupA;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StrudentController {
    @PostMapping("addStudent")
    public String addStu( @Validated({GroupA.class})@RequestBody @Valid Student student){
        return "add student success";
    }
}
