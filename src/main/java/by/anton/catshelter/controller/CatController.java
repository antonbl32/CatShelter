package by.anton.catshelter.controller;

import by.anton.catshelter.dto.CatDTO;
import by.anton.catshelter.facade.CatFacade;
import by.anton.catshelter.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/cat")
public class CatController {
    private CatService catService;
    private CatFacade catFacade;
    @Autowired
    public void setCatFacade(CatFacade catFacade) {
        this.catFacade = catFacade;
    }

    @Autowired
    public void setCatService(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CatDTO>> getAllCats(){
        List<CatDTO> list=catService.getAllCats().stream().map(catFacade::catToCatDTO).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatDTO> getCatById(@PathVariable("id") int id){
        return new ResponseEntity<>(catFacade.catToCatDTO(catService.getCatById(id)),HttpStatus.OK);
    }
}
