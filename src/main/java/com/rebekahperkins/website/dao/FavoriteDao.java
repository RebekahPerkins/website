package com.rebekahperkins.website.dao;

import com.rebekahperkins.website.domain.Favorite;
import com.rebekahperkins.website.domain.Poem;
import com.rebekahperkins.website.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteDao extends CrudRepository<Favorite, Long> {

  Favorite getByPoemIdAndUserId(Long poemId, Long userId);

  List<Favorite> findByUser(User user);

  List<Favorite> findByPoem(Poem poem);
}
