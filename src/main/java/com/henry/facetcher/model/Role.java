package com.henry.facetcher.model;

import com.henry.facetcher.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role", schema = "public")
public class Role extends BaseEntity {

    @Id
    @SequenceGenerator(name = "role_id_sequence", sequenceName = "role_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_sequence")
    private Long id;

    @Column(name = "name", unique = true, updatable = false, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
