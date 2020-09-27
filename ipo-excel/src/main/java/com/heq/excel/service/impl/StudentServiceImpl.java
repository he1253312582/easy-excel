package com.heq.excel.service.impl;


import com.heq.excel.entity.Student;
import com.heq.excel.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
@Slf4j
public class StudentServiceImpl implements StudentService {


    @Override
    public void readExcel(List<Student> studentList) {
        for (Student student : studentList) {
            log.info(student.toString());
        }
    }
}
