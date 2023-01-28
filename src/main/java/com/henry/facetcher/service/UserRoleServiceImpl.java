package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserRoleDao;
import com.henry.facetcher.dto.UserRoleDto;
import com.henry.facetcher.model.UserRole;
import com.henry.facetcher.transformer.UserRoleTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleTransformer userRoleTransformer;
    private final UserRoleDao userRoleDao;
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public UserRoleTransformer getTransformer() {
        return userRoleTransformer;
    }

    @Override
    public UserRoleDao getDao() {
        return userRoleDao;
    }

    @Override
    public UserRoleDto create(UserRoleDto dto) {
        log.info("UserRoleService: create() called");
        if (getDao().isUserRoleExistsByUserIdAndRoleId(dto.getUserId(), dto.getRoleId()))
            throw new EntityExistsException("User Role already exists");
        UserRole transformedDtoToEntity = getTransformer().transformDtoToEntity(dto);
        transformedDtoToEntity.setUser(userService.getTransformer().transformDtoToEntity(userService.findById(dto.getUserId())));
        transformedDtoToEntity.setRole(roleService.getTransformer().transformDtoToEntity(roleService.findById(dto.getRoleId())));
        return getTransformer().transformEntityToDto(getDao().create(transformedDtoToEntity));
    }

    @Override
    public UserRoleDto toggleUserRoleDeletionById(Long userRoleId) {
        log.info("UserRoleService: toggleUserRoleDeletionById() called");
        UserRoleDto userRoleDto = findById(userRoleId);
        userRoleDto.setMarkedAsDeleted(!userRoleDto.getMarkedAsDeleted());
        return update(userRoleDto, userRoleDto.getId());
    }
}
