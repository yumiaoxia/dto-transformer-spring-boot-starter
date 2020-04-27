package com.itsherman.dtotest.web.controller;

import com.itsherman.dtoassembler.annotations.ViewSelector;
import com.itsherman.dtoassembler.utils.DtoTransFormer;
import com.itsherman.dtotest.entity.Student;
import com.itsherman.dtotest.entity.Teacher;
import com.itsherman.dtotest.service.StudentService;
import com.itsherman.dtotest.web.command.StudentCreateCommand;
import com.itsherman.dtotest.web.command.TeacherCreateCommand;
import com.itsherman.dtotest.web.dto.StudentDo;
import com.itsherman.dtotest.web.dto.StudentDto;
import com.itsherman.web.common.request.ApiPageRequest;
import com.itsherman.web.common.request.ApiRequest;
import com.itsherman.web.common.response.ApiPageResponse;
import com.itsherman.web.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Api(tags = "Dto Jpa懒加载测试")
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiOperation("创建学生")
    @PostMapping("/create")
    public ApiResponse<Void> create(@RequestBody ApiRequest<StudentCreateCommand> request) {
        StudentCreateCommand studentCreateCommand = request.getCommand();
        Set<TeacherCreateCommand> teacherCreateCommands = studentCreateCommand.getTeachers();
        Student student = new Student();
        BeanUtils.copyProperties(request.getCommand(), student, "teachers");

        Set<Teacher> teachers = new HashSet<>();
        for (TeacherCreateCommand teacherCreateCommand : teacherCreateCommands) {
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacherCreateCommand, teacher);
            teacher.setStudents(Collections.singleton(student));
            teachers.add(teacher);
        }
        student.setTeachers(teachers);
        studentService.create(student);
        return ApiResponse.createSuccess();
    }

    @ApiOperation("获取学生详情")
    @GetMapping("/detail/{id}")
    public ApiResponse<StudentDto> detail(@PathVariable Long id) {
        Student student = studentService.getById(id);
        return ApiResponse.createSuccess(DtoTransFormer.to(StudentDto.class).apply(student));
    }


    @ViewSelector(selectView = StudentDto.BaseStudent.class)
    @ApiOperation("获取学生详情,接口带@DtoView注解")
    @GetMapping("/detail2/{id}")
    public ApiResponse<StudentDto> detail2(@PathVariable Long id) {
        Student student = studentService.getById(id);
        return ApiResponse.createSuccess(DtoTransFormer.to(StudentDto.class).apply(student));
    }

    @ApiOperation("获取学生详情,do类中带集合Dto接口")
    @GetMapping("/detail3/{id}")
    public ApiResponse<StudentDo> detail3(@PathVariable Long id) {
        Student student = studentService.getById(id);
        return ApiResponse.createSuccess(DtoTransFormer.to(StudentDo.class).apply(student));
    }

    @ApiOperation("获取学生列表")
    @PostMapping("/list")
    public ApiPageResponse<List<StudentDto>> list(@RequestBody @Validated ApiPageRequest<Void> request) {
        Page<Student> studentPage = studentService.findAllByPage(request.getPageable());
        Page<StudentDto> result = DtoTransFormer.toPage(StudentDto.class).apply(studentPage);
        return ApiPageResponse.createPageSuccess(result);
    }
}
