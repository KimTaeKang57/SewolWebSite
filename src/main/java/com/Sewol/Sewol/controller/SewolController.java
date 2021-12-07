package com.Sewol.Sewol.controller;

import com.Sewol.Sewol.config.Header;
import com.Sewol.Sewol.dto.DateDto;
import com.Sewol.Sewol.dto.PersonDto;
import com.Sewol.Sewol.dto.StudentDto;
import com.Sewol.Sewol.entity.Job;
import com.Sewol.Sewol.entity.Person;
import com.Sewol.Sewol.entity.Student;
import com.Sewol.Sewol.service.PersonService;
import com.Sewol.Sewol.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/Sewol")
public class SewolController extends HttpServlet {

    private final ModelMapper modelMapper;
    private final StudentService studentService;
    private final PersonService personService;

    @GetMapping
    @Transactional
    public DateDto checkDate(HttpSession session) {

        DateDto dateDto = new DateDto();
        LocalDateTime now = LocalDateTime.now();

        // 세션키가 방문자수 Map 에 없으면 추가 && +1

        String dayDiff = dateDto.dayDiff();
        dateDto.setLocalDateTime(now);
        dateDto.setDayDiff(dayDiff);
        // dateDto.setCount(visitorService.getTodayVisitor());

        // System.out.println("현재 방문자 수: " + visitorService.getTodayVisitor());

        return dateDto;
    }

    //사람 저장
    @PostMapping("/personSave")
    public Long personDtoSave(@RequestBody Person person){

        Long personId = personService.personSave(person);
        return personId;
    }

    @PutMapping("/personUpdate")
    public Long personDtoUpdate(@RequestBody Person person){
        Long personId = personService.personUpdate(person);
        return personId;
    }

    //학생저장
    @PostMapping("/studentSave")
    public Long studentSave(@RequestBody Student student){

        Long studentId = studentService.studentSave(student);
        return studentId;
    }

    @PutMapping("/studentUpdate")
    public Long studentUpdate(@RequestBody Student student){
        Long studentId = studentService.studentUpdate(student);
        return studentId;
    }

    @GetMapping("/student/list")
    public Header<List<StudentDto>> findAllStudent() {
        List<Student> studentList = studentService.findAllStudent();

        if (studentList.isEmpty()) {
            return Header.ERROR("리스트가 비어있음");
        } else {
            List<StudentDto> list
                    = studentList.stream().map(p -> modelMapper.map(p, StudentDto.class)).collect(Collectors.toList());
            extractedDayDiffForStudent(list);
            return Header.OK(list, "전체 리스트가 정상적으로 출력");
        }
    }

    @GetMapping("/student/exact")
    public Header<List<StudentDto>> findStudentExactName(@RequestParam(value ="studentName") String studentName) {
        List<Student> studentList = studentService.findStudentExact(studentName);

        if (studentList.isEmpty()) {
            return Header.ERROR("입력한 이름을 정확히 가진 학생이 존재하지 않음.");
        } else {
            List<StudentDto> students
                    = studentList.stream().map(p -> modelMapper.map(p, StudentDto.class)).collect(Collectors.toList());
            extractedDayDiffForStudent(students);
            return Header.OK(students, "입력한 이름을 정확히 가진 학생이 존재");
        }
    }

    @GetMapping("/student/include")
    public Header<List<StudentDto>> findStudentIncludeName(@RequestParam(value ="studentName") String studentName) {
        List<Student> studentList = studentService.findStudentInclude(studentName);

        if (studentList.isEmpty()) {
            return Header.ERROR("입력한 이름을 포함하는 학생이 존재하지 않음");
        } else {

            List<StudentDto> collect
                    = studentList.stream().map(p -> modelMapper.map(p, StudentDto.class)).collect(Collectors.toList());
            extractedDayDiffForStudent(collect);
            return Header.OK(collect, "입력한 이름을 포함하는 학생이 존재 함");
        }
    }

