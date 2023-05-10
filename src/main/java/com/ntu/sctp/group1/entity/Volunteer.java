package com.ntu.sctp.group1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="volunteer")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @NotBlank(message = "Name cannot be blank!")
    @Column(name = "name", nullable = false)
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NonNull
    @NotBlank(message = "Email cannot be blank!")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contact")
    private String contact;

    @Column(name = "address")
    private String address;

    @Column(name = "occupation")
    private String occupation = "";

    @Column(name = "education")
    private String education = "";

    @Column(name = "language")
    private String language = "";

    @Column(name = "language2")
    private String language2 = "";


    @Column(name = "language3")
    private String language3 = "";

    @Column(name = "past_experience")
    private String pastExperience = "";

    @Column(name = "criminal_record")
    private boolean hasCriminalRecord;

    @Column(name = "referrer_name")
    private String referrerName = "";

    @Column(name = "referrer_contact")
    private Long referrerContact;

    @Column(name = "profile_picture")
    private String profilePicture = "";

    @Column(name = "created_at", updatable = false)
    Timestamp createdAt = new Timestamp(new Date().getTime());

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    @OneToOne(mappedBy = "volunteer", cascade = CascadeType.ALL)
    private Profile profile;

    @JsonIgnore
    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL)
    List<Availability> availabilities;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "enrolment_volunteer",
            joinColumns = @JoinColumn(name = "volunteer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "enrolment_id", referencedColumnName = "id")
    )
    private Set<Enrolment> enrolments;

}