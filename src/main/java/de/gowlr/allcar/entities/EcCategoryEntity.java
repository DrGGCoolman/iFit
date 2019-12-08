package de.gowlr.allcar.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ec_category", schema = "public", catalog = "ec")
public class EcCategoryEntity {
    private Integer id;
    private String title;
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
    @Column(name = "title", nullable = false, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EcCategoryEntity that = (EcCategoryEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @OneToMany(mappedBy = "ecCategoryByCategoryId")
    public Collection<EcProductTypeEntity> getEcProductTypesById() {
        return ecProductTypesById;
    }

    public void setEcProductTypesById(Collection<EcProductTypeEntity> ecProductTypesById) {
        this.ecProductTypesById = ecProductTypesById;
    }
}
