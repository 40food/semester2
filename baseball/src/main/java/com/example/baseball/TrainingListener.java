package com.example.baseball;

public class TrainingListener implements Listener {
    
    int powerCriteria=2;

    public TrainingListener(int criteria){
        this.powerCriteria=criteria;
    }

    @Override
    public void powerChanged(int power) {
        if(power<powerCriteria){
            System.out.println("Do more work!");
        }
    }
    
}
