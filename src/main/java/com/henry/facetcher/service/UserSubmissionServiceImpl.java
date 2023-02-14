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

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.henry.facetcher.constants.FacetcherConstants.SUBMISSION_MESSAGE;

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
    @Transactional
    public UserSubmissionDto findById(Long userSubmissionId) {
        log.info("UserSubmissionService: findById() called");
        Optional<UserSubmission> optionalUserSubmission = getDao().findById(userSubmissionId);
        if (optionalUserSubmission.isEmpty()) throw new EntityExistsException("User submission not exists for id: " + userSubmissionId);
        return getTransformer().transformEntityToDto(optionalUserSubmission.get());
    }

    @Override
    public Long findUserSubmissionsCountByCurrentUser() {
        log.info("UserSubmissionService: findUserSubmissionsCountByCurrentUser() called");
        return getDao().countUserSubmissionsByUserId(userService.getCurrentUser().getId());
    }

    @Override
    public List<UserSubmissionDto> findAllUserSubmissions() {
        log.info("UserSubmissionService: findAllUserSubmissions() called");
        return getTransformer().transformEntityToDto(getDao().findAllUserSubmissions());
    }

    @Override
    public List<UserSubmissionDto> findAllUserSubmissionsByUserId(Long userId) {
        log.info("UserSubmissionService: findAllUserSubmissionsByCurrentUser() called");
        return getTransformer().transformEntityToDto(getDao().findAllUserSubmissionsByUserId(userId));
    }

    @Override
    public List<UserSubmissionDto> findAllUserSubmissionsByCurrentUser() {
        log.info("UserSubmissionService: findAllUserSubmissionsByCurrentUser() called");
        return getTransformer().transformEntityToDto(getDao().findAllUserSubmissionsByUserId(userService.getCurrentUser().getId()));
    }

    @Override
    public UserSubmissionDto createOrUpdate(UserSubmissionDto userSubmissionDto) {
        log.info("UserSubmissionService: create() called");
        if (userSubmissionDto.getId() != null) {
            UserSubmission userSubmission = getDao().findById(userSubmissionDto.getId()).get();
            if (findById(userSubmissionDto.getId()).getSubmitted()) throw new EntityExistsException("User submission is already submitted before");
            getTransformer().updateEntity(userSubmissionDto, userSubmission);
            if (userSubmissionDto.getInputImageId() != null) userSubmission.setInputImage(imageService.getTransformer().transformDtoToEntity(imageService.findById(userSubmissionDto.getInputImageId())));
            if (userSubmissionDto.getOutputImageId() != null) userSubmission.setOutputImage(imageService.getTransformer().transformDtoToEntity(imageService.findById(userSubmissionDto.getOutputImageId())));
            UserSubmission updatedEntity = getDao().update(userSubmission);
            return getTransformer().transformEntityToDto(updatedEntity);
        }
        UserSubmission transformedDtoToEntity = getTransformer().transformDtoToEntity(userSubmissionDto);
        transformedDtoToEntity.setUser(userService.getTransformer().transformDtoToEntity(userService.getCurrentUser()));
        transformedDtoToEntity.setSubmitted(false);
        return getTransformer().transformEntityToDto(getDao().create(transformedDtoToEntity));
    }

    @Override
    @Transactional
    public UserSubmissionDto submitUserSubmissionByUserTrialId(Long trialId) {
        log.info("UserSubmissionService: submitUserSubmissionByUserTrialId() called");
        UserTrialDto userTrialDto = userTrialService.findById(trialId);
        UserSubmissionDto userSubmissionDto = findById(userTrialDto.getUserSubmissionId());
        if (userSubmissionDto.getSubmitted()) throw new EntityExistsException("User submission is already submitted before");
        return createOrUpdate(mapUserTrialDtoToSubmittedUserSubmissionDto(userTrialDto, userSubmissionDto));
    }

    @Override
    public UserSubmissionDto toggleUserSubmissionDeletionById(Long userSubmissionId) {
        log.info("UserSubmissionService: toggleUserSubmissionDeletionById() called");
        UserSubmissionDto userSubmissionDto = findById(userSubmissionId);
        userSubmissionDto.setMarkedAsDeleted(!userSubmissionDto.getMarkedAsDeleted());
        return update(userSubmissionDto, userSubmissionDto.getId());
    }

    private UserSubmissionDto mapUserTrialDtoToSubmittedUserSubmissionDto(UserTrialDto userTrialDto, UserSubmissionDto userSubmissionDto) {
        log.info("UserSubmissionService: mapUserTrialDtoToSubmittedUserSubmissionDto() called");
        userSubmissionDto.setOutputImageId(userTrialDto.getOutputImageId());
        userSubmissionDto.setInputImageId(userTrialDto.getInputImageId());
        userSubmissionDto.setGender(userTrialDto.getGender());
        userSubmissionDto.setTitle(userTrialDto.getTitle());
        userSubmissionDto.setSubmitted(true);
        userSubmissionDto.setSubmissionDate(LocalDateTime.now());
        userSubmissionDto.setDescription(userTrialDto.getDescription());
        userSubmissionDto.setTrialCount(userTrialService.getDao().getRepository().countAllByUserSubmissionId(userSubmissionDto.getId()));
        userSubmissionDto.setSubmissionMessage(authenticationManager.getCurrentUserEmail() + SUBMISSION_MESSAGE + userSubmissionDto.getSubmissionDate());
        return userSubmissionDto;
    }
}
