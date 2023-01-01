package com.example.baseball.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity//저장하기 위해 필요한 객체로 도메인의 중요한 클래스임을 나타냄
public class TeamMember {

    @Id @GeneratedValue //GeneratedValue로 id숫자를 알아서 매기도록 함
    Long id;

    @ManyToOne //TeamMember가 여러개일때 Club은 하나
    Club club;

    String player;
        public String getPlayer() {
            return player;
        }
        public void setPlayer(String player) {
            this.player = player;
        }

    @Embedded //Value Object, 항상 따라다니는 클래스임을 나타냄
    Contract contract;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }
    public Contract getPayment() {
        return contract;
    }
    public void setPayment(Contract contract) {
        this.contract = contract;
    }
    
}
