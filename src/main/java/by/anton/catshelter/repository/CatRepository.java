package by.anton.catshelter.repository;

import by.anton.catshelter.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat,Integer> {
    Optional<Cat> getCatById(int id);
    Optional<List<Cat>> getAllByOrderByName();
}
