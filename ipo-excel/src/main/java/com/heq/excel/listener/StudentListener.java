package com.heq.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.heq.excel.entity.Student;
import com.heq.excel.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Scope("prototype")
public class StudentListener extends AnalysisEventListener<Student> {

    @Autowired
    private StudentService studentService;
    private List<Student> studentList = new ArrayList<>();

    /**
     * 每次读一行，都会调用该方法
     *
     * @param student
     * @param context
     */
    public void invoke(Student student, AnalysisContext context) {
        studentList.add(student);
        log.info(student.toString());
        if (studentList.size() % 5 == 0) {
            studentService.readExcel(studentList);
            studentList.clear();
        }
    }

    /**
     * 读取完整个文档之后调用该方法
     *
     * @param context
     */
    public void doAfterAllAnalysed(AnalysisContext context) {
        studentService.readExcel(studentList);
        studentList.clear();
    }
}
