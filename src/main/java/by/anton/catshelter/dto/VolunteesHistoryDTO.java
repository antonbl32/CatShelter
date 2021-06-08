package by.anton.catshelter.dto;

import by.anton.catshelter.entity.FeedTime;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class VolunteesHistoryDTO {
    private int id;
    private String name;
    private boolean access;
    private List<FeedTime> feedTime;
}
