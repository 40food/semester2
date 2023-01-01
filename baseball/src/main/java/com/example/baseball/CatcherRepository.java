package com.example.baseball;

import com.example.baseball.domain.Catcher;

import org.springframework.data.repository.CrudRepository;

public interface CatcherRepository extends CrudRepository<Catcher, Long> {
    //Catcher의 저장소   
}
