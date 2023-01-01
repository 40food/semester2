package com.example.baseball;

import com.example.baseball.domain.TeamMember;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

//상속받기 위해 만들어진 클래스를 나타낸다
@Component
public class RepositorySearchesProcessor implements RepresentationModelProcessor<EntityModel<TeamMember>> {

    @Override
    public EntityModel<TeamMember> process(EntityModel<TeamMember> model) {
        model.add(Link.of(model.getContent().getPlayer()).withRel("palyer"));
        
        return model;
    }//필터 패턴, 간섭해 들어가서 링크를 추가함
}
