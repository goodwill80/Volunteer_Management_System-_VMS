package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.DataTransferObject.ProgramDto;
import com.ntu.sctp.group1.Exceptions.NoProgramFoundExceptions;
import com.ntu.sctp.group1.Service.ProgramService;
import com.ntu.sctp.group1.entity.Program;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins= {"*"}, maxAge = 86400, allowCredentials = "false" )
@RestController
@RequestMapping
public class ProgramController {

    @Autowired
    ProgramService programService;

    record Message(String message, boolean success) {
    }

    @Operation(summary = "Get all programs", description = "Get all programs")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/programs")
    public ResponseEntity<?> getAllPrograms() {
        try {
            return ResponseEntity.ok().body(programService.getAllPrograms());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(), false));
        }
    }

    @Operation(summary = "Get a program by ID", description = "Get program by ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/admin/programs/{id}")
    public ResponseEntity<?> getProgramById(@PathVariable Integer id) {
        try {
            Program program = programService.getProgramById(id);
            return ResponseEntity.ok(program);
        } catch (NoProgramFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(), false));
        }
    }

    @Operation(summary = "Create a new program", description = "Create a new program")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/admin/newprogram")
    public ResponseEntity<?> createProgram(@RequestBody ProgramDto newProgram) {
        try {
            Program program = programService.createProgram(newProgram);
            return ResponseEntity.ok().body(program);
        } catch (NoProgramFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(), false));
        }
    }

    @Operation(summary = "Update a program", description = "Update a program by ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/admin/programs/{id}")
    public ResponseEntity<?> updateProgram(@PathVariable Integer id, @RequestBody ProgramDto updatedProgram) {
        try {
            Program program = programService.updateProgram(id, updatedProgram);
            return ResponseEntity.ok(program);
        } catch (NoProgramFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(), false));
        }
    }

    @Operation(summary = "Delete a program", description = "Delete a program by ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/admin/programs/{id}")
    public ResponseEntity<?> deleteProgram(@PathVariable Integer id) {
        try {
            programService.deleteProgram(id);
            return ResponseEntity.ok(new Message("Program with ID " + id + " deleted successfully", true));
        } catch (NoProgramFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(), false));
        }
    }
}
