package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.Exceptions.AvailDateFoundException;
import com.ntu.sctp.group1.Exceptions.NoAvailabilityFoundExceptions;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.entity.Availability;
import com.ntu.sctp.group1.entity.Volunteer;
import com.ntu.sctp.group1.repository.AvailabilityRepository;
import com.ntu.sctp.group1.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepo;

    @Autowired
    VolunteerRepository volunteerRepository;

    // Get availabilities of a volunteer
    public List<Availability> getAvailabilitiesOfAVolunteer(Integer volunteerId) throws NoVolunteerFoundExceptions, NoAvailabilityFoundExceptions {
        Optional<Volunteer> findVolunteer = volunteerRepository.findById(volunteerId);
        if(findVolunteer.isEmpty()) {
            throw new NoVolunteerFoundExceptions("No volunteer found under id " + volunteerId);
        }
        // Removed on 25 Apr
//        if(findVolunteer.get().getAvailabilities().size() == 0) {
//            throw new NoAvailabilityFoundExceptions("Volunteer had not set any availabilities as yet!");
//        }

        List<Availability> avails = findVolunteer.get().getAvailabilities();
        avails.sort(Comparator.comparing(Availability::getDate));
        return avails;

    }

    // create availability of a volunteer
    public Availability setAvailability(Integer volunteerId, String date, String timeslot) throws NoVolunteerFoundExceptions, AvailDateFoundException {
        Optional<Volunteer> findVolunteer = volunteerRepository.findById(volunteerId);
        if(findVolunteer.isEmpty()) {
            throw new NoVolunteerFoundExceptions("No volunteer id founds");
        }
        Optional<List<Availability>> searchExistingAvail = availabilityRepo.findByVolunteerId(volunteerId);
        boolean found = searchExistingAvail.isPresent();
        if(found) {
            List<Availability> checkForSameDate = searchExistingAvail.get().stream()
                    .filter((avail)-> avail.getDate().equals(LocalDate.parse((date))))
                    .toList();
            if(checkForSameDate.size() != 0) {
                throw new AvailDateFoundException("You have already set an availability on this date!");
            }
        }
        Availability availDate = new Availability();
        availDate.setAvail(true);
        availDate.setTimeslot(timeslot);
        availDate.setVolunteer(findVolunteer.get());
        availDate.setDate(LocalDate.parse((date)));

        return availabilityRepo.save(availDate);
    }


    public List<Volunteer> searchByDate(LocalDate date) throws NoAvailabilityFoundExceptions {
        List<Availability> availabilities = availabilityRepo.findByDate(date);

        if (availabilities.size() == 0) {
            throw new NoAvailabilityFoundExceptions("No volunteers available on the given date");
        }

        List<Volunteer> volunteers = availabilities.stream()
                .filter(Availability::isAvail)
                .map(Availability::getVolunteer)
                .collect(Collectors.toList());

        if (volunteers.size() == 0) {
            throw new NoAvailabilityFoundExceptions("No volunteers available on the given date");
        }

        return volunteers;
    }


    public Availability updateAvailability(Integer volunteerId, LocalDate date, String isAvail)
            throws NoVolunteerFoundExceptions, NoAvailabilityFoundExceptions {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new NoVolunteerFoundExceptions("No volunteer found with the given ID: " + volunteerId));

        Availability availability = availabilityRepo.findByVolunteerAndDate(volunteer, date)
                .orElseThrow(() -> new NoAvailabilityFoundExceptions("No availability record found for the given volunteer and date"));
        boolean avail = isAvail.equalsIgnoreCase("false") ? false : true;
        availability.setAvail(avail);
        return availabilityRepo.save(availability);
    }

    // Added on 29 Mar
    public void deleteAvail(Integer volunteerId, LocalDate date) throws NoVolunteerFoundExceptions, NoAvailabilityFoundExceptions {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new NoVolunteerFoundExceptions("No volunteer found with the given ID: " + volunteerId));
        Availability availability = availabilityRepo.findByVolunteerAndDate(volunteer, date)
                .orElseThrow(() -> new NoAvailabilityFoundExceptions("No availability record found for the given volunteer and date"));
        availabilityRepo.delete(availability);
    }

    // Added on 31 Mar
    public List<Availability> getAllAvailabilities() throws NoAvailabilityFoundExceptions {
//        List<Availability> allAvails = availabilityRepo.findAll();
//        if(allAvails.isEmpty()) {
//            throw new NoAvailabilityFoundExceptions("There is no availability found!");
//        }
        return  availabilityRepo.findAll();
    }

}
