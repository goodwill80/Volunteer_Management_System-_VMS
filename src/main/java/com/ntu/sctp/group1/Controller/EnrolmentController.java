package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.DataTransferObject.EnrolDto;
import com.ntu.sctp.group1.DataTransferObject.EnrolEditDto;
import com.ntu.sctp.group1.Exceptions.NoEnrolmentFoundExceptions;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.Service.EnrolmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"*"}, maxAge = 86400, allowCredentials = "false" )
@RestController
@RequestMapping
public class EnrolmentController {

    @Autowired
    EnrolmentService enrolmentService;

    record Status(String msg, boolean success){};

    @Operation(summary = "Get all enrolments", description = "Get information of all enrolments past and present")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/enrolments")
    public ResponseEntity<?> getAllEnrolments() {
        try {
            return ResponseEntity.ok().body(enrolmentService.getAllEnrolments());
        } catch (NoEnrolmentFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

    @Operation(summary = "Create new enrolment", description = "Create a new program and tag an enrolment to it")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/admin/enrolments/newenrolment")
    public ResponseEntity<?> createEnrolment(@RequestBody EnrolDto enrolDto) {
        try {
            return ResponseEntity.ok().body(enrolmentService.createEnrolment(enrolDto));
        } catch (NoEnrolmentFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

    @Operation(summary = "Update an enrolment", description = "Update information of an enrolment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping ("/admin/enrolments/update")
    public ResponseEntity<?> updateEnrolment(@RequestBody EnrolEditDto enrolEditDto) {
        try {
            return ResponseEntity.ok().body(enrolmentService.updateEnrolment(enrolEditDto));
        } catch (NoEnrolmentFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

    @Operation(summary = "Add volunteer to an enrolment", description = "Enrol a selected volunteer to an enrolment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/admin/enrolments/volunteers")
    public ResponseEntity<?> addVolunteer(@RequestParam int volunteer_id, @RequestParam int program_id){
        try{
            enrolmentService.addVolunteer(volunteer_id, program_id);
            return ResponseEntity.ok().body("volunteer added successfully");
        }catch (NoVolunteerFoundExceptions | NoEnrolmentFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(),false));
        }
    }

    @Operation(summary = "Remove a volunteer from an enrolment", description = "Remove volunteer from enrolment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/admin/enrolments/unenrol")
    public ResponseEntity<?> removeVolunteer(@RequestParam Integer volunteerId, @RequestParam Integer programId ) {
        try {
            enrolmentService.removeVolunteerFromEnrolment(volunteerId, programId);
            return ResponseEntity.ok().body("volunteer removed successfully");
        } catch (NoEnrolmentFoundExceptions | NoVolunteerFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(),false));
        }
    }

    @Operation(summary = "Get all volunteers in an enrolment", description = "Get all volunteers in an enrolment")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping ("/admin/enrolments/volunteers")
    public ResponseEntity<?> getAllVolunteer(@RequestParam int program_id){
        try{
            return ResponseEntity.ok().body(enrolmentService.getAllVolunteer(program_id));
        } catch(NoEnrolmentFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(),false));
        }
    }

    @Operation(summary = "Delete an enrolment", description = "Delete an enrolment")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/enrolments/{id}")
    public ResponseEntity<?> deleteEnrolment(@PathVariable Integer id) {
        try {
            enrolmentService.deleteEnrolment(id);
            return ResponseEntity.ok(new Status("Enrolment with ID " + id + " deleted successfully", true));
        } catch (NoEnrolmentFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

}