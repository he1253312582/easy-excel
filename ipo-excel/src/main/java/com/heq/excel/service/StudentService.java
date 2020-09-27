package com.heq.excel.service;

import com.heq.excel.entity.Student;

import java.util.List;

public interface StudentService {

    void readExcel(List<Student> studentList);
}
