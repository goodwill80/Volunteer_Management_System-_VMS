package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.Exceptions.NoAvailabilityFoundExceptions;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.Service.AvailabilityService;
import com.ntu.sctp.group1.entity.Volunteer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Set new availability of a volunteer", description = "Set a new date and timeslot that a volunteer is available")
    @SecurityRequirement(name = "Bearer Authentication")
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

    @Operation(summary = "Search volunteers by availability", description = "Search volunteers according to avail dates and timeslots")
    @SecurityRequirement(name = "Bearer Authentication")
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
    @Operation(summary = "Get availabilities of a volunteer", description = "Get all availabilities of a volunteer")
    @SecurityRequirement(name = "Bearer Authentication")
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
    @Operation(summary = "Update availability of volunteer by ID", description = "Update the available date and timeslot of a volunteer")
    @SecurityRequirement(name = "Bearer Authentication")
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
    @Operation(summary = "Delete availability of a volunteer", description = "Delete an availability of a volunteer based on date")
    @SecurityRequirement(name = "Bearer Authentication")
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
    @Operation(summary = "Get all availabilities of volunteers", description = "Get all availabilities of volunteers for analysis")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/volunteers/availability/all")
    public ResponseEntity<?> getAllAvailabilities () {
        try {
            return ResponseEntity.ok().body(availabilityService.getAllAvailabilities());
        }
        catch(Exception ex) {
            return ResponseEntity.internalServerError().body(new Status(ex.getMessage(), false));
        }
    }
}
