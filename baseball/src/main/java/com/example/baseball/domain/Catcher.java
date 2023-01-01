package com.example.baseball.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("catcher") //Entity를 저장할 때 구분컬럼에 입력할 값
public class Catcher extends Player implements Throwball, Serializable {
    //model 확장 영역

    public Catcher() {
        setType("Catcher");
    }//타입명 설정

    @Override //interface를 통한 다중 상속 구현
    public String throwBall() {
        setPower(getPower()+1);
        return "송구연습";
    }
}
