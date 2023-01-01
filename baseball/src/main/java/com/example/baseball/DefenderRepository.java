package com.example.baseball;

import com.example.baseball.domain.Defender;

import org.springframework.data.repository.CrudRepository;

public interface DefenderRepository extends CrudRepository<Defender, Long> {
    //Defender의 저장소
}
