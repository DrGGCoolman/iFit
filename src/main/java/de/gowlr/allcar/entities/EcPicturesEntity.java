package de.gowlr.allcar.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ec_pictures", schema = "public", catalog = "ec")
public class EcPicturesEntity {
    private Integer id;
    private String title;
    private String filePath;
    private EcProductTypeEntity ecProductTypeByProductId;

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

    @Basic
    @Column(name = "file_path", nullable = false, length = -1)
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EcPicturesEntity that = (EcPicturesEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title)
                && Objects.equals(filePath, that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, filePath);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public EcProductTypeEntity getEcProductTypeByProductId() {
        return ecProductTypeByProductId;
    }

    public void setEcProductTypeByProductId(EcProductTypeEntity ecProductTypeByProductId) {
        this.ecProductTypeByProductId = ecProductTypeByProductId;
    }
}
