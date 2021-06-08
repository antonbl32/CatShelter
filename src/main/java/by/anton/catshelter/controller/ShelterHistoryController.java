package by.anton.catshelter.controller;

import by.anton.catshelter.dto.CatHistoryDTO;
import by.anton.catshelter.dto.VolunteesHistoryDTO;
import by.anton.catshelter.facade.CatFacadeHistory;
import by.anton.catshelter.facade.VolunteersFacadeHistory;
import by.anton.catshelter.service.CatService;
import by.anton.catshelter.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/history")
public class ShelterHistoryController {
    private CatService catService;
    private CatFacadeHistory catFacade;
    private VolunteerService volunteerService;
    private VolunteersFacadeHistory volunteersFacadeHistory;

    @Autowired
    public void setVolunteersFacadeHistory(VolunteersFacadeHistory volunteersFacadeHistory) {
        this.volunteersFacadeHistory = volunteersFacadeHistory;
    }

    @Autowired
    public void setVolunteerService(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Autowired
    public void setCatFacade(CatFacadeHistory catFacade) {
        this.catFacade = catFacade;
    }

    @Autowired
    public void setCatService(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/allcats")
    public ResponseEntity<List<CatHistoryDTO>> getAllHistoryAboutCats() {
        List<CatHistoryDTO> list = catService.getAllCats().stream().map(catFacade::catToCatDTOHistory).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/allvolunteers")
    public ResponseEntity<List<VolunteesHistoryDTO>> getAllHistoryAboutVoluntees() {
        List<VolunteesHistoryDTO> list = volunteerService.getAllVolunteers().stream().map(volunteersFacadeHistory::volunteerToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
