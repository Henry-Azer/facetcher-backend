package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserSubmissionDao;
import com.henry.facetcher.dto.UserSubmissionDto;
import com.henry.facetcher.dto.UserTrialDto;
import com.henry.facetcher.manager.JWTAuthenticationManager;
import com.henry.facetcher.model.UserSubmission;
import com.henry.facetcher.transformer.UserSubmissionTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.henry.facetcher.constants.FacetcherMessageConstants.SUBMISSION_MESSAGE;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserSubmissionServiceImpl implements UserSubmissionService {
    private final UserSubmissionTransformer userSubmissionTransformer;
    private final UserSubmissionDao userSubmissionDao;
    private final UserTrialService userTrialService;
    private final UserService userService;
    private final ImageService imageService;
    private final JWTAuthenticationManager authenticationManager;

    @Override
    public UserSubmissionTransformer getTransformer() {
        return userSubmissionTransformer;
    }

    @Override
    public UserSubmissionDao getDao() {
        return userSubmissionDao;
    }

    @Override
    public UserSubmissionDto create(UserSubmissionDto dto) {
        log.info("UserSubmissionService: create() called");
        UserSubmission transformedDtoToEntity = getTransformer().transformDtoToEntity(dto);
        transformedDtoToEntity.setUser(userService.getTransformer().transformDtoToEntity(userService.findById(dto.getUserId())));
        transformedDtoToEntity.setInputImage(imageService.getTransformer().transformDtoToEntity(imageService.findById(dto.getInputImageId())));
        transformedDtoToEntity.setOutputImage(imageService.getTransformer().transformDtoToEntity(imageService.findById(dto.getOutputImageId())));
        return getTransformer().transformEntityToDto(getDao().create(transformedDtoToEntity));
    }

    @Override
    public List<UserSubmissionDto> findAllUserSubmissionsByCurrentUser() {
        log.info("UserSubmissionService: findAllUserSubmissionsByCurrentUser() called");
        return getTransformer().transformEntityToDto(getDao().findAllUserSubmissionsByUserId(userService.getCurrentUser().getId()));
    }

    @Override
    public UserSubmissionDto submitUserSubmissionByUserTrialId(Long trialId) {
        log.info("UserSubmissionService: submitUserSubmissionByTrialId() called");
        UserTrialDto userTrialDto = userTrialService.findById(trialId);
        return create(mapUserTrialDtoToUserSubmissionDto(userTrialDto));
    }

    @Override
    public UserSubmissionDto toggleUserSubmissionDeletionById(Long userSubmissionId) {
        log.info("UserSubmissionService: toggleUserSubmissionDeletionById() called");
        UserSubmissionDto userSubmissionDto = findById(userSubmissionId);
        userSubmissionDto.setMarkedAsDeleted(!userSubmissionDto.getMarkedAsDeleted());
        return update(userSubmissionDto, userSubmissionDto.getId());
    }

    private UserSubmissionDto mapUserTrialDtoToUserSubmissionDto(UserTrialDto userTrialDto) {
        log.info("UserSubmissionService: mapUserTrialDtoToUserSubmissionDto() called");
        UserSubmissionDto userSubmissionDto = new UserSubmissionDto();
        userSubmissionDto.setUserId(userService.getCurrentUser().getId());
        userTrialDto.setOutputImageId(userTrialDto.getOutputImageId());
        userTrialDto.setInputImageId(userTrialDto.getInputImageId());
        userSubmissionDto.setSubmissionDate(LocalDateTime.now());
        userSubmissionDto.setTitle(userSubmissionDto.getTitle());
        userSubmissionDto.setDescription(userTrialDto.getDescription());
        userSubmissionDto.setTrialCount(userTrialService.getDao().getRepository().countAllByTitle(userTrialDto.getTitle()));
        userSubmissionDto.setSubmissionMessage(authenticationManager.getCurrentUserEmail() + SUBMISSION_MESSAGE + userSubmissionDto.getSubmissionDate());
        return userSubmissionDto;
    }
}
