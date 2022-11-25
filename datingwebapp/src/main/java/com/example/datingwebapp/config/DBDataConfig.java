package com.example.datingwebapp.config;


import com.example.datingwebapp.entity.Client;
import com.example.datingwebapp.entity.Message;
import com.example.datingwebapp.entity.Profile;
import com.example.datingwebapp.repository.ClientRepository;
import com.example.datingwebapp.repository.MessageRepository;
import com.example.datingwebapp.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@AllArgsConstructor
public class DBDataConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Bean
    CommandLineRunner addData(ClientRepository clientRepository, ProfileRepository profileRepository
           , MessageRepository messageRepository) {

            if(clientRepository.findFirstByEmailIsNotNull().isEmpty()
                    && profileRepository.findFirstByIntroYourselfIsNotNull().isEmpty()
                    && messageRepository.findFirstByContentIsNotNull().isEmpty() ){


            String password = bCryptPasswordEncoder.encode("password");

            return args -> {

                Client admin = new Client(
                    Client.Role.ADMIN,
                    "admin@gmail.com",
                    password
                );
                Client user1 = new Client(
                        //"jerry",
                        "user1@gmail.com",
                        password
                );
                Client user2 = new Client(
                       // "terry",
                        "user2@gmail.com",
                        password
                );
                Client user3 = new Client(
                        "user3@gmail.com",
                        password
                );
                Client user4 = new Client(
                        "user4@gmail.com",
                        password
                );
                Client user5 = new Client(
                        "user5@gmail.com",
                        password
                );
                Client user6 = new Client(
                        //"user6",
                        "user6@gmail.com",
                        password
                );
                Client user7 = new Client(
                        //"user7",
                        "user7@gmail.com",
                        password
                );
                Client user8 = new Client(
                        //"user8",
                        "user8@gmail.com",
                        password
                );
                Client user9 = new Client(
                        //"user9",
                        "user9@gmail.com",
                        password
                );
                Client user10 = new Client(
                        //"user10",
                        "user10@gmail.com",
                        password
                );
                Client user11 = new Client(
                        //"user11",
                        "user11@gmail.com",
                        password
                );
                Client user12 = new Client(
                        //"user12",
                        "user12@gmail.com",
                        password
                );
                Client user13 = new Client(
                        //"user13",
                        "user13@gmail.com",
                        password
                );
                clientRepository.saveAll(
                        List.of(admin,user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11,user12,user13)
                );

                Profile p1 = new Profile(
                        user1,
                        LocalDateTime.now().plusHours(1),
                        "Jack",
                        "Smith",
                        22,
                        180,
                        Profile.Gender.MALE,
                        "Oakland",
                        "https://maximonline.com/wp-content/uploads/2020/03/average-joe-scaled-1.jpg",
                        26,
                        "Montreal",
                        180,
                        "travel",
                        "i would love to think that i was some some kind of intellectual." ,
                        "student",
                        3000,
                        Profile.EyeColor.BROWN,
                        Profile.EyeColor.BROWN,
                        Profile.HairColor.BROWN,
                        Profile.HairColor.BROWN,
                        true
                );

                Profile p2 = new Profile(
                        user2,
                        LocalDateTime.now().plusHours(2),
                        "Jerry",
                        "Smith",
                        35,
                        175,
                         Profile.Gender.MALE,
                        "Sanmito",
                        "https://img.huffingtonpost.com/asset/55b2af4a170000260056560e.jpeg?ops=scalefit_630_400_noupscale&format=webp",
                        40,
                        "Montreal",
                        180,
                        "cooking",
                        "i am a chef:  1. i am a workaholic. 2. i love to cook  " ,
                                 "chef",
                        3000,
                        Profile.EyeColor.BROWN,
                        Profile.EyeColor.BROWN,
                        Profile.HairColor.BROWN,
                        Profile.HairColor.BROWN,
                        true
                );

                Profile p3 = new Profile(
                        user3,
                        LocalDateTime.now().plusHours(3),
                        "Mike",
                        "Smith",
                        29,
                        165,
                        Profile.Gender.MALE,
                        "Montreal",
                        "https://news.qingdaonews.com/images/attachement/jpg/site1/20160321/ec55f9c0c40f1859896832_small.jpg",                    30,
                        "Montreal",
                        180,
                        "Camping",
                        "i'm not ashamed of much, but writing public text on an online dating site makes me pleasantly uncomfortable.",

                        "artist",
                        5000,
                        Profile.EyeColor.BROWN,
                        Profile.EyeColor.BROWN,
                        Profile.HairColor.BROWN,
                        Profile.HairColor.BROWN,
                        true
                );
                Profile p4 = new Profile(
                        user4,
                        LocalDateTime.now().plusHours(3),
                        "Jerry",
                        "Smith",
                        26,
                        180,
                        Profile.Gender.FEMALE,
                        "Montreal",
                        "http://n.sinaimg.cn/sinacn16/693/w640h853/20180627/0e8b-hencxtv2345774.jpg",
                        30,
                        "Montreal",
                        180,
                        "Reading",
                        "i grew up in a small town in the midwest and have moved around a lot over the last few years.",
                        "teacher",
                        2000,
                        Profile.EyeColor.BROWN,
                        Profile.EyeColor.BROWN,
                        Profile.HairColor.BROWN,
                        Profile.HairColor.BROWN,
                        true
                );
                Profile p5 = new Profile(
                        user5,
                        LocalDateTime.now().plusHours(3),
                        "Jerry",
                        "Smith",
                        26,
                        180,
                        Profile.Gender.MALE,
                        "Montreal",
                        "https://www.elitesingles.com/wp-content/uploads/sites/85/2020/06/2b_en_ta_teaser_sp1-350x264.png",
                        30,
                        "Montreal",
                        180,
                        "movie",
                        "raised by a sailor and a taxidermist in the pacific norhtwest,  ",
                        "lawyer",
                        10000,
                        Profile.EyeColor.BROWN,
                        Profile.EyeColor.BROWN,
                        Profile.HairColor.BROWN,
                        Profile.HairColor.BROWN,
                        true
                );
                Profile p6 = new Profile(
                        user6,
                        LocalDateTime.now().plusHours(3),
                        "Jessica",
                        "Smith",
                        24,
                        160,
                        Profile.Gender.FEMALE,
                        "Montreal",
                        "https://i.insider.com/59b6c4bfba785e36f932a317?width=1000&format=jpeg&auto=webp",
                        30,
                        "Montreal",
                        180,
                        "fashion",
                        "me:  -i work  -i'm fun, i'm adventurous,  i love to laugh.",
                        "actor",
                        3000,
                        Profile.EyeColor.BROWN,
                        Profile.EyeColor.BROWN,
                        Profile.HairColor.BROWN,
                        Profile.HairColor.BROWN,
                        true
                );
                Profile p7 = new Profile(
                        user7,
                        LocalDateTime.now().plusHours(3),
                        "Anna",
                        "Smith",
                        28,
                        164,
                        Profile.Gender.FEMALE,
                        "Montreal",
                        "https://www.perfocal.com/blog/content/images/2021/01/Perfocal_17-11-2019_TYWFAQ_82_standard-3.jpg",
                        30,
                        "Montreal",
                        180,
                        "photo",
                        "english teacher, studying hypnotherapy, founder and active member of a community garden club, part time professional photographer.",
                        "teacher",
                        3000,
                        Profile.EyeColor.BROWN,
                        Profile.EyeColor.BROWN,
                        Profile.HairColor.BROWN,
                        Profile.HairColor.BROWN,
                        true
                );
                Profile p8 = new Profile(
                        user8,
                        LocalDateTime.now().plusHours(3),
                        "Mary",
                        "Smith",
                        29,
                        155,
                        Profile.Gender.FEMALE,
                        "Montreal",
                        "https://miro.medium.com/max/1200/0*qF0r7bmypo45Yit5",
                        30,
                        "Montreal",
                        180,
                        "music",
                        "above all else i am, and always have been, a good girl. being a good girl is everything. it means being honest and true.",
                        "engineer",
                        3000,
                        Profile.EyeColor.BROWN,
                        Profile.EyeColor.BROWN,
                        Profile.HairColor.BROWN,
                        Profile.HairColor.BROWN,
                        true
                );
                Profile p9 = new Profile(
                        user9,
                        LocalDateTime.now().plusHours(3),
                        "Ken",
                        "Smith",
                        32,
                        185,
                        Profile.Gender.MALE,
                        "Montreal",
                        "https://i.insider.com/59b6c4bfba785e36f932a318?width=1600&format=jpeg&auto=webp",
                        30,
                        "Montreal",
                        180,
                        "car rides",
                        "hello, i am outgoing, family oriented. i am a single father of a wonder 7 yr old.",
                        "CEO",
                        3000,
                        Profile.EyeColor.BROWN,
                        Profile.EyeColor.BROWN,
                        Profile.HairColor.BROWN,
                        Profile.HairColor.BROWN,
                        true
                );

                Profile p10 = new Profile(
                        user10,
                        LocalDateTime.now().plusHours(3),
                        "Kevin",
                        "Smith",
                        37,
                        176,
                        Profile.Gender.MALE,
                        "Montreal",
                        "https://i.insider.com/59b6c4bfba785e36f932a318?width=1600&format=jpeg&auto=webp",
                        30,
                        "Montreal",
                        180,
                        "bike",
                        "i'm a really simple person. i have an anaytical personality, but i love people. i build all relationships based on trust. i work quite a bit and always find time to have fun.",
                        "engineer",
                        3000,
                        Profile.EyeColor.BROWN,
                        Profile.EyeColor.BROWN,
                        Profile.HairColor.BROWN,
                        Profile.HairColor.BROWN,
                        true
                );
                profileRepository.saveAll(
                        List.of(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10)
                );

                Message m1 = new Message(
                        user1,
                        user2,
                        "Hi, nice to meet you!",
                        LocalDateTime.now().plusHours(1),
                        "msg",
                        "delieved"

                );

                Message m2 = new Message(
                        user2,
                        user4,
                        "",
                        LocalDateTime.now().plusHours(2),
                        "like",
                        "delieved"

                );

                Message m3 = new Message(
                        user3,//user2,
                        user4,
                        "Just want to say hello :)",
                        LocalDateTime.now().plusHours(3),
                        "msg",
                        "delieved"

                );

                messageRepository.saveAll(
                        List.of(m1,m2,m3)
                );



            };
        }
        return args -> {};
    }
}

