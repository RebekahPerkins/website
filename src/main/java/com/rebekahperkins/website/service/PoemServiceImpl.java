package com.rebekahperkins.website.service;

import com.rebekahperkins.website.dao.PoemDao;
import com.rebekahperkins.website.domain.Poem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoemServiceImpl implements PoemService {

  @Autowired
  private PoemDao poemDao;

  @Override
  public Poem addOrUpdate(Poem poem) { return poemDao.save(poem);
  }

  @Override
  public Poem get(Long id) {
    return poemDao.findOne(id);
  }

  @Override
  public void delete(Poem poem) {
    poemDao.delete(poem);
  }
}
