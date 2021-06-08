package by.anton.catshelter.dto;

import by.anton.catshelter.entity.FeedTime;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CatHistoryDTO {
    private int id;
    private String name;
    private List<FeedTime> feedtime;
    private Boolean expiredFeeding;
}
