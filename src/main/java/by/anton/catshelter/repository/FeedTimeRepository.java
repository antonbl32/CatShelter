package by.anton.catshelter.repository;

import by.anton.catshelter.entity.FeedTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedTimeRepository extends JpaRepository<FeedTime,Integer> {
    Optional<List<FeedTime>> getAllByIdcat(Integer idcat);
    Optional<List<FeedTime>> getAllByIdvolantire(Integer idvolantire);

    @Query(value = "select * from FeedTime fd where (select max(sfd.feedTime) from FeedTime sfd where fd.id=sfd.id) and fd.idcat= :idcat",
    nativeQuery = true)
    Optional<FeedTime> getLastTimeFeedByCatId(@Param("idcat") Integer idcat);
    @Query(value = "select * from FeedTime fd where (select max(sfd.feedTime) from FeedTime sfd where fd.id=sfd.id) and fd.idcat= :idvolantire",
            nativeQuery = true)
    Optional<FeedTime> getLastTimeFeedByVolantireId(@Param("idvolantire") Integer idVolantire);
}
