package com.liming.service;

import com.liming.entity.Tag;
import com.liming.vo.TagVo;

import java.util.List;

public interface TagService {
    List<Tag> findAll();

    Tag getTagById(Integer id);

    Integer saveTag(Tag tag);

    Integer updateTag(Tag tag);

    void deleteTagById(Integer id);

    List<TagVo> findAllDetail();

    TagVo getTagDetail(Integer tagId);

}
