package com.rebekahperkins.website.dao;

import com.rebekahperkins.website.domain.Favorite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteDao extends CrudRepository<Favorite, Long> {

  Favorite getByPoemIdAndUserId(Long poemId, Long userId);
}
