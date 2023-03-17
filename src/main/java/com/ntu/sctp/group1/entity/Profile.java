package com.ntu.sctp.group1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="profile")
public class Profile {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    
    @Column(name = "interests")
    private String interests = "";

    @Column(name = "hobbies")
    private String hobbies = "";

    @Column(name = "professional_experience")
    private String professionalExperience = "";

    @Column(name = "profile_picture")
    private String profilePicture = "";

//    @OneToOne(mappedBy = "profile")
//    private Volunteer volunteer;

    @OneToOne(mappedBy = "profile", orphanRemoval = true)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Volunteer volunteer;;
}
