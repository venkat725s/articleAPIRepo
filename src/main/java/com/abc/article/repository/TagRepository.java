package com.abc.article.repository;

import com.abc.article.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
   List<Tag> findAllByName(String tagName);
}
