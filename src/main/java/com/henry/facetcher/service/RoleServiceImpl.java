package com.henry.facetcher.service;

import com.henry.facetcher.dao.RoleDao;
import com.henry.facetcher.dto.RoleDto;
import com.henry.facetcher.model.Role;
import com.henry.facetcher.transformer.RoleTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleTransformer roleTransformer;
    private final RoleDao roleDao;

    @Override
    public RoleDao getDao() {
        return roleDao;
    }

    @Override
    public RoleTransformer getTransformer() {
        return roleTransformer;
    }

    @Override
    public RoleDto findById(Long roleId) {
        log.info("RoleService: findById() called");
        Optional<Role> optionalRole = getDao().findById(roleId);
        if (optionalRole.isEmpty()) throw new EntityExistsException("Role not exists for id: " + roleId);
        return getTransformer().transformEntityToDto(optionalRole.get());
    }

    @Override
    public List<RoleDto> findAllRoles() {
        log.info("RoleService: findAllRoles() called");
        return getTransformer().transformEntityToDto(getDao().findAllByMarkedAsDeletedFalse());
    }

    @Override
    public RoleDto toggleRoleDeletionById(Long roleId) {
        log.info("RoleService: toggleRoleDeletionById() called");
        RoleDto roleDto = findById(roleId);
        roleDto.setMarkedAsDeleted(!roleDto.getMarkedAsDeleted());
        return update(roleDto, roleDto.getId());
    }
}
