package com.example.baseball.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.baseball.Listener;

@Entity//저장하기 위해 필요한 객체로 도메인의 중요한 클래스임을 나타냄
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.STRING,
        name="palyer_type",
        columnDefinition = "CHAR(10)"
    )
public abstract class Player {
    //model로 재사용 될 공통영역
   
    @Id @GeneratedValue //GeneratedValue로 id숫자를 알아서 매기도록 함
    Long id;
        public Long getId() {
            return id;
        } //id는 바뀌면 안 되기 때문에 setter는 삭제

    String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            if(name==null)throw new IllegalArgumentException("이름은 꼭 들어가야 합니다"); 
            this.name = name;
        }

    String type;
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }

    //멤버변수 접근 권한 분리//
    private int power=0;
        public int getPower() {
            return power;
        }
        protected void setPower(int power) {
            if(Math.abs(this.power-power)<5)
            this.power = power;
            else
            throw new IllegalArgumentException("Stop over work!");
        }
    private int speed=0;
        public int getSpeed(){
            return speed;
        }
        protected void setSpeed(int speed){
            this.speed=speed;
        }
    //멤버변수 접근 권한 분리//

    List<Listener> listeners = new ArrayList<Listener>();
    public void addListeners(Listener listener){
        this.listeners.add(listener);
    }//옵저버 패턴 구현을 위한 listener 추가

    public void weightTraining(){
        power+=2;
       
        if(listeners!=null){
            for(int i=0;i<listeners.size();i++){
                listeners.get(i).powerChanged(power);
            }//power가 변했을 때 최소치를 충족하지 못하면 간섭하도록 옵저버 패턴 적용
        }
    }
    public void running() {
        speed+=2;
    }//training과 running 기본 기능 구현
}
