package com.example.datingwebapp.repository;

import com.example.datingwebapp.entity.Photo;
import com.example.datingwebapp.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
//    Optional<Photo> findByProfileId(Long profileId);
}
