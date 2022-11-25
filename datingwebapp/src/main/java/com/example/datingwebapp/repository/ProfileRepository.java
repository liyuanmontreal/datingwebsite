package com.example.datingwebapp.repository;

import com.example.datingwebapp.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findTop10ByOrderByCreatedTSDesc();
    Optional<Profile> findFirstByIntroYourselfIsNotNull();
    Optional<Profile>findById(Long profileId);

    Optional<Profile> findByCity(String city);

    Optional<Profile> findByHeight(int height);

    Optional<Profile> findByHobby (String hobby);

    Optional<Profile> findByAge(int age);
    Optional<Profile> findProfileById(Long id);

    ArrayList<Profile> findAllByAgeIsBetween(int min, int max);

    ArrayList<Profile> findAllByGenderIn(ArrayList<Profile.Gender> gender);

    ArrayList<Profile> findAllByCityEquals(String city);

    ArrayList<Profile> findAllByHeightIsBetween(int min, int max);



    @Query(value = "select * from tbl_profile order by rand() limit ?3", nativeQuery = true)
    List <Profile> getRandomProfile(int num);

    List <Profile> findByGenderOrderByCreatedTSDesc(Profile.Gender gender);
}
