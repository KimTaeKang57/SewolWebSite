package com.Sewol.Sewol.repository;

import com.Sewol.Sewol.dto.StudentDto;
import com.Sewol.Sewol.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // 모든 학생 조회
    @Query("select s from Student s")
    List<Student> findAllStudent();

    // 정확히 일치하는 이름 하나로 조회
    @Query("select s from Student s where s.studentName = :studentName")
    List<Student> findStudentExact(@Param("studentName")String studentName);

    // 포함하는 이름 하나로 조회
    @Query("select s from Student s where s.studentName Like %:studentName%")
    List<Student> findStudentInclude(@Param("studentName")String studentName);

    // 검색한 학년 모두 조회
    @Query("Select s from Student s where s.grade = :grade")
    List<Student> findByGrade(String grade);

    // 검색한 반 모두 조회
    @Query("Select s from Student s where s.classNumber = :classNumber")
    List<Student> findByClassNumber(String classNumber);

    // DTO로 조회
    //@Query("select new Users.study.sewolProject.Sewol.dto.StudentDto(s.studentName) from Student s")
    //List<StudentDto> findStudentDto();

    /**
    // 모든 학생 조회
    @Query("select s from Student s")
    Page<Student> findAllStudent(Pageable pageable);

    // 정확히 일치하는 이름 하나로 조회
    @Query("select s from Student s where s.studentName = :studentName")
    Page<Student> findStudentExact(String studentName, Pageable pageable);

    // 포함하는 이름 하나로 조회
    @Query("select s from Student s where s.studentName Like %:studentName%")
    Page<Student> findStudentInclude(String studentName, Pageable pageable);

    // 검색한 학년 모두 조회
    @Query("Select s from Student s where s.grade = :grade")
    Page<Student> findByGrade(String grade, Pageable pageable);

    // 검색한 반 모두 조회
    @Query("Select s from Student s where s.classNumber = :classNumber")
    Page<Student> findByClassNumber(String classNumber, Pageable pageable);

    // DTO로 조회
    //@Query("select new Users.study.sewolProject.Sewol.dto.StudentDto(s.studentName) from Student s")
    //List<StudentDto> findStudentDto();
    */
}
