package com.dailycodebuffer.spring.data.jpatutorial.repository;

import com.dailycodebuffer.spring.data.jpatutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpatutorial.entity.Student;
import com.dailycodebuffer.spring.data.jpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printAllCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {

        Teacher teacher = Teacher
                .builder()
                .firstName("pok")
                .lastName("hengly")
                .build();

        Course course = Course
                .builder()
                .title("Java")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {

        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);

        List<Course> courses =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getContent();

        long totalElements =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getTotalElements();

        int totalPages =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getTotalPages();

        System.out.println("courses = " + courses);
        System.out.println("totalElements = " + totalElements);
        System.out.println("totalPages = " + totalPages);
    }

    @Test
    public void findAllSorting() {

        Pageable sortByTitle =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                );

        Pageable sortByCreditsDesc =
                PageRequest.of(
                        0,
                        3,
                        Sort.by("credit").ascending()
                );

        Pageable sortByTitleAndCreditsDesc =
                PageRequest.of(
                        0,
                        3,
                        Sort.by("title")
                                .descending()
                                .and(Sort.by("credit"))
                );

        List<Course> courses =
                courseRepository.findAll(sortByCreditsDesc).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void printFindByTitleContaining() {

        Pageable firstPageWithTwoRecords =
                PageRequest.of(0, 10);

        List<Course> courses =
                courseRepository.findByTitleContaining(
                        "Java", firstPageWithTwoRecords).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("NUTH")
                .lastName("SOK")
                .build();

        Student student = Student.builder()
                .firstName("SOK")
                .lastName("NUTH")
                .emailId("N@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(6)
                .teacher(teacher)
                .build();

        course.addStudent(student);

        courseRepository.save(course);
    }
}