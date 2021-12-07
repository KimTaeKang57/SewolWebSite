package com.Sewol.Sewol.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Student {

    //  저장 조회, entity -> DTO 반환 -> POSTMAN으로 DTO로 받아서 //조회도 dto로
    // 서버를 api로 만들어서 restcontroller로 계속 돌 /// okok
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;
    private String studentName;
    //학년 반
    private String grade;
    private String classNumber;

    protected Student() {
    }

    public Student(String studentName) {
        this.studentName = studentName;
    }

    public Student(String studentName, String classNumber) {
        this.studentName = studentName;
        this.classNumber = classNumber;
    }

    public Student(String studentName, String grade, String classNumber) {
        this.studentName = studentName;
        this.grade = grade;
        this.classNumber = classNumber;
    }


}
