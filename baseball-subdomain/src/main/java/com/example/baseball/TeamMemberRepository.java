package com.example.baseball;

import com.example.baseball.domain.*;

import org.springframework.data.repository.CrudRepository;

public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {
    //TeamMember의 저장소
}