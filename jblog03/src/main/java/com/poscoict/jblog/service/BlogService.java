package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public BlogVo getBlog(String blogId) {
		return blogRepository.findBlog(blogId);
	}

	public List<CategoryVo> getCategoryList(String blogId) {
		return blogRepository.findCategory(blogId);
	}

	public List<PostVo> getAllPostList(String blogId) {
		return blogRepository.findAllPost(blogId);
	}

	public PostVo getRecentPost(String blogId) {
		return blogRepository.findRecentPost(blogId);
	}

	public boolean createBlog(String userId) {
		return 1 == blogRepository.insertBlog(userId);
	}

	public boolean createDefaultCategory(String userId) {
		return 1 == blogRepository.insertDefaultCategory(userId);
	}

	public boolean addCategory(CategoryVo categoryVo) {
		return 1 == blogRepository.insertCategory(categoryVo);
	}

	public List<PostVo> getCategoryPostList(String blogId, Long categoryNo) {
		return blogRepository.findCategoryPostList(blogId, categoryNo);
	}

	public boolean writePost(PostVo postVo) {
		return 1 == blogRepository.insertPost(postVo);
	}

}
