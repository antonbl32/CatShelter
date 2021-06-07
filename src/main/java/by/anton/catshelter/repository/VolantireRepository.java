package by.anton.catshelter.repository;

import by.anton.catshelter.entity.Volantire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolantireRepository extends JpaRepository<Volantire,Integer> {
    Optional<Volantire> getVolantireById(int id);
    Optional<List<Volantire>> getAllByOrderByAccess();
}
