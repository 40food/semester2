package com.example.baseball;

import com.example.baseball.domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController //하위 메소드에 문자열과 JSON 전송 가능
@EnableAspectJAutoProxy
public class BaseballApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseballApplication.class, args);
	}

	//자동으로 player 저장소와 연결, 데이터를 적재
	@Autowired
	PlayerRepository playerRepository;

	//저장소에서 id를 가져오고 path와 기능을 연결
	@RequestMapping(method=RequestMethod.PUT,path="players/{playerId}/training")
	public String trainingPlayer(@PathVariable(value = "playerId") Long playerId){
		Player thePlayer=playerRepository.findById(playerId).get();
		thePlayer.weightTraining();

		playerRepository.save(thePlayer);
		return "훈련했습니다.";
	}
	@RequestMapping(method=RequestMethod.PUT,path="players/{playerId}/running")
	public String runningPlayer(@PathVariable(value = "playerId") Long playerId){
		Player thePlayer=playerRepository.findById(playerId).get();
		thePlayer.running();

		playerRepository.save(thePlayer);
		return "달렸습니다.";
	}
	@RequestMapping(method=RequestMethod.PUT,path="players/{playerId}/throwball")
	public String throwballPlayer(@PathVariable(value = "playerId") long playerId){
		Player thePlayer=playerRepository.findById(playerId).get();

		if(thePlayer instanceof Throwball){
			String message=((Throwball)thePlayer).throwBall();
			playerRepository.save(thePlayer);
			return message;
		}
		return "송구연습 불가";
	}

	//각각의 model에 기능마다 path를 설정한 후 반환
	@RequestMapping(method=RequestMethod.PUT,path="catchers/{playerId}/throwball")
	public String throwballCatcher(@PathVariable(value = "playerId") long playerId){
		return throwballPlayer(playerId);
	}
	@RequestMapping(method=RequestMethod.PUT,path="catchers/{playerId}/training")
	public String trainingCatcher(@PathVariable(value = "playerId") Long playerId){
		return trainingPlayer(playerId);
	}
	@RequestMapping(method=RequestMethod.PUT,path="pitchers/{playerId}/training")
	public String trainingPitcher(@PathVariable(value = "playerId") Long playerId){
		return trainingPlayer(playerId);
	}
	@RequestMapping(method=RequestMethod.PUT,path="defenders/{playerId}/training")
	public String trainingDefender(@PathVariable(value = "playerId") Long playerId){
		return trainingPlayer(playerId);
	}
	@RequestMapping(method=RequestMethod.PUT,path="catchers/{playerId}/running")
	public String runningCatcher(@PathVariable(value = "playerId") Long playerId){
		return runningPlayer(playerId);
	}
	@RequestMapping(method=RequestMethod.PUT,path="pitchers/{playerId}/running")
	public String runningPitcher(@PathVariable(value = "playerId") Long playerId){
		return runningPlayer(playerId);
	}
	@RequestMapping(method=RequestMethod.PUT,path="defenders/{playerId}/running")
	public String runningDefender(@PathVariable(value = "playerId") Long playerId){
		return runningPlayer(playerId);
	}
}
