package com.Sewol.Sewol.repository;

import com.Sewol.Sewol.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void 회원가입() {
        Student student = new Student("김태강");
        Student saveOneStudent = studentRepository.save(student);

        Student findById = studentRepository.findById(student.getId()).get();

        assertThat(findById.getStudentName()).isEqualTo(saveOneStudent.getStudentName());
    }

    /**
    @Test
    public void 정확한이름으로_조회() {
        Student student1 = new Student("student1");
        Student student2 = new Student("student2");

        Student save1 = studentRepository.save(student1);
        studentRepository.save(student2);

        List<Student> studentExact = studentRepository.findStudentExact(save1.getStudentName());
        for (Student student : studentExact) {
            System.out.println("student = " + student);
        }
    }
    */

    /**
    @Test
    public void 이름을포함하여_조회() {
        Student student1 = new Student("김태강");
        Student student2 = new Student("김태현");
        Student student3 = new Student("박성환");

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        List<Student> include = studentRepository.findStudentInclude("강");
        for (Student student : include) {
            System.out.println("student.getStudentName() = " + student.getStudentName());        
        }   
    }
    */

    /**
    @Test
    public void 조회한_학년_전체조회() {
        Student student1 = new Student("student1", "3", "3");
        Student student2 = new Student("student2", "2", "3");
        Student student3 = new Student("student3", "2", "2");

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        List<Student> byGrade = studentRepository.findByGrade("2");
        for (Student student : byGrade) {
            System.out.println("student.getStudentName() = " + student.getStudentName());
        }
    }
    */

    /**
    @Test
    public void 조회한_반_전체조회() {
        Student student1 = new Student("student1", "3");
        Student student2 = new Student("student2", "3");
        Student student3 = new Student("student3", "3");

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        List<Student> byClassNumber = studentRepository.findByClassNumber("3");
        for (Student student : byClassNumber) {
            System.out.println("student.getStudentName() = " + student.getStudentName());
        }
        assertThat(byClassNumber.size()).isEqualTo(3);
    }
    */
}