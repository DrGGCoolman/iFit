package de.gowlr.allcar.services;

import de.gowlr.allcar.web.CarFilterModel;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import de.gowlr.allcar.entities.*;
import de.gowlr.allcar.repositories.*;

/**
 * SearchService
 */
public class SearchService {
    @Autowired
    private ProductTypeRepository ProductTypeRepository;

    public LinkedHashSet<EcProductTypeEntity> search(String searchfor) {
        String[] searchWords = null;
        ArrayList<EcProductTypeEntity> searchResultsWithDupes = new ArrayList<EcProductTypeEntity>();

        if (searchfor != null && !searchfor.isEmpty()) {

            searchWords = searchfor.split(" ");

            // TODO: speichern in DB von searchfor

            for (String word : searchWords) {
                if (ProductTypeRepository.findByEcBrandByBrandIdBrandTitleContainingIgnoreCase(word) != null) {
                    searchResultsWithDupes
                            .addAll(ProductTypeRepository.findByEcBrandByBrandIdBrandTitleContainingIgnoreCase(word));
                }
                if (ProductTypeRepository.findByModelContainingIgnoreCase(word) != null) {
                    searchResultsWithDupes.addAll(ProductTypeRepository.findByModelContainingIgnoreCase(word));
                }
                if (ProductTypeRepository.findByVariantContainingIgnoreCase(word) != null) {
                    searchResultsWithDupes.addAll(ProductTypeRepository.findByVariantContainingIgnoreCase(word));
                }
            }
        }
        return new LinkedHashSet<EcProductTypeEntity>(searchResultsWithDupes);
    }
}