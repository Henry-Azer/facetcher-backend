package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserTrialDao;
import com.henry.facetcher.dto.UserSubmissionDto;
import com.henry.facetcher.dto.UserTrialDto;
import com.henry.facetcher.manager.JWTAuthenticationManager;
import com.henry.facetcher.model.UserTrial;
import com.henry.facetcher.processor.FDLProcessor;
import com.henry.facetcher.transformer.UserTrialTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.henry.facetcher.constants.FacetcherConstants.TRIAL_MESSAGE;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Slf4j
@Service
public class UserTrialServiceImpl implements UserTrialService {
    private final ImageService imageService;
    private final UserService userService;
    private final UserTrialTransformer userTrialTransformer;
    private final UserTrialDao userTrialDao;
    private final FDLProcessor fdlProcessor;
    private final JWTAuthenticationManager authenticationManager;
    private final UserSubmissionService userSubmissionService;

    public UserTrialServiceImpl(UserTrialTransformer userTrialTransformer, UserTrialDao userTrialDao, FDLProcessor fdlProcessor,
                                JWTAuthenticationManager authenticationManager, @Lazy UserSubmissionService userSubmissionService,
                                ImageService imageService, UserService userService) {
        this.userTrialTransformer = userTrialTransformer;
        this.userTrialDao = userTrialDao;
        this.fdlProcessor = fdlProcessor;
        this.authenticationManager = authenticationManager;
        this.userSubmissionService = userSubmissionService;
        this.imageService = imageService;
        this.userService = userService;
    }

    @Override
    public UserTrialTransformer getTransformer() {
        return userTrialTransformer;
    }

    @Override
    public UserTrialDao getDao() {
        return userTrialDao;
    }

    @Override
    @Transactional
    public UserTrialDto findById(Long userTrialId) {
        log.info("UserTrialService: findById() called");
        Optional<UserTrial> optionalUserTrial = getDao().findById(userTrialId);
        if (optionalUserTrial.isEmpty()) throw new EntityExistsException("User trial not exists for id: " + userTrialId);
        return getTransformer().transformEntityToDto(optionalUserTrial.get());
    }

    @Override
    public UserTrialDto create(UserTrialDto dto) {
        log.info("UserTrialService: create() called");
        UserTrial transformedDtoToEntity = getTransformer().transformDtoToEntity(dto);
        transformedDtoToEntity.setUser(userService.getTransformer().transformDtoToEntity(userService.getCurrentUser()));
        transformedDtoToEntity.setInputImage(imageService.getTransformer().transformDtoToEntity(imageService.findById(dto.getInputImage().getId())));
        transformedDtoToEntity.setUserSubmission(userSubmissionService.getTransformer().transformDtoToEntity(userSubmissionService.findById(dto.getUserSubmissionId())));
        if (!dto.getExceptionOccurred()) transformedDtoToEntity.setOutputImage(imageService.getTransformer().transformDtoToEntity(imageService.findById(dto.getOutputImage().getId())));
        return getTransformer().transformEntityToDto(getDao().create(transformedDtoToEntity));
    }

    @Override
    public List<UserTrialDto> findAllUserTrials() {
        log.info("UserTrialService: findAllUserTrials() called");
        return getTransformer().transformEntityToDto(getDao().findAllUserTrials());
    }

    @Override
    public List<UserTrialDto> findAllFailedUserTrials() {
        log.info("UserTrialService: findAllFailedUserTrials() called");
        return getTransformer().transformEntityToDto(getDao().findAllFailedUserTrials());
    }

    @Override
    public List<UserTrialDto> findAllUserTrialsByUserSubmissionId(Long userSubmissionId) {
        log.info("UserTrialService: findAllUserTrialsByUserSubmissionId() called");
        return getTransformer().transformEntityToDto(getDao().findAllUserTrialsByUserSubmissionId(userSubmissionId));
    }

    @Override
    public UserTrialDto processUserTrial(UserTrialDto userTrialDto) {
        log.info("UserTrialService: processUserTrial() called");
        userTrialDto.setUserSubmission(userSubmissionService.findById(userTrialDto.getUserSubmissionId()));
        userTrialDto.setGender(userTrialDto.getUserSubmission().getGender());
        fdlProcessor.process(userTrialDto);
        userTrialDto.setTrialDate(LocalDateTime.now());
        userTrialDto.setTitle(userTrialDto.getUserSubmission().getTitle());
        userTrialDto.setDescription(userTrialDto.getUserSubmission().getDescription());
        userTrialDto.setTrailMessage(authenticationManager.getCurrentUserEmail() + TRIAL_MESSAGE + userTrialDto.getTrialDate());
        UserTrialDto dbUserTrialDto = create(userTrialDto);
        if (userTrialDto.getExceptionOccurred()) throw new EntityNotFoundException("Invalid FDL processor image input, Try again!");
        return dbUserTrialDto;
    }

    @Override
    public UserSubmissionDto submitUserTrialById(Long userTrialId) {
        log.info("UserTrialService: processUserTrial() called");
        return userSubmissionService.submitUserSubmissionByUserTrialId(userTrialId);
    }
}
