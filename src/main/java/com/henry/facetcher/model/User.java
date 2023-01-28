package com.henry.facetcher.model;

import com.henry.facetcher.model.base.BaseEntity;
import com.henry.facetcher.enums.UserGender;
import com.henry.facetcher.enums.UserMartialStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user", schema = "public")
public class User extends BaseEntity {

    @Id
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, updatable = false, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "country")
    private String country;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private UserGender gender;

    @Column(name = "marital_status")
    private UserMartialStatus maritalStatus;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;
}
