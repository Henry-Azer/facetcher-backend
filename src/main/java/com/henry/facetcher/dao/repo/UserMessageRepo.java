package com.henry.facetcher.dao.repo;

import com.henry.facetcher.dto.UserMessageDto;
import com.henry.facetcher.model.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Henry Azer
 * @since 15/02/2023
 */
@Repository
public interface UserMessageRepo extends JpaRepository<UserMessage, Long> {
    List<UserMessageDto> findAllByMarkedAsDeletedFalse();
}
