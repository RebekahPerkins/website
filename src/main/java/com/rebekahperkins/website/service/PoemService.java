package com.rebekahperkins.website.service;

import com.rebekahperkins.website.domain.Poem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PoemService {

  Poem addOrUpdate(Poem poem);

  Poem get(Long id);

  void delete(Poem poem);

  Page<Poem> findAll(Pageable pageable);
}
