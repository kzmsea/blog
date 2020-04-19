package com.kzm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kzm.dao.BlogRepository;
import com.kzm.entity.Blog;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getBlog(id);
    }

    @Override
    public PageInfo<Blog> listBlog(int pageNum, int pageSize, Blog blog) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogRepository.listBlog(blog);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new PageInfo<Blog>(blogs);
    }

    @Override
    public int saveBlog(Blog blog) {
        return blogRepository.saveBlog(blog);
    }

    @Override
    public int removeBlog(Long id) {
        return blogRepository.removeBlog(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogRepository.updateBlog(blog);
    }

    @Override
    public List<Blog> listBlogByGxsj(int size) {
        return blogRepository.listBlogByGxsj(size);
    }

    @Override
    public PageInfo<Blog> listBlogBySearch(int pageNum, int pageSize, String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogRepository.listBlogBySearch(query);
        return new PageInfo<Blog>(blogs);
    }

    @Override
    public int updateViews(Long id) {
        return blogRepository.updateViews(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        Map<String, List<Blog>> map=new HashMap<>();
        List<String> years = blogRepository.listGroupYear();
        for(String year:years){
            map.put(year,blogRepository.listBlogByYear(year));
        }
        return map;
    }

    @Override
    public int blogCount() {
        return blogRepository.blogCount();
    }
}
