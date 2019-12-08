
package de.gowlr.allcar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.gowlr.allcar.entities.EcBrandEntity;

@Repository
public interface BrandRepository extends JpaRepository<EcBrandEntity, Integer> {

}
