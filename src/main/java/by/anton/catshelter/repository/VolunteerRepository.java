package by.anton.catshelter.repository;

import by.anton.catshelter.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer,Integer> {
    Optional<Volunteer> getVolunteerById(int id);
    Optional<List<Volunteer>> getAllByOrderByAccess();
}
