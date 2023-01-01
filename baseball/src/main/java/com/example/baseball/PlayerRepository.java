package com.example.baseball;

import com.example.baseball.domain.Player;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    //Player의 저장소
}