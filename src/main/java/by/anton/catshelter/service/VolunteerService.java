package by.anton.catshelter.service;

import by.anton.catshelter.entity.Cat;
import by.anton.catshelter.entity.FeedTime;
import by.anton.catshelter.entity.Volunteer;
import by.anton.catshelter.exception.NoSuchVolunteerException;
import by.anton.catshelter.repository.FeedTimeRepository;
import by.anton.catshelter.repository.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class VolunteerService {
    private VolunteerRepository volunteerRepository;
    private FeedTimeRepository feedTimeRepository;
    private CatService catService;
    private final Logger LOG = LoggerFactory.getLogger(VolunteerService.class);
    private final Random random = new Random();

    @Autowired
    public void setCatService(CatService catService) {
        this.catService = catService;
    }

    @Autowired
    public void setVolunteerRepository(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Autowired
    public void setFeedTimeRepository(FeedTimeRepository feedTimeRepository) {
        this.feedTimeRepository = feedTimeRepository;
    }

    @Scheduled(fixedDelay = 10000)
    public void addFeedCat() {
        List<Integer> listIdVolunteer = volunteerRepository.findAll().stream().mapToInt(Volunteer::getId).boxed()
                .collect(Collectors.toList());
        List<Integer> listIdCat = catService.getAllCats().stream().mapToInt(Cat::getId)
                .boxed().collect(Collectors.toList());
        if (listIdCat.size() != 0 || listIdVolunteer.size() != 0) {
            int idVolunteerToFeed = listIdVolunteer.get(random.ints(1, 0, listIdVolunteer.size())
                    .findFirst().getAsInt());
            int idCatToFeed = listIdCat.get(random.ints(1, 0, listIdCat.size())
                    .findFirst().getAsInt());
            Volunteer volunteer = volunteerRepository.getVolunteerById(idVolunteerToFeed)
                    .orElseThrow(()->new NoSuchVolunteerException("no volunteer"));
            if (volunteer.isAccess() && catService.getLastTimeFeedByCatId(idCatToFeed) != null) {
                LocalDateTime lastTime = catService.getLastTimeFeedByCatId(idCatToFeed);
                LocalDateTime localDateTime = LocalDateTime.now();
                if (lastTime != null && Duration.between(localDateTime, lastTime).toHours() >= 4) {
                    Cat cat = catService.getCatById(idCatToFeed);
                    cat.setLastFeedTime(lastTime);
                    catService.setLastTimeFeedByCatId(cat.getId(), lastTime);
                    if (Duration.between(localDateTime, lastTime).toHours() > 5) {
                        volunteer.setAccess(false);
                        volunteerRepository.save(volunteer);
                    }
                }
                FeedTime feedTime = new FeedTime();
                feedTime.setIdcat(idCatToFeed);
                feedTime.setIdvolantire(idVolunteerToFeed);
                feedTimeRepository.save(feedTime);
            } else {
                LOG.info("Volunteer with id {} doesn't has access", idVolunteerToFeed);
            }
        }

    }



    public List<Integer> getListIdVolunteer() {
        return volunteerRepository.findAll().stream().mapToInt(Volunteer::getId).boxed().collect(Collectors.toList());
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

}
