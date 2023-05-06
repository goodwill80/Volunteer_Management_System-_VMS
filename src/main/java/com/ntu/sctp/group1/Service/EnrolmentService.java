package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.DataTransferObject.EnrolDto;
import com.ntu.sctp.group1.DataTransferObject.EnrolEditDto;
import com.ntu.sctp.group1.Exceptions.NoEnrolmentFoundExceptions;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.entity.Enrolment;
import com.ntu.sctp.group1.entity.Program;
import com.ntu.sctp.group1.entity.Volunteer;
import com.ntu.sctp.group1.repository.EnrolmentRepository;
import com.ntu.sctp.group1.repository.ProgramRepository;
import com.ntu.sctp.group1.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnrolmentService {

    @Autowired
    EnrolmentRepository enrolmentRepository;

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    ProgramRepository programRepository;

    public List<Enrolment> getAllEnrolments() throws NoEnrolmentFoundExceptions {
        List<Enrolment> enrolment = enrolmentRepository.findAll();
        if (enrolment.isEmpty()) {
            throw new NoEnrolmentFoundExceptions("No enrolments found");
        } else {
            return enrolment;
        }
    }

    public Enrolment createEnrolment(EnrolDto enrolDto) throws NoEnrolmentFoundExceptions {
        Enrolment enrolment = new Enrolment();
        Optional<Program> findProgram = programRepository.findById(enrolDto.getProgram_id());
        if (findProgram.isEmpty()) {
            throw new NoEnrolmentFoundExceptions("No program with this ID found");
        }
        enrolment.setProgram(findProgram.get());
        enrolment.setTimeOfProgram(enrolDto.getTimeOfProgram());
        enrolment.setDate(enrolDto.getDate());
        Set<Volunteer> volunteers = new HashSet<>();
        enrolment.setVolunteers(volunteers);
        return enrolmentRepository.save(enrolment);
    }

    public Enrolment updateEnrolment(EnrolEditDto enrolEditDto) throws NoEnrolmentFoundExceptions {

        Optional<Enrolment> findEnrolment = enrolmentRepository.findById(enrolEditDto.getId());
        System.out.println(enrolEditDto.getId());
        if (findEnrolment.isEmpty()) {
            throw new NoEnrolmentFoundExceptions("No enrolment with this ID found");
        }
        Enrolment enrolmentFound = findEnrolment.get();
        enrolmentFound.setDate(enrolEditDto.getDate());
        enrolmentFound.setTimeOfProgram(enrolEditDto.getTimeOfProgram());
        return enrolmentRepository.save(enrolmentFound);
    }

    public void addVolunteer(int volunteer_id, int program_id) throws NoVolunteerFoundExceptions, NoEnrolmentFoundExceptions {
        Optional<Volunteer> volunteer = volunteerRepository.findById(volunteer_id);
        if (volunteer.isEmpty()) {
            throw new NoVolunteerFoundExceptions("No volunteer found");
        } Optional<Enrolment> enrolment = enrolmentRepository.findByProgramId(program_id);
        if (enrolment.isEmpty()) {
            throw new NoEnrolmentFoundExceptions("No enrolment found");
        }
        Enrolment enrolmentFound = enrolment.get();
        enrolmentFound.getVolunteers().add(volunteer.get());
        enrolmentRepository.save(enrolmentFound);
    }

    public List<Volunteer> getAllVolunteer(int program_id) throws NoEnrolmentFoundExceptions{
        Optional<Enrolment> enrolment = enrolmentRepository.findByProgramId(program_id);
        if (enrolment.isEmpty()) {
            throw new NoEnrolmentFoundExceptions("No program found");
        }
        //Convert to a array list
        Enrolment enrolmentFound = enrolment.get();
        Set<Volunteer> volunteers = enrolmentFound.getVolunteers();
        return new ArrayList<>(volunteers);
    }

    public void deleteEnrolment(Integer id) throws NoEnrolmentFoundExceptions {
        Optional<Enrolment> enrolment = enrolmentRepository.findById(id);
        if (enrolment.isPresent()) {
            Enrolment existingEnrolment = enrolment.get();
            enrolmentRepository.delete(existingEnrolment);
        } else {
            throw new NoEnrolmentFoundExceptions("Enrolment not found with id: " + id);
        }
    }

    public void removeVolunteerFromEnrolment (Integer volunteerId, Integer programId ) throws NoEnrolmentFoundExceptions, NoVolunteerFoundExceptions {
        Optional<Enrolment> enrolment = enrolmentRepository.findByProgramId(programId);
        Optional <Volunteer> volunteer = volunteerRepository.findById(volunteerId);

        if(enrolment.isEmpty()) {
            throw new NoEnrolmentFoundExceptions("Enrolment with program id of " + programId + " cannot be found!" );
        }

        if(volunteer.isEmpty()) {
            throw new NoVolunteerFoundExceptions(("Volunteer with id of " + volunteerId + " cannot be found!"));
        }
        Enrolment enrolmentFound = enrolment.get();

        enrolmentFound.getVolunteers().remove(volunteer.get());
        enrolmentRepository.save(enrolmentFound);
    }

}