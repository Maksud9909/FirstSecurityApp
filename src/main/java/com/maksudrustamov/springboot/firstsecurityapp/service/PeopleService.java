package com.maksudrustamov.springboot.firstsecurityapp.service;

import com.maksudrustamov.springboot.firstsecurityapp.entity.Person;
import com.maksudrustamov.springboot.firstsecurityapp.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PeopleService {
    PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public Optional<Person> findByUsername(String username){
        return peopleRepository.findByUsername(username);
    }

    @Transactional // так как нам нужно менять базу данных
    public void register(Person person){
        peopleRepository.save(person); // он добавляет нового человека в базу при регистрации
    }
}
