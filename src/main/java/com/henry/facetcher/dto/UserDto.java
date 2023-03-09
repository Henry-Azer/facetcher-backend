package com.henry.facetcher.dto;

import com.henry.facetcher.dto.base.BaseDto;
import com.henry.facetcher.enums.Gender;
import com.henry.facetcher.enums.UserMartialStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Date birthdate;
    private String country;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private UserMartialStatus maritalStatus;
    private String profilePictureUrl;
    private List<UserRoleDto> userRoles;
}
