package by.anton.catshelter.facade;

import by.anton.catshelter.dto.CatDTO;
import by.anton.catshelter.entity.Cat;
import by.anton.catshelter.entity.FeedTime;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;

@Component
public class CatFacade {
    public CatDTO catToCatDTO(Cat cat){
        CatDTO catDTO=new CatDTO();
        catDTO.setId(cat.getId());
        catDTO.setName(cat.getName());
        catDTO.setLastfeedtime(cat.getLastFeedTime());
        LocalDateTime time=cat.getFeedTime().stream().max(Comparator.comparing(FeedTime::getFeedTime)).get().getFeedTime();
        LocalDateTime localDateTime=LocalDateTime.now();
        if(Duration.between(localDateTime,time).toHours()>4){
            catDTO.setExpiredFeeding(true);
        }else {
            catDTO.setExpiredFeeding(false);
        }
        return catDTO;
    }
}
