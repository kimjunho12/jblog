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

	public List<PostVo> getPostList(String blogId, Long categoryNo) {
		List<PostVo> postList = null;
		if (categoryNo == 0) {
			postList = blogRepository.findAllPost(blogId);
		} else {
			postList = blogRepository.findCategoryPostList(blogId, categoryNo);
		}
		return postList;
	}

	public PostVo getPost(String blogId, Long categoryNo, Long postNo) {
		PostVo postVo = null;
		if (categoryNo == 0) {
			postVo = blogRepository.findRecentPost(blogId);
		} else if (postNo == 0) {
			postVo = blogRepository.findRecentPost(blogId, categoryNo);
		} else {
			postVo = blogRepository.findPost(blogId, categoryNo, postNo);
		}

		return postVo;
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

	public boolean deleteCategory(Long categoryNo) {
		return 1 == blogRepository.deleteCategory(categoryNo);
	}

	public boolean writePost(PostVo postVo) {
		return 1 == blogRepository.insertPost(postVo);
	}

}
