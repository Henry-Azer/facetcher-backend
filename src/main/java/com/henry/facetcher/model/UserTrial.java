package com.henry.facetcher.model;

import com.henry.facetcher.enums.Gender;
import com.henry.facetcher.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_trial", schema = "public")
public class UserTrial extends BaseEntity {

    @Id
    @SequenceGenerator(name = "user_trial_id_sequence", sequenceName = "user_trial_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_trial_id_sequence")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_submission_id", referencedColumnName = "id", nullable = false)
    private UserSubmission userSubmission;

    @Column(name = "user_submission_id", insertable = false, updatable = false)
    private Long userSubmissionId;

    @OneToOne
    @JoinColumn(name = "input_image_id", referencedColumnName = "id", nullable = false)
    private Image inputImage;

    @Column(name = "input_image_id", insertable = false, updatable = false)
    private Long inputImageId;

    @OneToOne
    @JoinColumn(name = "output_image_id", referencedColumnName = "id", nullable = false)
    private Image outputImage;

    @Column(name = "output_image_id", insertable = false, updatable = false)
    private Long outputImageId;

    @Column(name = "image_properties")
    private String imageProperties;

    @Column(name = "exception_occurred")
    private Boolean exceptionOccurred;

    @Column(name = "exception_message")
    private String exceptionMessage;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "trial_date")
    private LocalDateTime trialDate;

    @Column(name = "trial_message")
    private String trailMessage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserTrial userTrial = (UserTrial) o;
        return id != null && Objects.equals(id, userTrial.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
