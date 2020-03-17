package com.liming.repository.wrapper;

import com.liming.vo.TagVo;

import java.util.List;

public interface TagWrapper {
    List<TagVo> findAllDetail();

    TagVo getTagDetail(Integer tagId);
}
