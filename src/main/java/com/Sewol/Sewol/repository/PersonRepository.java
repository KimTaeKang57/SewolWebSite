package com.Sewol.Sewol.repository;

import com.Sewol.Sewol.dto.PersonDto;
import com.Sewol.Sewol.entity.Job;
import com.Sewol.Sewol.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    // 모든 사람 조회
    @Query("select p from Person p")
    List<Person> findAllPerson();

    // 정확히 일치하는 이름 하나로 조회
    @Query("select p from Person p where p.personName = :personName")
    List<Person> findPersonExact(@Param("personName")String personName);

    // 포함하는 이름 하나로 조회
    @Query("select p from Person p where p.personName Like %:personName%")
    List<Person> findPersonInclude(@Param("personName")String personName);

    // 검색한 직업으로 조회
    @Query("select p from Person p where p.job = :job")
    List<Person> findPersonJob(Job job);

    /**
    // 모든 사람 조회
    @Query("select p from Person p")
    Page<Person> findAllPerson(Pageable pageable);

    // 정확히 일치하는 이름 하나로 조회
    @Query("select p from Person p where p.personName = :personName")
    Page<Person> findPersonExact(String personName, Pageable pageable);

    // 포함하는 이름 하나로 조회
    @Query("select p from Person p where p.personName Like %:personName%")
    Page<Person> findPersonInclude(String personName, Pageable pageable);

    // 검색한 직업으로 조회
    @Query("select p from Person p where p.job = :job")
    Page<Person> findPersonJob(Job job, Pageable pageable);
    */
}