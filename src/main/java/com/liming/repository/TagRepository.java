package com.liming.repository;

import com.liming.entity.Tag;
import com.liming.repository.wrapper.TagWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Integer>, TagWrapper {

}
