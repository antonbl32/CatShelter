package by.anton.catshelter.facade;

import by.anton.catshelter.dto.VolunteesHistoryDTO;
import by.anton.catshelter.entity.Volunteer;
import org.springframework.stereotype.Component;

@Component
public class VolunteersFacadeHistory {
    public VolunteesHistoryDTO volunteerToDTO(Volunteer volunteer) {
        VolunteesHistoryDTO volunteesHistoryDTO = new VolunteesHistoryDTO();
        volunteesHistoryDTO.setId(volunteer.getId());
        volunteesHistoryDTO.setAccess(volunteer.isAccess());
        volunteesHistoryDTO.setName(volunteer.getName());
        volunteesHistoryDTO.setFeedTime(volunteer.getFeedTime());
        return volunteesHistoryDTO;
    }
}
