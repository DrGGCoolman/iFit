package de.gowlr.allcar.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ec_product_type", schema = "public", catalog = "ec")
public class EcProductTypeEntity {
    private Integer id;
    private String model;
    private String variant;
    private String gearingType;
    private Integer age;
    private Double weightKg;
    private Integer powerPs;
    private String engine;
    private Integer cubicCapacityCm3;
    private Double nm;
    private String driveSystem;
    private String fuelType;
    private Double zeroToHundred;
    private Integer seats;
    private Integer doors;
    private Boolean addDriver;
    private Integer minimumAge;
    private String bodyColor;
    private String upholstery;
    private Boolean massageSeats;
    private Boolean displaysRear;
    private Double price200;
    private Double price500;
    private Double price1000;
    private String blockedWhen;
    private Boolean highlighted;
    private Collection<EcPicturesEntity> ecPicturesById;
    private EcCategoryEntity ecCategoryByCategoryId;
    private EcBrandEntity ecBrandByBrandId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "model", nullable = false, length = -1)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "variant", nullable = true, length = -1)
    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    @Basic
    @Column(name = "gearing_type", nullable = false, length = -1)
    public String getGearingType() {
        return gearingType;
    }

    public void setGearingType(String gearingType) {
        this.gearingType = gearingType;
    }

    @Basic
    @Column(name = "age", nullable = false)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "weight_kg", nullable = false, precision = 0)
    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    @Basic
    @Column(name = "power_ps", nullable = false)
    public Integer getPowerPs() {
        return powerPs;
    }

    public void setPowerPs(Integer powerPs) {
        this.powerPs = powerPs;
    }

    @Basic
    @Column(name = "engine", nullable = false, length = -1)
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Basic
    @Column(name = "cubic_capacity_cm3", nullable = false)
    public Integer getCubicCapacityCm3() {
        return cubicCapacityCm3;
    }

    public void setCubicCapacityCm3(Integer cubicCapacityCm3) {
        this.cubicCapacityCm3 = cubicCapacityCm3;
    }

    @Basic
    @Column(name = "nm", nullable = false, precision = 0)
    public Double getNm() {
        return nm;
    }

    public void setNm(Double nm) {
        this.nm = nm;
    }

    @Basic
    @Column(name = "drive_system", nullable = false, length = -1)
    public String getDriveSystem() {
        return driveSystem;
    }

    public void setDriveSystem(String driveSystem) {
        this.driveSystem = driveSystem;
    }

    @Basic
    @Column(name = "fuel_type", nullable = false, length = -1)
    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Basic
    @Column(name = "zero_to_hundred", nullable = false, precision = 0)
    public Double getZeroToHundred() {
        return zeroToHundred;
    }

    public void setZeroToHundred(Double zeroToHundred) {
        this.zeroToHundred = zeroToHundred;
    }

    @Basic
    @Column(name = "seats", nullable = false)
    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Basic
    @Column(name = "doors", nullable = false)
    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    @Basic
    @Column(name = "add_driver", nullable = false)
    public Boolean getAddDriver() {
        return addDriver;
    }

    public void setAddDriver(Boolean addDriver) {
        this.addDriver = addDriver;
    }

    @Basic
    @Column(name = "minimum_age", nullable = false)
    public Integer getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }

    @Basic
    @Column(name = "body_color", nullable = false, length = -1)
    public String getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(String bodyColor) {
        this.bodyColor = bodyColor;
    }

    @Basic
    @Column(name = "upholstery", nullable = false, length = -1)
    public String getUpholstery() {
        return upholstery;
    }

    public void setUpholstery(String upholstery) {
        this.upholstery = upholstery;
    }

    @Basic
    @Column(name = "massage_seats", nullable = false)
    public Boolean getMassageSeats() {
        return massageSeats;
    }

    public void setMassageSeats(Boolean massageSeats) {
        this.massageSeats = massageSeats;
    }

    @Basic
    @Column(name = "displays_rear", nullable = false)
    public Boolean getDisplaysRear() {
        return displaysRear;
    }

    public void setDisplaysRear(Boolean displaysRear) {
        this.displaysRear = displaysRear;
    }

    @Basic
    @Column(name = "price_200", nullable = false, precision = 0)
    public Double getPrice200() {
        return price200;
    }

    public void setPrice200(Double price200) {
        this.price200 = price200;
    }

    @Basic
    @Column(name = "price_500", nullable = false, precision = 0)
    public Double getPrice500() {
        return price500;
    }

    public void setPrice500(Double price500) {
        this.price500 = price500;
    }

    @Basic
    @Column(name = "price_1000", nullable = false, precision = 0)
    public Double getPrice1000() {
        return price1000;
    }

    public void setPrice1000(Double price1000) {
        this.price1000 = price1000;
    }

    @Basic
    @Column(name = "blocked_when", nullable = true, length = -1)
    public String getBlockedWhen() {
        return blockedWhen;
    }

    public void setBlockedWhen(String blockedWhen) {
        this.blockedWhen = blockedWhen;
    }

    @Basic
    @Column(name = "highlighted", nullable = true)
    public Boolean getHighlighted() {
        return highlighted;
    }

    public void setHighlighted(Boolean highlighted) {
        this.highlighted = highlighted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EcProductTypeEntity that = (EcProductTypeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(model, that.model) && Objects.equals(variant, that.variant)
                && Objects.equals(gearingType, that.gearingType) && Objects.equals(age, that.age)
                && Objects.equals(weightKg, that.weightKg) && Objects.equals(powerPs, that.powerPs)
                && Objects.equals(engine, that.engine) && Objects.equals(cubicCapacityCm3, that.cubicCapacityCm3)
                && Objects.equals(nm, that.nm) && Objects.equals(driveSystem, that.driveSystem)
                && Objects.equals(fuelType, that.fuelType) && Objects.equals(zeroToHundred, that.zeroToHundred)
                && Objects.equals(seats, that.seats) && Objects.equals(doors, that.doors)
                && Objects.equals(addDriver, that.addDriver) && Objects.equals(minimumAge, that.minimumAge)
                && Objects.equals(bodyColor, that.bodyColor) && Objects.equals(upholstery, that.upholstery)
                && Objects.equals(massageSeats, that.massageSeats) && Objects.equals(displaysRear, that.displaysRear)
                && Objects.equals(price200, that.price200) && Objects.equals(price500, that.price500)
                && Objects.equals(price1000, that.price1000) && Objects.equals(blockedWhen, that.blockedWhen)
                && Objects.equals(highlighted, that.highlighted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, variant, gearingType, age, weightKg, powerPs, engine, cubicCapacityCm3, nm,
                driveSystem, fuelType, zeroToHundred, seats, doors, addDriver, minimumAge, bodyColor, upholstery,
                massageSeats, displaysRear, price200, price500, price1000, blockedWhen, highlighted);
    }

    @OneToMany(mappedBy = "ecProductTypeByProductId", fetch = FetchType.EAGER)
    public Collection<EcPicturesEntity> getEcPicturesById() {
        return ecPicturesById;
    }

    public void setEcPicturesById(Collection<EcPicturesEntity> ecPicturesById) {
        this.ecPicturesById = ecPicturesById;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public EcCategoryEntity getEcCategoryByCategoryId() {
        return ecCategoryByCategoryId;
    }

    public void setEcCategoryByCategoryId(EcCategoryEntity ecCategoryByCategoryId) {
        this.ecCategoryByCategoryId = ecCategoryByCategoryId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    public EcBrandEntity getEcBrandByBrandId() {
        return ecBrandByBrandId;
    }

    public void setEcBrandByBrandId(EcBrandEntity ecBrandByBrandId) {
        this.ecBrandByBrandId = ecBrandByBrandId;
    }
}
