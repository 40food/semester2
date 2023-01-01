package com.example.baseball;

import com.example.baseball.domain.Catcher;
import com.example.baseball.domain.Player;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

//상속받기 위해 만들어진 클래스를 나타낸다
@Component
public class RepositorySearchesProcessor implements RepresentationModelProcessor<EntityModel<Player>> {

    @Override
    public EntityModel<Player> process(EntityModel<Player> model) {
        model.add(Link.of(model.getRequiredLink("self").getHref()+"/training").withRel("training"));
        model.add(Link.of(model.getRequiredLink("self").getHref()+"/running").withRel("running"));

        if(model.getContent() instanceof Catcher)
            model.add(Link.of(model.getRequiredLink("self").getHref()+"/throwball").withRel("throwball"));
        
        return model;
        //필터 패턴, 간섭해 들어가서 링크를 추가함
        //여기선 if로 Catcher의 경우 throwball을 추가하도록 간섭
    }
    
}
