package com.rebekahperkins.website.service;

import com.rebekahperkins.website.dao.FavoriteDao;
import com.rebekahperkins.website.dao.PoemDao;
import com.rebekahperkins.website.dao.UserDao;
import com.rebekahperkins.website.domain.Favorite;
import com.rebekahperkins.website.domain.Poem;
import com.rebekahperkins.website.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoemServiceImpl implements PoemService {

  @Autowired
  private PoemDao poemDao;

  @Autowired
  private FavoriteDao favoriteDao;

  @Autowired
  private UserDao userDao;

  @Override
  public Poem addOrUpdate(Poem poem) { return poemDao.save(poem);
  }

  @Override
  public Poem get(Long id) {
    return poemDao.findOne(id);
  }

  @Override
  public void delete(Poem poem) {
    List<Favorite> favorites = favoriteDao.findByPoem(poem);
    for (Favorite favorite : favorites){
      favoriteDao.delete(favorite);
    }
    poemDao.delete(poem);
  }

  @Override
  public Page<Poem> findAll(Pageable pageable) {
    Page<Poem> poems = poemDao.findAll(pageable);
    return poems;
  }

  @Override
  public void toggleFavorite(Long poemId, User user) {
    Favorite favorite = favoriteDao.getByPoemIdAndUserId(poemId, user.getId());
    if (null == favorite){
      favorite = new Favorite(poemDao.findOne(poemId), user);
      favoriteDao.save(favorite);
    } else {
      favoriteDao.delete(favorite);
    }
  }

  @Override
  public boolean isFavorite(Long poemId, Long userId) {
    return null == favoriteDao.getByPoemIdAndUserId(poemId, userId);
  }

  @Override
  public Page<Poem> findBySubmittedBy(User user, Pageable pageable) {
    Page<Poem> poems = poemDao.findBySubmittedBy(user, pageable);
    return poems;
  }

  @Override
  public Page<Poem> findFavorites(User user, Pageable pageable) {
    List<Favorite> favorites = favoriteDao.findByUser(user);

    List<Poem> poems = favorites.stream()
        .map(favorite -> favorite.getPoem())
        .collect(Collectors.toList());

    Page<Poem> page = new PageImpl<Poem>(poems, pageable, poems.size());

    return page;
  }
}
