package by.anton.catshelter.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This cat data tran object fro client
 */
@Data
@NoArgsConstructor
public class CatDTO {
    private int id;
    private String name;
    private LocalDateTime lastfeedtime;
    private Boolean expiredFeeding; // if lastfeedtime less then 4 hours at now - false
}
