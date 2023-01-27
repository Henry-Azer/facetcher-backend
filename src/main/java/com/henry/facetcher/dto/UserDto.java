package com.henry.facetcher.dto;

import com.henry.facetcher.dto.base.BaseDto;
import com.henry.facetcher.enums.UserGender;
import com.henry.facetcher.enums.UserMartialStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private UserGender gender;
    private UserMartialStatus maritalStatus;
    private String imageUrl;
}
