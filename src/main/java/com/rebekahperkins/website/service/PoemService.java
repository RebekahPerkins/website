package com.rebekahperkins.website.service;

import com.rebekahperkins.website.domain.Poem;
import com.rebekahperkins.website.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PoemService {

  Poem addOrUpdate(Poem poem);

  Poem get(Long id);

  void delete(Poem poem);

  Page<Poem> findAll(Pageable pageable);

  void toggleFavorite(Long id, User user);

  boolean isFavorite(Long poemId, Long userId);

  Page<Poem> findBySubmittedBy(User user, Pageable pageable);

  Page<Poem> findFavorites(User user, Pageable pageable);
}
