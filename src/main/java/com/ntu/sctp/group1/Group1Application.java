package com.ntu.sctp.group1;

import com.ntu.sctp.group1.entity.*;
import com.ntu.sctp.group1.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;


@SpringBootApplication
public class Group1Application {

	@Autowired
	VolunteerRepository volunteerRepo;

	@Autowired
	ProfileRepository profileRepo;

	@Autowired
	ProgramRepository programRepo;

	@Autowired
	EnrolmentRepository enrolmentRepository;

//	@Autowired
//	AvailabilityRepository availRepo;

	@Autowired
	UserRepository userRepo;



	public static void main(String[] args) {
		SpringApplication.run(Group1Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@overide
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}


	@PostConstruct
	public void seeder () {

		UserCredentials user = new UserCredentials();
		UserCredentials user2 = new UserCredentials();
		UserCredentials user3 = new UserCredentials();
		UserCredentials admin = new UserCredentials();
		UserCredentials user5 = new UserCredentials();
		UserCredentials user6 = new UserCredentials();
		UserCredentials user7 = new UserCredentials();
		UserCredentials user8 = new UserCredentials();


		Profile profile = new Profile();
		Profile profile2 = new Profile();
		Profile profile3 = new Profile();
		Profile profile4 = new Profile();
		Profile profile5 = new Profile();
		Profile profile6 = new Profile();
		Profile profile7 = new Profile();
		Profile profile8 = new Profile();

		Volunteer person = new Volunteer("Coco Tan", "coco@mail.com");
		person.setAddress("Blk 233, Bishan street 12");
		person.setProfilePicture("https://mountstudio.com.sg/wp-content/uploads/corporate-headshot-studio-singapore.jpg");
		person.setDateOfBirth(LocalDate.parse(("1980-07-01")));
		person.setContact("98273628");
		person.setEducation("diploma");
		person.setLanguage("chinese");
		person.setHasCriminalRecord(false);
		person.setPastExperience("soup kitchen, child care");
		person.setReferrerName("Oliver");
		person.setReferrerContact(Long.parseLong("92378229"));

		Volunteer person2 = new Volunteer("Enzo Lorenzo", "may@mail.com");
		person2.setAddress("Blk 233, Ang mo kio street 12");
		person2.setProfilePicture("https://pbs.twimg.com/media/DwoqWMYWwAAow84.jpg");
		person2.setDateOfBirth(LocalDate.parse(("1990-07-01")));
		person2.setContact("987979792");
		person2.setEducation("master");
		person2.setLanguage("english");
		person2.setHasCriminalRecord(false);
		person2.setPastExperience("yoga nursing");
		person2.setReferrerName("Oliver");
		person2.setReferrerContact(Long.parseLong("92378229"));

		Volunteer person3 = new Volunteer("Ace Tay", "ace@mail.com");
		person3.setProfilePicture("https://www.whiteroomstudio.com.sg/wordpress/wp-content/uploads/2021/09/headshots-3.jpg");
		person3.setAddress("Blk 233, yishun street 12");
		person3.setDateOfBirth(LocalDate.parse(("1997-07-01")));
		person3.setContact("937464545");
		person3.setEducation("degree");
		person3.setLanguage("english");
		person3.setHasCriminalRecord(false);
		person3.setPastExperience("teaching childcare");
		person3.setReferrerName("Oliver");
		person3.setReferrerContact(Long.parseLong("92378229"));

		Volunteer person4 = new Volunteer("Mary Cheng", "Mary@mail.com");
		person4.setProfilePicture("https://mountstudio.com.sg/wp-content/uploads/Beauty-Headshot-Photographer-Singapore.jpg");
		person4.setAddress("Blk 233, Pasir ris street 12");
		person4.setDateOfBirth(LocalDate.parse(("1997-07-01")));
		person4.setContact("937464545");
		person4.setEducation("degree");
		person4.setLanguage("english");
		person4.setHasCriminalRecord(false);
		person4.setPastExperience("teaching elderly care");
		person4.setReferrerName("Oliver");
		person4.setReferrerContact(Long.parseLong("92378229"));

		Volunteer person5 = new Volunteer("Cherry Cheng", "cherry@mail.com");
		person5.setProfilePicture("https://theindependent.sg/wp-content/uploads/2013/09/Singapore-Girl-1-433x385.jpg");
		person5.setAddress("Blk 233, Pasir ris street 12");
		person5.setDateOfBirth(LocalDate.parse(("1997-07-01")));
		person5.setContact("937464545");
		person5.setEducation("diploma");
		person5.setLanguage("chinese");
		person5.setHasCriminalRecord(false);
		person5.setPastExperience("teaching, elderly care, nursing");
		person5.setReferrerName("Oliver");
		person5.setReferrerContact(Long.parseLong("92378229"));

		Volunteer person6 = new Volunteer("Yuna", "Yuna@mail.com");
		person6.setProfilePicture("https://images.lifestyleasia.com/wp-content/uploads/sites/5/2022/11/29132842/20-Yuna.jpg");
		person6.setAddress("Blk 233, Pasir ris street 12");
		person6.setDateOfBirth(LocalDate.parse(("1997-07-01")));
		person6.setContact("937464545");
		person6.setEducation("diploma");
		person6.setLanguage("malay");
		person6.setHasCriminalRecord(false);
		person6.setPastExperience("teaching, elderly care, nursing, singing, acting");
		person6.setReferrerName("Oliver");
		person6.setReferrerContact(Long.parseLong("92378229"));

		Volunteer person7 = new Volunteer("Chandra", "chandra@mail.com");
		person7.setProfilePicture("https://www.asiabiz.sg/wp-content/uploads/shutterstock_31682071-a.jpg");
		person7.setAddress("Blk 233, Pasir ris street 12");
		person7.setDateOfBirth(LocalDate.parse(("1997-07-01")));
		person7.setContact("937464545");
		person7.setEducation("master");
		person7.setLanguage("indian");
		person7.setLanguage2("english");
		person7.setHasCriminalRecord(false);
		person7.setPastExperience("teaching, elderly care, public speaking, singing, dancing");
		person7.setReferrerName("Oliver");
		person7.setReferrerContact(Long.parseLong("92378229"));

		Volunteer person8 = new Volunteer("Adrain", "Adrian@mail.com");
		person8.setProfilePicture("https://cdn.tatlerasia.com/tatlerasia/i/2021/11/24121529-adrian-pang_cover_1200x1800.jpg");
		person8.setAddress("Blk 233, Pasir ris street 12");
		person8.setDateOfBirth(LocalDate.parse(("1997-07-01")));
		person8.setContact("937464545");
		person8.setEducation("master");
		person8.setLanguage("indian");
		person8.setLanguage2("english");
		person8.setHasCriminalRecord(false);
		person8.setPastExperience("teaching, elderly care, public speaking, singing, dancing");
		person8.setReferrerName("Oliver");
		person8.setReferrerContact(Long.parseLong("92378229"));

		person = volunteerRepo.save(person);
		person2 = volunteerRepo.save(person2);
		person3 = volunteerRepo.save(person3);
		person4 = volunteerRepo.save(person4);
		person5 = volunteerRepo.save(person5);
		person6 = volunteerRepo.save(person6);
		person7 = volunteerRepo.save(person7);
		person8 = volunteerRepo.save(person8);

		user.setUsername(person.getEmail());
		user.setUid("aId0fTgOlKdrNpCd6IapWFnBC2D3");
		user.setTokenIsActive(false);
		user.setVolunteerId(person.getId());
		user.setRole(Role.USER);
		userRepo.save(user);

		user2.setUsername(person2.getEmail());
		user2.setUid("Bq1SrD7WrHY4TNoBIY2Nw4v7K6x1");
		user2.setTokenIsActive(false);
		user2.setVolunteerId(person2.getId());
		user2.setRole(Role.USER);
		userRepo.save(user2);

		user3.setUsername(person4.getEmail());
		user3.setUid("SehwggBQfPOpHDtUKvv6OtD0IUA3");
		user3.setTokenIsActive(false);
		user3.setVolunteerId(person4.getId());
		user3.setRole(Role.USER);
		userRepo.save(user3);

		admin.setUsername(person3.getEmail());
		admin.setUid("YH70F63hN4gx3FgKW5yQJYP14az2");
		admin.setTokenIsActive(false);
		admin.setVolunteerId(person3.getId());
		admin.setRole(Role.ADMIN);
		userRepo.save(admin);

		user5.setUsername(person5.getEmail());
		user5.setUid("QiduurfOymRClhhAv6PZCJtpJ2d2");
		user5.setTokenIsActive(false);
		user5.setVolunteerId(person5.getId());
		user5.setRole(Role.USER);
		userRepo.save(user5);

		user6.setUsername(person6.getEmail());
		user6.setUid("oOnrhkENLHbRPrs3dt8IN1hX1mM2");
		user6.setTokenIsActive(false);
		user6.setVolunteerId(person6.getId());
		user6.setRole(Role.USER);
		userRepo.save(user6);

		user7.setUsername(person7.getEmail());
		user7.setUid("sat4suIMMaapRvPuybHabeGlym52");
		user7.setTokenIsActive(false);
		user7.setVolunteerId(person7.getId());
		user7.setRole(Role.USER);
		userRepo.save(user7);

		user8.setUsername(person8.getEmail());
		user8.setUid("r07EMIjXmKX0Io7eXHyPNKr4Hyq2");
		user8.setTokenIsActive(false);
		user8.setVolunteerId(person8.getId());
		user8.setRole(Role.USER);
		userRepo.save(user8);

		profile.setVolunteer(person);
		profile.setProfilePicture("https://cdn.vectorstock.com/i/preview-1x/51/48/cartoon-character-in-glasses-avatar-young-man-vector-33215148.jpg");
		profile2.setVolunteer(person2);
		profile2.setProfilePicture("https://www.shutterstock.com/image-vector/man-character-face-avatar-glasses-260nw-542759665.jpg");
		profile3.setVolunteer(person3);
		profile3.setProfilePicture("https://previews.123rf.com/images/vectorkif/vectorkif1904/vectorkif190400101/121272615-face-expression-of-stylish-modern-young-man-sad-or-tired-male-emotion-handsome-cartoon-character.jpg");
		profile4.setVolunteer(person4);
		profile4.setProfilePicture("https://static01.nyt.com/images/2020/04/27/us/politics/00-trump-cand-page/00-trump-cand-page-mediumSquareAt3X.jpg");
		profile5.setVolunteer(person5);
		profile5.setProfilePicture("https://theindependent.sg/wp-content/uploads/2014/03/singapore_girl2.jpg");
		profile6.setVolunteer(person6);
		profile6.setProfilePicture("https://images.lifestyleasia.com/wp-content/uploads/sites/5/2022/11/29132842/20-Yuna.jpg");
		profile7.setVolunteer(person7);
		profile7.setProfilePicture("https://www.asiabiz.sg/wp-content/uploads/shutterstock_31682071-a.jpg");
		profile8.setVolunteer(person8);
		profile8.setProfilePicture("https://cdn.tatlerasia.com/tatlerasia/i/2021/11/24121529-adrian-pang_cover_1200x1800.jpg");

		profileRepo.save(profile);
		profileRepo.save(profile2);
		profileRepo.save(profile3);
		profileRepo.save(profile4);
		profileRepo.save(profile5);
		profileRepo.save(profile6);
		profileRepo.save(profile7);
		profileRepo.save(profile8);

		Program program1 = new Program();
		Enrolment enrolment1 = new Enrolment();
		program1.setTimeOfProgram("Full day");
		program1.setName("Soup Kitchen");
		program1.setPhoto("https://assets.avenueone.sg/wp-content/uploads/2019/11/where-to-volunteer-in-Singapore-Willing-Hearts.jpeg");
		program1.setDate(LocalDate.parse(("2023-04-01")));
		program1.setDescription("Hone your cooking skills and bring joy to others!");
		program1.setVolunteersRequired(20);
		program1.setNoOfVolunteers(0);
		enrolment1.setProgram(program1);
		enrolment1.setTimeOfProgram(program1.getTimeOfProgram());
		enrolment1.setDate(program1.getDate());
		enrolment1.setVolunteers(new HashSet<>());

		Program program2 = new Program();
		Enrolment enrolment2 = new Enrolment();
		program2.setTimeOfProgram("0800hrs - 1200hrs");
		program2.setPhoto("https://www.ahealingheartmedical.com/assets/img/team/A-Healing-Heart-Medical-Team.webp");
		program2.setName("Elderly care");
		program2.setDescription("Care for our seniors!");
		program2.setDate(LocalDate.parse(("2023-04-02")));
		program2.setVolunteersRequired(15);
		program2.setNoOfVolunteers(0);
		enrolment2.setProgram(program2);
		enrolment2.setTimeOfProgram(program2.getTimeOfProgram());
		enrolment2.setDate(program2.getDate());
		enrolment2.setVolunteers(new HashSet<>());

		Program program3 = new Program();
		Enrolment enrolment3 = new Enrolment();
		program3.setTimeOfProgram("Full day");
		program3.setPhoto("https://wayd-sg.s3.ap-southeast-1.amazonaws.com/img/2020/08/20002802/20200817_Willing-Hearts_CCH9667-scaled.jpg");
		program3.setName("Soup Kitchen");
		program3.setDate(LocalDate.parse(("2023-04-03")));
		program3.setDescription("Hone your cooking skills and spread the joy to others!");
		program3.setVolunteersRequired(15);
		program3.setNoOfVolunteers(0);
		enrolment3.setProgram(program3);
		enrolment3.setTimeOfProgram(program3.getTimeOfProgram());
		enrolment3.setDate(program3.getDate());
		enrolment3.setVolunteers(new HashSet<>());

		Program program4 = new Program();
		Enrolment enrolment4 = new Enrolment();
		program4.setTimeOfProgram("Full day");
		program4.setPhoto("https://www.sassymamasg.com/wp-content/uploads/2018/07/Singapore-Childrens-association-kids-charity-walk.jpg");
		program4.setName("Exploration");
		program4.setDate(LocalDate.parse(("2023-04-04")));
		program4.setDescription("Bring the children to various places of interests");
		program4.setVolunteersRequired(15);
		program4.setNoOfVolunteers(0);
		enrolment4.setProgram(program4);
		enrolment4.setTimeOfProgram(program4.getTimeOfProgram());
		enrolment4.setDate(program4.getDate());
		enrolment4.setVolunteers(new HashSet<>());

		Program program5 = new Program();
		Enrolment enrolment5 = new Enrolment();
		program5.setTimeOfProgram("Full day");
		program5.setPhoto("https://media.timeout.com/images/105905438/750/562/image.jpg");
		program5.setName("Donation events");
		program5.setDate(LocalDate.parse(("2023-04-05")));
		program5.setDescription("To source for donations at various locations");
		program5.setVolunteersRequired(15);
		program5.setNoOfVolunteers(0);
		enrolment5.setProgram(program5);
		enrolment5.setTimeOfProgram(program5.getTimeOfProgram());
		enrolment5.setDate(program5.getDate());
		enrolment5.setVolunteers(new HashSet<>());

		Program program6 = new Program();
		Enrolment enrolment6 = new Enrolment();
		program6.setTimeOfProgram("Full day");
		program6.setPhoto("https://thesmartlocal.com/wp-content/uploads/2018/11/images_easyblog_articles_7240_b2ap3_large_charity-subscriptions-4.png");
		program6.setName("Donation events");
		program6.setDate(LocalDate.parse(("2023-04-02")));
		program6.setDescription("To source for donations at various locations");
		program6.setVolunteersRequired(15);
		program6.setNoOfVolunteers(0);
		enrolment6.setProgram(program6);
		enrolment6.setTimeOfProgram(program6.getTimeOfProgram());
		enrolment6.setDate(program6.getDate());
		enrolment6.setVolunteers(new HashSet<>());

		programRepo.saveAll(List.of(program1, program2, program3, program4, program5, program6));
		enrolmentRepository.saveAll(List.of(enrolment1, enrolment2, enrolment3, enrolment4, enrolment5, enrolment6));

//		Availability avail = new Availability();
//		avail.setDate(LocalDate.parse(("2025-07-01")));
//		avail.setAvail(true);
//		availRepo.save(avail);


	}





}
