package com.rebekahperkins.website.service;

import com.rebekahperkins.website.domain.Poem;
import com.rebekahperkins.website.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PoemService {

  Poem addOrUpdate(Poem poem);

  Poem get(Long id);

  void delete(Poem poem);

  Page<Poem> findAll(Pageable pageable);

  void toggleFavorite(Long id, UserEntity user);

  boolean isFavorite(Long poemId, Long userId);

  Page<Poem> findBySubmittedBy(UserEntity user, Pageable pageable);

  Page<Poem> findFavorites(UserEntity user, Pageable pageable);
}
