package com.maksudrustamov.springboot.firstsecurityapp.repository;

import com.maksudrustamov.springboot.firstsecurityapp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username); // мы получаем здесь optional(из-за того, что может такого человека и не быть)
}
