package de.gowlr.allcar.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.gowlr.allcar.entities.*;

@Repository
public interface PictureRepository extends JpaRepository<EcPicturesEntity, Integer> {

    List<EcPicturesEntity> findAll();

    Optional<EcPicturesEntity> findById(Integer id);

    List<EcPicturesEntity> findBy();

}