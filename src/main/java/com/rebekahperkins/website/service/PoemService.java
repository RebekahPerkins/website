package com.rebekahperkins.website.service;

import com.rebekahperkins.website.domain.Poem;

public interface PoemService {

  Poem addOrUpdate(Poem poem);

  Poem get(Long id);

  void delete(Poem poem);
}
