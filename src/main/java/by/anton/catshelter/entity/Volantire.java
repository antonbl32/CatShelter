package by.anton.catshelter.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "volantire")
public class Volantire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "access")
    private boolean access;

}
