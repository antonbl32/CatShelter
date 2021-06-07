package by.anton.catshelter.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CatDTO {
    private int id;
    private String name;
    private LocalDateTime lastfeedtime;
    private Boolean expiredFeeding;
}