    @GetMapping("/student/byClass")
    public Header<List<StudentDto>> findStudentByClassNum(@RequestParam("classNum") String classNumber) {
        List<Student> studentList = studentService.findStudentByClass(classNumber);

        if (studentList.isEmpty()) {
            return Header.ERROR("입력한 반에 해당하는 학생이 존재하지 않음");
        } else {
            List<StudentDto> collect
                    = studentList.stream().map(p -> modelMapper.map(p, StudentDto.class)).collect(Collectors.toList());
            extractedDayDiffForStudent(collect);
            return Header.OK(collect, "입력한 반에 해당하는 학생이 존재 함");
        }
    }

    @GetMapping("/person")
    public Header<List<PersonDto>> findAllPerson() {
        List<Person> personList = personService.findAllPerson();

        if (personList.isEmpty()) {
            return Header.ERROR("사람들이 비어있음");
        } else {
            List<PersonDto> persons
                    = personList.stream().map(p -> modelMapper.map(p, PersonDto.class)).collect(Collectors.toList());
            extractedDayDiffForPerson(persons);
            return Header.OK(persons, "전체 리스트 출력");
        }
    }

    @GetMapping("/person/exact")
    public Header<List<PersonDto>> findByExactName(@RequestParam(value = "personName") String personName) {
        List<Person> persons = personService.findPersonExact(personName);

        if (persons.isEmpty()) {
            return Header.ERROR("입력한 이름을 정확히 가진 사람이 없음");
        } else {
            List<PersonDto> collect
                    = persons.stream().map(p -> modelMapper.map(p, PersonDto.class)).collect(Collectors.toList());
            extractedDayDiffForPerson(collect);
            return Header.OK(collect, "입려한 이름을 정확히 가진 사람이 있음");
        }
    }

    @GetMapping("/person/include")
    public Header<List<PersonDto>> findByIncludeName(@RequestParam(value = "personName")String personName) {
        List<Person> persons = personService.findPersonInclude(personName);

        if (persons.isEmpty()) {
            return Header.ERROR("입력한 이름을 포함하는 사람이 없음");
        } else {
            List<PersonDto> collect
                    = persons.stream().map(p -> modelMapper.map(p, PersonDto.class)).collect(Collectors.toList());
            extractedDayDiffForPerson(collect);

            return Header.OK(collect, "입력한 이름을 포함하는 사람이 있음");
        }
    }

    @GetMapping("/person/byJob")
    public Header<List<PersonDto>> findByJob(@RequestParam("job") String job) {
        // 파라미터로 받은 String 데이터의 데이터타입을 ENUM 타입으로 바꾸어줌
        List<Person> persons = personService.findPersonJob(Job.valueOf(job));

        if (persons.isEmpty()) {
            return Header.ERROR("입력한 직업에 해당하는 사람이 없음.");
        } else {
            List<PersonDto> collect
                    = persons.stream().map(p -> modelMapper.map(p, PersonDto.class)).collect(Collectors.toList());
            extractedDayDiffForPerson(collect);
            return Header.OK(collect, "입력한 직업에 해당하는 사람이 있음");
        }
    }

    private void extractedDayDiffForStudent(List<StudentDto> allStudent) {
        for (StudentDto student : allStudent) {
            student.setLocalDateTime(LocalDateTime.now());
            String dayDiff = student.dayDiff();
            student.setDayDiff(dayDiff);
        }
    }

    private void extractedDayDiffForPerson(List<PersonDto> personList) {
        for (PersonDto person : personList) {
            person.setLocalDateTime(LocalDateTime.now());
            String dayDiff = person.dayDiff();
            person.setDayDiff(dayDiff);
        }
    }

    @GetMapping("/time")
    public DateDto findTime() {
        DateDto dateDto = new DateDto();
        LocalDateTime date = dateDto.getLocalDateTime().now();
        String timeDate = dateDto.dayDiff();
        dateDto.setLocalDateTime(date);
        dateDto.setDayDiff(timeDate);
        return dateDto;
    }

