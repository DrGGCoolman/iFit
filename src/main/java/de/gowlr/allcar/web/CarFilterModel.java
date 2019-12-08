package de.gowlr.allcar.web;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import de.gowlr.allcar.entities.*;
import de.gowlr.allcar.repositories.BrandRepository;
import de.gowlr.allcar.repositories.CategoryRepository;
import de.gowlr.allcar.repositories.ProductTypeRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CarFilterModel {

    @Getter
    private List<EcCategoryEntity> Categories;
    @Getter
    private Iterable<EcBrandEntity> Brands;
    @Getter
    private List<String> Transmissions;
    @Getter
    private int YearFrom;
    @Getter
    private int YearTo;
    @Getter
    private int PowerMin;
    @Getter
    private List<String> DriveSystems;
    @Getter
    private List<String> Fuels;
    @Getter
    private List<Integer> DriverAges;

    @Getter
    @Setter
    public List<String> SelectedCategories;
    @Getter
    @Setter
    public List<String> SelectedBrands;
    @Getter
    @Setter
    public List<String> SelectedTransmissions;
    @Getter
    @Setter
    public int SelectedYearFrom;
    @Getter
    @Setter
    public int SelectedYearTo;
    @Getter
    @Setter
    public int SelectedPowerMin;
    @Getter
    @Setter
    public List<String> SelectedDriveSystems;
    @Getter
    @Setter
    public List<String> SelectedFuels;
    @Getter
    @Setter
    public Integer SelectedDriverAge;

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository catRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @PostConstruct
    private void init() {
        this.Categories = catRepository.findAll();
        this.Brands = brandRepository.findAll();
        this.Fuels = productTypeRepository.findDistinctFuels();
        this.Transmissions = productTypeRepository.findDistinctTransmissions();
        this.DriveSystems = productTypeRepository.findDistinctDirveSystems();
        this.DriverAges = productTypeRepository.findDistinctDriverAges();
        this.YearFrom = productTypeRepository.findMinYear();
        this.YearTo = productTypeRepository.findMaxYear();
    }

}