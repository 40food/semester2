package com.example.baseball.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("picther")//Entity를 저장할 때 구분컬럼에 입력할 값
public class Pitcher extends Player implements Serializable {
    //model 확장 영역

    public Pitcher() {
        setType("Picther");
    }//타입명 설정

    @Override //오버라이드를 통한 행위 재정의
    public void weightTraining() {
        setPower(getPower()+1);
    }

    @Override
    public void running() {
        super.running(); //슈퍼클래스 메소드 호출
        setSpeed(getSpeed()+1);
    }
}
