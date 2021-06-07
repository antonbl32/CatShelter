package by.anton.catshelter.controller;

import by.anton.catshelter.entity.Cat;
import by.anton.catshelter.entity.FeedTime;
import by.anton.catshelter.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cat")
public class CatController {
    private CatService catService;
    @Autowired
    public void setCatService(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cat>> getAllCats(){
        return getAllCats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cat> getCatById(@PathVariable("id") int id){
        return catService.;
    }
}
