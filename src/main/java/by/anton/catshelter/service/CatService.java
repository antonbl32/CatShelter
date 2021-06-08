package by.anton.catshelter.service;

import by.anton.catshelter.entity.Cat;
import by.anton.catshelter.entity.FeedTime;
import by.anton.catshelter.exception.NoSuchCatException;
import by.anton.catshelter.repository.CatRepository;
import by.anton.catshelter.repository.FeedTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CatService {
    private CatRepository catRepository;
    private FeedTimeRepository feedTimeRepository;
    private Logger LOG = LoggerFactory.getLogger(CatService.class);

    @Autowired
    public void setCatRepository(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Autowired
    public void setFeedTimeRepository(FeedTimeRepository feedTimeRepository) {
        this.feedTimeRepository = feedTimeRepository;
    }

    public List<FeedTime> getAllHistoryByCatId(int id) {
        LOG.info("Get Cat with id " + id);
        return feedTimeRepository.getAllByIdcat(id).orElseThrow(() -> new NoSuchCatException("No history about cat with id=" + id));
    }

    public LocalDateTime getLastTimeFeedByCatId(int id) {
        return feedTimeRepository.getLastTimeFeedByCatId(id)
                .orElseThrow(() -> new NoSuchCatException("FeedTime not found with cat id " + id));
    }

    public void setLastTimeFeedByCatId(int id, LocalDateTime time){
        Optional<Cat> cat=catRepository.getCatById(id);
        if(cat.isPresent()){
            cat.get().setLastFeedTime(time);
            catRepository.save(cat.get());
        }
    }

    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    public Cat getCatById(int id) {
        return catRepository.getCatById(id).orElseThrow(() -> new NoSuchCatException("Cat not found with id " + id));
    }
}