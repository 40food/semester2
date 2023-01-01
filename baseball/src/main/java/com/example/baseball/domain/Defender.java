package com.example.baseball.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("defender")//Entity를 저장할 때 구분컬럼에 입력할 값
public class Defender extends Player implements Serializable {
    //model 확장 영역

    public Defender() {
        setType("Defender");
    }//타입명 설정

    @Override //오버라이드를 통한 행위 재정의
    public void running() {
        setSpeed(getSpeed()+3);
    }
}
