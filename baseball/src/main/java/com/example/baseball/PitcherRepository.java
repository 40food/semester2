package com.example.baseball;

import com.example.baseball.domain.Pitcher;

import org.springframework.data.repository.CrudRepository;

public interface PitcherRepository extends CrudRepository<Pitcher, Long> {
    //Pitcher의 저장소
}