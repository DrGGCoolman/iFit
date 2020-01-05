package de.hsba.ifit.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;

import de.hsba.ifit.course.Course;
import de.hsba.ifit.slot.Slot;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements Comparable<User> {

    public static String USER_ROLE = "USER";
    public static String ADMIN_ROLE = "ADMIN";

    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserAdapter) {
            return ((UserAdapter) principal).getUser();
        }
        return null;
    }

    @Id
    @GeneratedValue
    @Setter
    private Integer id;

    @Basic(optional = false)
    @Column(unique = true)
    private String name;

    @Basic(optional = false)
    private String password;

    private String role;

    @Getter
    @Setter
    public String firstname;

    @Getter
    @Setter
    public String lastname;

    public User(String firstname, String lastname, String name, String password, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Getter
    @Setter
    @ManyToMany
    private List<Slot> slots;
    
    @Getter
    @Setter
    @ManyToMany
    private List<Course> courses;

}
