package com.kzm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kzm.dao.TagRepository;
import com.kzm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;

    @Override
    public int saveTag(Tag tag) {
        return tagRepository.saveTag(tag);
    }

    @Override
    public int removeTag(Long id) {
        return tagRepository.removeTag(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagRepository.updateTag(tag);
    }

    @Override
    public PageInfo<Tag> listTag(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Tag> tags = tagRepository.listTag();
        return new PageInfo<Tag>(tags);
    }

    @Override
    public List<Tag> listTag(){
        return tagRepository.listTag();
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.getTag(id);
    }

    @Override
    public List<Tag> listTagTop(int size) {
        List<Tag> tags = tagRepository.listTag();
        Tag tag=null;
        /*for(int i=0;i<tags.size();i++){
            for(int j=0;j<tags.size()-i-1;j++){
                if(tags.get(j).getBlogs().size()<tags.get(j+1).getBlogs().size()){
                    tag=tags.get(j);
                    tags.add(j,tags.get(j+1));
                    tags.add(j+1,tag);
                }
            }
        }*/
        Collections.sort(tags,Collections.reverseOrder(new Comparator<Tag>() {
            @Override
            public int compare(Tag o1, Tag o2) {
                if(o1.getBlogs().size()>o2.getBlogs().size()){
                    return 1;
                }
                return -1;
            }
        }));
        List<Tag> tags2 = tags.subList(size, tags.size());
        tags.removeAll(tags2);
        /*for(int i=size;i<tags.size();i++){
            tags.remove(size);
        }*/
        return tags;
    }
}
