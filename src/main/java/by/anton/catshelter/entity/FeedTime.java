package by.anton.catshelter.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "feedtime")
public class FeedTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="feedtime")
    private LocalDateTime feedTime;
    @Column(name = "idcat")
    private int idcat;
    @Column(name = "idvolantire")
    private int idvolantire;

    @PrePersist
    protected void onCreate(){
        this.feedTime=LocalDateTime.now();
    }
}
