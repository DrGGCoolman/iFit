package de.gowlr.allcar.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ec_brand", schema = "public", catalog = "ec")
public class EcBrandEntity {
    private Integer id;
    private String brandTitle;
    private Collection<EcProductTypeEntity> ecProductTypesById;

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
    @Column(name = "brand_title", nullable = false, length = -1)
    public String getBrandTitle() {
        return brandTitle;
    }

    public void setBrandTitle(String brandTitle) {
        this.brandTitle = brandTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EcBrandEntity that = (EcBrandEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(brandTitle, that.brandTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandTitle);
    }

    @OneToMany(mappedBy = "ecBrandByBrandId")
    public Collection<EcProductTypeEntity> getEcProductTypesById() {
        return ecProductTypesById;
    }

    public void setEcProductTypesById(Collection<EcProductTypeEntity> ecProductTypesById) {
        this.ecProductTypesById = ecProductTypesById;
    }
}
