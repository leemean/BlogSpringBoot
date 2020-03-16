package com.liming.service.impl;

import com.liming.entity.Tag;
import com.liming.repository.TagRepository;
import com.liming.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagRepository.getOne(id);
    }

    @Override
    public Integer saveTag(Tag tag) {
        return tagRepository.save(tag).getId();
    }

    @Override
    public Integer updateTag(Tag tag) {
        Tag old = tagRepository.getOne(tag.getId());
        old.setTagName(tag.getTagName());
        return tagRepository.save(tag).getId();
    }

    @Override
    public void deleteTagById(Integer id) {
        tagRepository.deleteById(id);
    }
}
