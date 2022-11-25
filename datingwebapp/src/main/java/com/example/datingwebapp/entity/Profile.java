package com.example.datingwebapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="tbl_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


//relation to entity Client, fk = clientId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="clientId", nullable = false)
    private Client client;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTS;

    @Column(nullable = false, length = 30)
    @Pattern(regexp = "[a-z A-Z]{1,30}", message ="Name can not have special characters")
    private String firstName;

    @Column(nullable = false, length = 30)
    @Pattern(regexp = "[a-z A-Z]{1,30}", message ="Name can not have special characters")
    private String lastName;

    @Min(value=0, message = "Age should not be less than 0")
    @Max(value=120, message ="Age should not be greater than 120")
    @Column(nullable = false)
    private int age;

    @Min(value = 80, message = "Height should not be less than 80 cm")
    @Max(value= 250, message = "Height should not be greater than 250 cm")
    @Column(nullable = false)
    private int height;

    public enum Gender {
        MALE, FEMALE,
        }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Size(max = 40, message = "City name can not have more than 40 char")
    @Column(nullable = false, length = 40)
    @Pattern(regexp = "[a-z A-Z]{1,40}", message ="City can not have special characters")
    private String city;

    @Column(nullable = true)
    private String photos;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photoId", nullable = true)
    private Photo image;

    @Max (value =50, message = "Our clients' age are between 25 to 50")
    @Min (value = 25, message =" Our clients' age are between 25 to 50")
    @Column(nullable = false)
    private int preferAge;

    @Size(max = 40, message = "City name can not have more than 40 char")
    @Pattern(regexp = "[a-z A-Z]{1,25}", message ="City can not have special characters")
    @Column(nullable = false, length = 40)
    private String preferCity;

    @Min(value = 80, message = "Height should not be less than 80 cm")
    @Max(value= 250, message = "Height should not be greater than 250 cm")
    @Column(nullable = false)
    private int preferHeight;


    //Selective column to fill
    @Column(nullable = true, length = 60)
    private String hobby;

    @Column(nullable = true, length = 1500)
    private String introYourself;

    @Column(nullable = true)
    private String job;


    @Column(nullable = true)
    private int income;

    public enum EyeColor{
        BROWN, BLUE, GREEN, BLACK
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private EyeColor eyeColor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private EyeColor preferEyeColor;

    public enum HairColor{
        BROWN, BLACK, BLONDE, RED, OTHER
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private HairColor hairColor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private HairColor preferHairColor;

    //    @OneToOne(fetch = FetchType.LAZY, optional = false)
    //    @JoinColumn(name="paymentId", nullable = false)

    private boolean termsAccepted;

    public Profile(Client client, LocalDateTime createdTS, String firstName,String lastName,
        int age,int height,  Gender gender, String city,String photos, int preferAge,
        String preferCity,int preferHeight,String hobby,  String introYourself, String job,
        int income, EyeColor eyeColor,EyeColor preferEyeColor, HairColor hairColor,
        HairColor preferHairColor,boolean termsAccepted){
        this.client= client;
        this.createdTS = createdTS;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.city= city;
        this.photos = photos;
        this.preferAge = preferAge;
        this.preferCity = preferCity;
        this.preferHeight = preferHeight;
        this.hobby = hobby;
        this.introYourself= introYourself;
        this.job = job;
        this.income = income;
        this.eyeColor=eyeColor;
        this.preferEyeColor = preferEyeColor;
        this.hairColor = hairColor;
        this.preferHairColor= preferHairColor;
        this.termsAccepted = termsAccepted;
    }

    public Profile(Client client, String firstName,String lastName,
                   int age,int height,  Gender gender, String city,String photos, int preferAge,
                   String preferCity,int preferHeight,String hobby,  String introYourself, String job,
                   int income, EyeColor eyeColor,EyeColor preferEyeColor, HairColor hairColor,
                   HairColor preferHairColor,boolean termsAccepted){
        this.client= client;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.city= city;
        this.photos = photos;
        this.preferAge = preferAge;
        this.preferCity = preferCity;
        this.preferHeight = preferHeight;
        this.hobby = hobby;
        this.introYourself= introYourself;
        this.job = job;
        this.income = income;
        this.eyeColor=eyeColor;
        this.preferEyeColor = preferEyeColor;
        this.hairColor = hairColor;
        this.preferHairColor= preferHairColor;
        this.termsAccepted = termsAccepted;
    }
    /*public Profile(Client owner, String name, String photo,String intro) {
        this.owner= owner;
        this.name = name;
        this.photo = photo;
        this.intro = intro;
    }*/

}