    /**
     @GetMapping("/student/list") public Page<StudentDto> findAllStudentPaging(@PageableDefault(sort = "studentName", direction = Sort.Direction.ASC)
     Pageable pageable) {
     Page<Student> page = studentService.findAllStudent(pageable);
     Page<StudentDto> map
     = page.map(student ->
     new StudentDto(student.getId(), student.getStudentName(), student.getGrade(), student.getClassNumber()));

     return map;
     }

     @PostMapping("/student/exact") public Page<StudentDto> findStudentExactNamePaging(@RequestParam("name") String studentName,
     @PageableDefault(sort = "studentName", direction = Sort.Direction.ASC)
     Pageable pageable) {
     Page<Student> page = studentService.findStudentExact(studentName, pageable);

     Page<StudentDto> map = page.map(student ->
     new StudentDto(student.getId(), student.getStudentName(), student.getGrade(), student.getClassNumber()));

     return map;
     }

     @PostMapping("/student/include") public Page<StudentDto> findStudentIncludeName(@RequestParam("name") String studentName,
     @PageableDefault(sort = "studentName", direction = Sort.Direction.ASC)
     Pageable pageable) {
     Page<Student> page = studentService.findStudentInclude(studentName, pageable);

     Page<StudentDto> map = page.map(student ->
     new StudentDto(student.getId(), student.getStudentName(), student.getGrade(), student.getClassNumber()));


     return map;
     }

     @PostMapping("/student/byGrade") public Page<StudentDto> findStudentByGrade(@RequestParam("grade") String grade,
     @PageableDefault(sort = "studentName", direction = Sort.Direction.ASC)
     Pageable pageable) {
     Page<Student> page = studentService.findStudentByGrade(grade, pageable);

     Page<StudentDto> map = page.map(student ->
     new StudentDto(student.getId(), student.getStudentName(), student.getGrade(), student.getClassNumber()));

     return map;
     }

     @PostMapping("/student/byClass") public Page<StudentDto> findStudentByClassNum(@RequestParam("classNum") String classNumber,
     @PageableDefault(sort = "studentName", direction = Sort.Direction.ASC)
     Pageable pageable) {
     Page<Student> page = studentService.findStudentByClass(classNumber, pageable);

     Page<StudentDto> map = page.map(student ->
     new StudentDto(student.getId(), student.getStudentName(), student.getGrade(), student.getClassNumber()));

     return map;
     }

     @GetMapping("/person") public Page<PersonDto> findAllPersonPaging(@PageableDefault(sort = "personName", direction = Sort.Direction.ASC)
     Pageable pageable) {
     Page<Person> page = personService.findAllPerson(pageable);

     Page<PersonDto> map
     = page.map(person -> new PersonDto(person.getId(), person.getPersonName(), person.getJob()));

     return map;
     }

     @PostMapping("/person/exact") public Page<PersonDto> findByExactNamePaging(@RequestParam("name") String personName,
     @PageableDefault(sort = "personName", direction = Sort.Direction.ASC)
     Pageable pageable) {
     Page<Person> page = personService.findPersonExact(personName, pageable);

     Page<PersonDto> map =
     page.map(person -> new PersonDto(person.getId(), person.getPersonName(), person.getJob()));

     return map;
     }

     @PostMapping("/person/include") public Page<PersonDto> findByIncludeNamePaging(@RequestParam("name") String personName,
     @PageableDefault(sort = "personName", direction = Sort.Direction.ASC)
     Pageable pageable) {
     Page<Person> page = personService.findPersonInclude(personName, pageable);

     Page<PersonDto> map =
     page.map(person -> new PersonDto(person.getId(), person.getPersonName(), person.getJob()));

     return map;
     }

     @PostMapping("/person/byJob") public Page<PersonDto> findByJobPaging(@RequestParam("job") String job,
     @PageableDefault(sort = "personName", direction = Sort.Direction.ASC)
     Pageable pageable) {
     // 파라미터로 받은 String 데이터의 데이터타입을 ENUM 타입으로 바꾸어줌
     Page<Person> page = personService.findPersonJob(Job.valueOf(job), pageable);
     Page<PersonDto> map
     = page.map(person -> new PersonDto(person.getId(), person.getPersonName(), person.getJob()));

     return map;
     }
     */

}
