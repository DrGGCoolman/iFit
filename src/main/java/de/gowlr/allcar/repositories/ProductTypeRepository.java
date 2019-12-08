
package de.gowlr.allcar.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import de.gowlr.allcar.entities.EcProductTypeEntity;;

@Repository
public interface ProductTypeRepository extends JpaRepository<EcProductTypeEntity, Integer> {

        Optional<EcProductTypeEntity> findById(Integer id);

        List<EcProductTypeEntity> findByModelContainingIgnoreCase(String model);

        List<EcProductTypeEntity> findByVariantContainingIgnoreCase(String variant);

        List<EcProductTypeEntity> findByEcBrandByBrandIdBrandTitleContainingIgnoreCase(String brandTitle);

        List<EcProductTypeEntity> findByHighlighted(Boolean highlighted);

        // _
        List<EcProductTypeEntity> findByEcCategoryByCategoryIdTitleIn(List<String> titles);

        List<EcProductTypeEntity> findByEcBrandByBrandIdBrandTitleIn(List<String> brandTitle);

        List<EcProductTypeEntity> findByGearingTypeIn(List<String> gearingTypes);

        List<EcProductTypeEntity> findByAgeBetween(Integer min, Integer max);

        List<EcProductTypeEntity> findByAgeGreaterThanEqual(Integer min);

        List<EcProductTypeEntity> findByAgeLessThanEqual(Integer max);

        List<EcProductTypeEntity> findByMinimumAgeGreaterThanEqual(Integer minAge);

        List<EcProductTypeEntity> findByPowerPsGreaterThanEqual(Integer power);

        List<EcProductTypeEntity> findByFuelTypeIn(List<String> fuelTypes);

        List<EcProductTypeEntity> findByDriveSystemIn(List<String> driveSystem);

        // _

        @Query("SELECT DISTINCT fuelType FROM EcProductTypeEntity")
        List<String> findDistinctFuels();

        @Query("SELECT DISTINCT gearingType FROM EcProductTypeEntity")
        List<String> findDistinctTransmissions();

        @Query("SELECT DISTINCT driveSystem FROM EcProductTypeEntity")
        List<String> findDistinctDirveSystems();

        @Query("SELECT DISTINCT minimumAge FROM EcProductTypeEntity")
        List<Integer> findDistinctDriverAges();

        @Query("SELECT MIN(age) FROM EcProductTypeEntity")
        Integer findMinYear();

        @Query("SELECT MIN(powerPs) FROM EcProductTypeEntity")
        Integer findMinPower();

        @Query("SELECT MAX(age) FROM EcProductTypeEntity")
        Integer findMaxYear();

}
