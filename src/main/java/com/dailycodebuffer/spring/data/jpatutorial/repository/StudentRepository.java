package com.dailycodebuffer.spring.data.jpatutorial.repository;

import com.dailycodebuffer.spring.data.jpatutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);
    List<Student> findByFirstNameContaining(String name);
    List<Student> findByLastNameNotNull();
    List<Student> findByGuardianName(String guardianName);
    Student findByLastNameAndFirstName(String firstName, String lastName);

    // JPQL
    @Query("SELECT s FROM Student s WHERE s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    @Query("SELECT s.firstName FROM Student s WHERE s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    // Native Query
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address =?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);

    //Native Named Param
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNameParam(@Param("emailId") String emailId);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE tbl_student s SET s.first_name = :firstName WHERE s.email_address = :emailId",
            nativeQuery = true
    )
    int updateStudentFirstNameByEmailId(@Param("firstName") String firstName, @Param("emailId") String emailId);


    // TODO: 5:48:00 JPA One to One Relationship
}
