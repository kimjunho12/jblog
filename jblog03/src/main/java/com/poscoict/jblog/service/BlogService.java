package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public List<CategoryVo> getCategoryList(String uid) {
		return blogRepository.findCategory(uid);
	}

	public List<PostVo> getAllPostList(String uid) {
		return blogRepository.findAllPost(uid);
	}

	public PostVo getRecentPost(String uid) {
		return blogRepository.findRecentPost(uid);
	}

	public boolean createBlog(String userId) {
		return 1 == blogRepository.insertBlog(userId);
	}

	public boolean createDefaultCategory(String userId) {
		return 1 == blogRepository.insertDefaultCategory(userId);
	}
	
}
