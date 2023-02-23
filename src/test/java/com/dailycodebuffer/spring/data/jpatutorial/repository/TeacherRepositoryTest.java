package com.dailycodebuffer.spring.data.jpatutorial.repository;

import com.dailycodebuffer.spring.data.jpatutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {

        Course courseJava = Course.builder()
                .title("Java")
                .credit(6)
                .build();

        Course  courseSpring = Course.builder()
                .title("Spring")
                .credit(4)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("chea")
                .lastName("kdoy")
                .courses(List.of(courseJava, courseSpring))
                .build();

        teacherRepository.save(teacher);
    }
}

// TODO - 6:18:00 Save Teacher with Courses