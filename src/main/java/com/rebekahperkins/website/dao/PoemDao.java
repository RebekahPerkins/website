package com.rebekahperkins.website.dao;

import com.rebekahperkins.website.domain.Poem;
import com.rebekahperkins.website.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoemDao extends CrudRepository<Poem, Long> {

}
