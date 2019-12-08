package de.gowlr.allcar.entities;

import javax.persistence.*;

import org.springframework.security.core.context.SecurityContextHolder;

import de.gowlr.allcar.services.UserAdapter;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ec_user", schema = "public", catalog = "ec")
public class EcUserEntity {
    private Integer id;
    private String role;
    private String name;
    private String firstname;
    private String gender;
    private String username;
    private String password;
    private String title;
    private Date birth;
    private String street;
    private Integer houseNumber;
    private Integer postCode;
    private String city;
    private Collection<EcSearchWordsEntity> ecSearchWordsById;

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
    @Column(name = "role", nullable = true, length = -1)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "firstname", nullable = true, length = -1)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "gender", nullable = true, length = -1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "e_mail", nullable = false, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "title", nullable = true, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "birth", nullable = true)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "street", nullable = true, length = -1)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "house_number", nullable = true)
    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Basic
    @Column(name = "post_code", nullable = true)
    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    @Basic
    @Column(name = "city", nullable = true, length = -1)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EcUserEntity that = (EcUserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(role, that.role) && Objects.equals(name, that.name)
                && Objects.equals(firstname, that.firstname) && Objects.equals(gender, that.gender)
                && Objects.equals(username, that.username) && Objects.equals(password, that.password)
                && Objects.equals(title, that.title) && Objects.equals(birth, that.birth)
                && Objects.equals(street, that.street) && Objects.equals(houseNumber, that.houseNumber)
                && Objects.equals(postCode, that.postCode) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, name, firstname, gender, username, password, title, birth, street, houseNumber,
                postCode, city);
    }

    @OneToMany(mappedBy = "ecUserByUserId")
    public Collection<EcSearchWordsEntity> getEcSearchWordsById() {
        return ecSearchWordsById;
    }

    public static EcUserEntity getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserAdapter) {
            return ((UserAdapter) principal).getUser();
        }
        return null;
    }

    public void setEcSearchWordsById(Collection<EcSearchWordsEntity> ecSearchWordsById) {
        this.ecSearchWordsById = ecSearchWordsById;
    }

    public EcUserEntity(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public EcUserEntity() {
    }
}
