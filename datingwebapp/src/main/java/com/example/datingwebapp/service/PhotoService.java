package com.example.datingwebapp.service;

import com.example.datingwebapp.entity.Photo;
import com.example.datingwebapp.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Transactional
    public void savePhoto(Photo photo){
        photoRepository.save(photo);
    }

    @Transactional
    public List<Photo> getAllPhotos(){
        return photoRepository.findAll();
    }



    @Transactional
    public Optional<Photo> getPhotoById(Long id){
        return photoRepository.findById(id);
    }

//    @Transactional
//    public Optional<Photo> getPhotoByProfileId(Long profileId) {return photoRepository.findByProfileId(profileId);}

}
