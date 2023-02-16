package com.dailycodebuffer.spring.data.jpatutorial.repository;

import com.dailycodebuffer.spring.data.jpatutorial.entity.Guardian;
import com.dailycodebuffer.spring.data.jpatutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .emailId("shabbir@gmail.com")
                //.guardianName("Nikhil")
                //.guardianEmail("nikhil@gmail.com")
                //.guardianMobile("1234567890")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .name("Nikhil")
                .email("nikhil@gmail.com")
                .mobile("1234567890")
                .build();

        Student student = Student.builder()
                .firstName("asd")
                .emailId("123@gmail.com")
                .lastName("asd123")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> students = studentRepository.findAll();
        System.out.println("studentList = " + students);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> students = studentRepository.findByFirstName("John");
        System.out.println("studentList = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("J");
        System.out.println("studentList = " + students);
    }


    @Test
    public void printStudentByLastNameNotNull() {
        List<Student> students = studentRepository.findByLastNameNotNull();
        System.out.println("studentList = " + students);
    }

    @Test
    public void printStudentByGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("Nikhil");
        System.out.println("studentList = " + students);
    }
}
    
    // TODO: 5:30:00 JPA @Query Annotation