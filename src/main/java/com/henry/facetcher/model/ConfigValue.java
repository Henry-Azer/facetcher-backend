package com.henry.facetcher.model;

import com.henry.facetcher.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "config_value", schema = "public")
public class ConfigValue extends BaseEntity {

    @Id
    @SequenceGenerator(name = "config_value_id_sequence", sequenceName = "config_value_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_value_id_sequence")
    private Long id;

    @Column(name = "config_key", nullable = false, unique = true, updatable = false)
    private String key;

    @Column(name = "config_value", nullable = false)
    private String value;

    @Column(name = "config_type", nullable = false)
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ConfigValue user = (ConfigValue) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
