package de.gowlr.allcar.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.gowlr.allcar.entities.EcUserEntity;

@Repository
public interface UserRepository extends JpaRepository<EcUserEntity, Integer> {

    List<EcUserEntity> findAll();

    Optional<EcUserEntity> findById(Integer id);

    EcUserEntity findByUsername(String username);

    EcUserEntity findByUsernameIgnoreCase(String username);

    List<EcUserEntity> findByRole(String role);

}