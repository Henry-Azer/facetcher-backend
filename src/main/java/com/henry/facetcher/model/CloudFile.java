package com.henry.facetcher.model;

import com.henry.facetcher.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cloud_file", schema = "public")
public class CloudFile extends BaseEntity {

    @Id
    @SequenceGenerator(name = "cloud_file_id_sequence", sequenceName = "cloud_file_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cloud_file_id_sequence")
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "bucket_name", nullable = false)
    private String bucketName;

    @Column(name = "cdn_code", nullable = false)
    private String cdnCode;

    @Column(name = "file_url", nullable = false)
    private String fileURL;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CloudFile user = (CloudFile) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
