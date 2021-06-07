package by.anton.catshelter.service;

import by.anton.catshelter.entity.Cat;
import by.anton.catshelter.entity.FeedTime;
import by.anton.catshelter.entity.Volunteer;
import by.anton.catshelter.repository.FeedTimeRepository;
import by.anton.catshelter.repository.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Scheduled(fixedDelay = 1000)
    public void addFeedCat() {
        List<Integer> listIdVolunteer = volunteerRepository.findAll().stream().mapToInt(Volunteer::getId).boxed()
                .collect(Collectors.toList());
        int idVolunteerToFeed = listIdVolunteer.get(random.nextInt(listIdVolunteer.size()-1));
        List<Integer> listIdCat = catService.getAllCats().stream().mapToInt(Cat::getId)
                .boxed().collect(Collectors.toList());
        int idCatToFeed = listIdCat.get(random.nextInt(listIdCat.size()-1));
        Volunteer volunteer = volunteerRepository.getVolunteerById(idVolunteerToFeed).get();
        if (!ObjectUtils.isEmpty(volunteer)) {
            if (volunteer.isAccess()) {
                LocalDateTime lastTime = catService.getLastTimeFeedByCatId(idCatToFeed);
                Cat cat = catService.getCatById(idCatToFeed);
                cat.setLastFeedTime(lastTime);
                catService.setLastTimeFeedByCatId(cat.getId(), lastTime);
                FeedTime feedTime = new FeedTime();
                feedTime.setIdcat(idCatToFeed);
                feedTime.setIdvolantire(idVolunteerToFeed);
                feedTimeRepository.save(feedTime);
                LocalDateTime localDateTime = LocalDateTime.now();
                if (Duration.between(localDateTime, lastTime).toHours() > 4) {
                    volunteer.setAccess(false);
                    volunteerRepository.save(volunteer);
                }
            } else {
                LOG.info("Volunteer with id {} doesn't has access", idVolunteerToFeed);
            }
        }
    }

    public List<Integer> getListIdVolunteer() {
        return volunteerRepository.findAll().stream().mapToInt(Volunteer::getId).boxed().collect(Collectors.toList());
    }
}
