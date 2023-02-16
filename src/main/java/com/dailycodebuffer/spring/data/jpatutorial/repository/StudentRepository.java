package com.dailycodebuffer.spring.data.jpatutorial.repository;

import com.dailycodebuffer.spring.data.jpatutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);
    List<Student> findByFirstNameContaining(String name);
    List<Student> findByLastNameNotNull();
    List<Student> findByGuardianName(String guardianName);
    List<Student> findByLastNameAndFirstName(String firstName, String lastName);

    // TODO: 5:30:00 JPA @Query Annotation
}
