package com.kzm.service;

import com.github.pagehelper.PageInfo;
import com.kzm.entity.Tag;

import java.util.List;


public interface TagService {

    int saveTag(Tag tag);

    int removeTag(Long id);

    int updateTag(Tag tag);

    PageInfo<Tag> listTag(int pageNum, int pageSize);

    List<Tag> listTag();

    Tag getTag(Long id);

    List<Tag> listTagTop(int size);

}
