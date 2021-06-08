package by.anton.catshelter.repository;

import by.anton.catshelter.entity.FeedTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FeedTimeRepository extends JpaRepository<FeedTime,Integer> {
    Optional<List<FeedTime>> getAllByIdcat(Integer idcat);
    Optional<List<FeedTime>> getAllByIdvolantire(Integer idvolantire);

    @Query(value = "select fd.feedtime from FeedTime fd where fd.idcat=:idcat order by fd.feedtime desc limit 1;",
    nativeQuery = true)
    Optional<LocalDateTime> getLastTimeFeedByCatId(@Param("idcat") Integer idcat);
    @Query(value = "select fd.feedtime from FeedTime fd where fd.idvolantire=:idvolantire order by fd.feedtime desc limit 1;",
            nativeQuery = true)
    Optional<LocalDateTime> getLastTimeFeedByVolantireId(@Param("idvolantire") Integer idVolantire);
}
