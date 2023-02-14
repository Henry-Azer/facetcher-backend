package com.henry.facetcher.model;

import com.henry.facetcher.enums.UserLogStatus;
import com.henry.facetcher.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_log", schema = "public")
public class UserLog extends BaseEntity {

    @Id
    @SequenceGenerator(name = "user_log_id_sequence", sequenceName = "user_log_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_log_id_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "log_count")
    private Long logCount;

    @Column(name = "log_date")
    private LocalDateTime logDate;

    @Column(name = "log_status")
    @Enumerated(EnumType.STRING)
    private UserLogStatus logStatus;

    @Column(name = "log_message")
    private String logMessage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserLog userLog = (UserLog) o;
        return id != null && Objects.equals(id, userLog.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
