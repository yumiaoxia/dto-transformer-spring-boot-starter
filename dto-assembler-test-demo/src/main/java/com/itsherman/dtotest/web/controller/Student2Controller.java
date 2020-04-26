package com.itsherman.dtotest.web.controller;

import com.itsherman.dtoassembler.utils.DtoTransFormer;
import com.itsherman.dtotest.domain.Course;
import com.itsherman.dtotest.domain.Student2;
import com.itsherman.dtotest.web.dto.Student2Dto;
import com.itsherman.dtotest.web.dto.Student2Dto2;
import com.itsherman.web.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "高级功能测试")
@RestController
@RequestMapping("/api/student2")
public class Student2Controller {

    @ApiOperation("属性带注解@ViewDto，属性是接口")
    @GetMapping("/detail1/{id}")
    public ApiResponse<Student2Dto> getDetail1(@PathVariable("id") Long id) {
        Student2 student2 = new Student2();
        student2.setId(id);
        student2.setName("文华");

        Course course1 = new Course();
        course1.setCourseName("语文");
        course1.setScore(78.5D);

        Course course2 = new Course();
        course2.setCourseName("数学");
        course2.setScore(80D);

        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        student2.setCourses(courses);
        Student2Dto result = DtoTransFormer.to(Student2Dto.class).apply(student2);
        return ApiResponse.createSuccess(result);
    }

    @ApiOperation("属性带注解@ViewDto,属性是类")
    @GetMapping("/detail2/{id}")
    public ApiResponse<Student2Dto2> getDetail2(@PathVariable("id") Long id) {
        Student2 student2 = new Student2();
        student2.setId(id);
        student2.setName("蓝文");

        Course course1 = new Course();
        course1.setCourseName("语文");
        course1.setScore(79.5D);

        Course course2 = new Course();
        course2.setCourseName("数学");
        course2.setScore(80.5D);

        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        student2.setCourses(courses);
        Student2Dto2 result = DtoTransFormer.to(Student2Dto2.class).apply(student2);
        return ApiResponse.createSuccess(result);
    }
}
