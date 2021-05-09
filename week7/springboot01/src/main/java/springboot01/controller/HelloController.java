package springboot01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springboot01.bean.Student;

@Component
public class HelloController {

    private Student student;

    @Autowired
    HelloController(Student student) {
        this.student = student;
    }

}
