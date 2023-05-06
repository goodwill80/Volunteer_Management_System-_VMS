package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.Exceptions.NoAvailabilityFoundExceptions;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.Service.AvailabilityService;
import com.ntu.sctp.group1.entity.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 86400, allowCredentials = "false" )
@RestController
public class AvailabilityController {

    @Autowired
    AvailabilityService availabilityService;

    record Status(String msg, boolean success){};

    @PostMapping("/volunteers/availability/{volunteerId}")
    public ResponseEntity<?> setAvailability(@PathVariable Integer volunteerId, @RequestParam String date, @RequestParam String timeslot) {
        try {
            return ResponseEntity.ok().body(availabilityService.setAvailability(volunteerId, date, timeslot));
        } catch(NoVolunteerFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false) );
        }
    }

    @GetMapping("/volunteers/availability/date/{date}")
    public ResponseEntity<?> searchByDate(@PathVariable String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            List<Volunteer> volunteers = availabilityService.searchByDate(parsedDate);
            return ResponseEntity.ok().body(volunteers);
        } catch (NoAvailabilityFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

    // Removed availabilityException - 25 Apr
    @GetMapping("/volunteers/availabilities/{volunteerId}")
    public ResponseEntity<?> getAvailabilitiesOfAVolunteer(@PathVariable Integer volunteerId) {
        try {
            return ResponseEntity.ok().body(availabilityService.getAvailabilitiesOfAVolunteer(volunteerId));
        } catch(NoVolunteerFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(new Status(ex.getMessage(), false));
        }
    }

    // Change to Date String - 29 Mar
    @PutMapping("/volunteers/availability/{volunteerId}")
    public ResponseEntity<?> updateAvailability(@PathVariable Integer volunteerId,
                                                @RequestParam String date,
                                                @RequestParam(required = true) String isAvail) {
        try {
            LocalDate parsedDate = LocalDate.parse((date));
            return ResponseEntity.ok().body(availabilityService.updateAvailability(volunteerId, parsedDate, isAvail));
        } catch (NoVolunteerFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

    // Added on 29 Mar
    @DeleteMapping("/volunteers/availability/{volunteerId}")
    public ResponseEntity<?> deleteAvailability(@PathVariable Integer volunteerId,
                                                @RequestParam String date
                                                ) {
        try {
            LocalDate parsedDate = LocalDate.parse((date));
            availabilityService.deleteAvail(volunteerId, parsedDate);
            return ResponseEntity.ok().body(new Status("Availability deleted successfully", true));
        } catch (NoVolunteerFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

    // Added on 31 Mar
    @GetMapping("/volunteers/availability/all")
    public ResponseEntity<?> getAllAvailabilities () {
        try {
            return ResponseEntity.ok().body(availabilityService.getAllAvailabilities());
        }
//        catch(NoAvailabilityFoundExceptions ex) {
//            return ResponseEntity.notFound().build();
//        }
        catch(Exception ex) {
            return ResponseEntity.internalServerError().body(new Status(ex.getMessage(), false));
        }
    }
}
