package de.gowlr.allcar.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ec_search_words", schema = "public", catalog = "ec")
public class EcSearchWordsEntity {
    private Integer id;
    private String searchWords;
    private EcUserEntity ecUserByUserId;

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
    @Column(name = "search_words", nullable = false, length = -1)
    public String getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(String searchWords) {
        this.searchWords = searchWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EcSearchWordsEntity that = (EcSearchWordsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(searchWords, that.searchWords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, searchWords);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public EcUserEntity getEcUserByUserId() {
        return ecUserByUserId;
    }

    public void setEcUserByUserId(EcUserEntity ecUserByUserId) {
        this.ecUserByUserId = ecUserByUserId;
    }
}
