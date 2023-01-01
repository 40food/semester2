package com.example.baseball;

import com.example.baseball.domain.*;

import org.springframework.data.repository.CrudRepository;

public interface ClubRepository extends CrudRepository<Club, String> {
    //Club의 저장소
}