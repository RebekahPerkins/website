package com.rebekahperkins.website.dao;

import com.rebekahperkins.website.domain.Poem;
import com.rebekahperkins.website.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoemDao extends PagingAndSortingRepository<Poem, Long> {
  Page<Poem> findAllByOrderByDateUploadedDesc(Pageable pageable);

  Page<Poem> findBySubmittedByOrderByDateUploadedDesc(UserEntity user, Pageable pageable);
}
