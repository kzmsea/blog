package com.kzm.service;

import com.github.pagehelper.PageInfo;
import com.kzm.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    PageInfo<Blog> listBlog(int pageNum,int pageSize,Blog blog);

    int saveBlog(Blog blog);

    int removeBlog(Long id);

    int updateBlog(Blog blog);

    List<Blog> listBlogByGxsj(int size);

    PageInfo<Blog> listBlogBySearch(int pageNum,int pageSize,String query);

    int updateViews(Long id);

    Map<String,List<Blog>> archiveBlog();

    int blogCount();

}
