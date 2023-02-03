package com.henry.facetcher.model;

import com.henry.facetcher.enums.Gender;
import com.henry.facetcher.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "input_image_id", referencedColumnName = "id", nullable = false)
    private Image inputImage;

    @OneToOne
    @JoinColumn(name = "output_image_id", referencedColumnName = "id", nullable = false)
    private Image outputImage;

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
}
