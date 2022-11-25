package com.example.datingwebapp.repository;
import com.example.datingwebapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.Optional;
import java.util.List;



@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);
    Optional<Client> findFirstByEmailIsNotNull();
    Optional<Client> findById(long id);


//    Optional<Client> findFirstByUsernameIsNotNull();
}
