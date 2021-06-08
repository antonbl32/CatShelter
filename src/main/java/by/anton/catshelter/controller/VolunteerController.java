package by.anton.catshelter.controller;

import by.anton.catshelter.entity.Volunteer;
import by.anton.catshelter.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/vol")
public class VolunteerController {
    private VolunteerService volunteerService;
    @Autowired
    public void setVolunteerService(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Volunteer>> getAllVolunteers(){
        return new ResponseEntity<>(volunteerService.getAllVolunteers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getVolunteerFedByWeek(@PathVariable("id") int id){
        return new ResponseEntity<>(volunteerService.getVolunteerFedByOneWeek(volunteerService.getVolunteerById(id)),HttpStatus.OK);
    }

}
