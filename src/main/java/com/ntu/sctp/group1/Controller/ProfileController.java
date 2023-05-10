package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.DataTransferObject.ProfileEditDto;
import com.ntu.sctp.group1.Exceptions.NoProfileFoundExceptions;
import com.ntu.sctp.group1.Service.ProfileService;
import com.ntu.sctp.group1.entity.Profile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"*"}, maxAge = 86400, allowCredentials = "false" )
@RestController
@RequestMapping
public class ProfileController {

    @Autowired
    ProfileService profileService;

    record Message(String message, boolean success) {}

    @Operation(summary = "Get all profiles of volunteers", description = "Interview Profiles of all volunteers")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/volunteers/profiles/all")
    public ResponseEntity<?> getAllProfiles(@RequestParam String UID) {
        try {
            return ResponseEntity.ok().body(profileService.getAllProfiles(UID));
        } catch(NoProfileFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(new Message("Something is wrong with the server", false));
        }
    }

    @Operation(summary = "Get profile by ID", description = "Get profile of volunteer by ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/volunteers/profiles/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Integer id) {
        try {
            Profile profile = profileService.getProfileById(id);
            return ResponseEntity.ok(profile);
        } catch (NoProfileFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(), false));
        }
    }

    @Operation(summary = "Edit a volunteer's profile", description = "Edit a volunteer's profile during interview")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/admin/volunteers/profiles/{volunteerid}/edit")
    public ResponseEntity<?> editVolunteerProfile(@PathVariable Integer volunteerid, @RequestBody ProfileEditDto newVolunteerProfile) {
        try{
            return ResponseEntity.ok().body(profileService.editProfile(volunteerid, newVolunteerProfile));
        } catch (NoProfileFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(),false));
        }
    }
}