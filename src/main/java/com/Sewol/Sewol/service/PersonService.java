package com.Sewol.Sewol.service;

import com.Sewol.Sewol.entity.Job;
import com.Sewol.Sewol.entity.Person;
import com.Sewol.Sewol.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    // 모든 사람 조회
    @Transactional(readOnly = true)
    public List<Person> findAllPerson() {
        return personRepository.findAllPerson();
    }

    // 정확한 이름 하나로 조회
    @Transactional(readOnly = true)
    public List<Person> findPersonExact(String personName) {
        return personRepository.findPersonExact(personName);
    }

    // 포함한 이름 하나로 조회
    @Transactional(readOnly = true)
    public List<Person> findPersonInclude(String personName) {
        return personRepository.findPersonInclude(personName);
    }

    public List<Person> findPersonJob(Job job) {
        return personRepository.findPersonJob(job);
    }

    public Long personSave(Person person) {
        Person personId = personRepository.save(person);

       return personId.getId();
    }

    @Transactional
    public Long personUpdate(Person person) {
        Person personFind = personRepository.getById(person.getId());
        personFind.setId(person.getId());
        personFind.setPersonName(person.getPersonName());
        personFind.setJob(person.getJob());

        Person personId = personRepository.save(personFind);
        return personId.getId();
    }

    /**
     // 모든 사람 조회
     public Page<Person> findAllPerson(Pageable pageable) {
     return personRepository.findAllPerson(pageable);
     }

     // 정확한 이름 하나로 조회
     public Page<Person> findPersonExact(String personName, Pageable pageable) {
     return personRepository.findPersonExact(personName, pageable);
     }

     // 포함한 이름 하나로 조회
     public Page<Person> findPersonInclude(String personName, Pageable pageable) {
     return personRepository.findPersonInclude(personName, pageable);
     }

     // 검색한 직업으로 조회
     public Page<Person> findPersonJob(Job job, Pageable pageable) {
     return personRepository.findPersonJob(job, pageable);
     }
     */
}
