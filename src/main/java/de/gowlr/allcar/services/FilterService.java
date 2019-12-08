package de.gowlr.allcar.services;

import de.gowlr.allcar.web.CarFilterModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import de.gowlr.allcar.entities.*;
import de.gowlr.allcar.repositories.*;

/**
 * FilterService
 */
public class FilterService {

    @Autowired
    private ProductTypeRepository ProductTypeRepository;

    public List<EcProductTypeEntity> filter(CarFilterModel carFilter) {

        List<EcProductTypeEntity> filteredResults = ProductTypeRepository.findAll();

        if (!carFilter.SelectedCategories.isEmpty())
            filteredResults
                    .retainAll(ProductTypeRepository.findByEcCategoryByCategoryIdTitleIn(carFilter.SelectedCategories));

        if (!carFilter.SelectedBrands.isEmpty())
            filteredResults
                    .retainAll(ProductTypeRepository.findByEcBrandByBrandIdBrandTitleIn(carFilter.SelectedBrands));

        if (!carFilter.SelectedTransmissions.isEmpty())
            filteredResults.retainAll(ProductTypeRepository.findByGearingTypeIn(carFilter.SelectedTransmissions));

        if (!carFilter.SelectedDriveSystems.isEmpty())
            filteredResults.retainAll(ProductTypeRepository.findByDriveSystemIn(carFilter.SelectedDriveSystems));

        if (!carFilter.SelectedFuels.isEmpty())
            filteredResults.retainAll(ProductTypeRepository.findByFuelTypeIn(carFilter.SelectedFuels));

        if (carFilter.SelectedYearFrom != 0 || carFilter.SelectedYearTo != 0) {
            carFilter.SelectedYearFrom = (carFilter.SelectedYearFrom == 0) ? ProductTypeRepository.findMinYear()
                    : carFilter.SelectedYearFrom;
            carFilter.SelectedYearTo = (carFilter.SelectedYearTo == 0) ? ProductTypeRepository.findMaxYear()
                    : carFilter.SelectedYearTo;
            filteredResults.retainAll(
                    ProductTypeRepository.findByAgeBetween(carFilter.SelectedYearFrom, carFilter.SelectedYearTo));

        }
        if (carFilter.SelectedPowerMin != 0)
            filteredResults.retainAll(ProductTypeRepository.findByPowerPsGreaterThanEqual(carFilter.SelectedPowerMin));

        if (carFilter.SelectedDriverAge != 0)
            filteredResults
                    .retainAll(ProductTypeRepository.findByMinimumAgeGreaterThanEqual(carFilter.SelectedDriverAge));
        return filteredResults;
    }
}