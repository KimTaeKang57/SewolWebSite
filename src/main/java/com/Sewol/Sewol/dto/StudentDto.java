package com.Sewol.Sewol.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class StudentDto {

    private Long id;
    private String studentName;
    private String grade;
    private String classNumber;

    @JsonIgnore
    private LocalDateTime localDateTime;
    @JsonIgnore
    private String dayDiff;

    protected StudentDto() {

    }

    public StudentDto(Long id, String studentName, String grade, String classNumber) {
        this.id = id;
        this.studentName = studentName;
        this.grade = grade;
        this.classNumber = classNumber;
    }

    public StudentDto(Long id, String studentName, String grade, String classNumber, String dayDiff) {
        this.id = id;
        this.studentName = studentName;
        this.grade = grade;
        this.classNumber = classNumber;
        this.dayDiff = dayDiff;
    }

    @SneakyThrows
    public String dayDiff() {
        SimpleDateFormat NowDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat SewolDateFormat = new SimpleDateFormat("2014/04/18");

        String NowDate = NowDateFormat.format(new Date());
        String sewolDate = SewolDateFormat.format(new Date());

        Date now = NowDateFormat.parse(NowDate);
        Date sewol = NowDateFormat.parse(sewolDate);

        long diffSec = (now.getTime() - sewol.getTime()) / 1000; //초 차이
        long diffDays = diffSec / (24 * 60 * 60); //일자수 차이

        String day = String.valueOf(diffDays);

        return day;
    }
}
