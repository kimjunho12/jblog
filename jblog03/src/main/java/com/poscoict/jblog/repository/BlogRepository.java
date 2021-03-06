package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public BlogVo findBlog(String blogId) {
		return sqlSession.selectOne("blog.findBlog", blogId);
	}

	public List<CategoryVo> findCategory(String blogId) {
		return sqlSession.selectList("blog.findCategory", blogId);
	}

	public List<PostVo> findAllPost(String blogId) {
		return sqlSession.selectList("blog.findAllPost", blogId);
	}

	public List<PostVo> findCategoryPostList(String blogId, Long categoryNo) {
		Map<String, Object> param = new HashMap<>();
		param.put("blogId", blogId);
		param.put("categoryNo", categoryNo);
		return sqlSession.selectList("blog.findCategoryPostList", param);
	}

	public PostVo findRecentPost(String blogId) {
		return sqlSession.selectOne("blog.findRecentPost", blogId);
	}

	public PostVo findRecentPost(String blogId, Long categoryNo) {
		Map<String, Object> param = new HashMap<>();
		param.put("blogId", blogId);
		param.put("categoryNo", categoryNo);
		return sqlSession.selectOne("blog.findCategoryRecentPost", param);
	}

	public PostVo findPost(String blogId, Long categoryNo, Long postNo) {
		Map<String, Object> param = new HashMap<>();
		param.put("blogId", blogId);
		param.put("categoryNo", categoryNo);
		param.put("postNo", postNo);
		return sqlSession.selectOne("blog.findPost", param);
	}

	public int insertBlog(String userId) {
		return sqlSession.insert("blog.insertBlog", userId);
	}

	public int insertDefaultCategory(String userId) {
		return sqlSession.insert("blog.insertDefaultCategory", userId);
	}

	public int insertCategory(CategoryVo categoryVo) {
		return sqlSession.insert("blog.insertCategory", categoryVo);
	}

	public int deleteCategory(Long categoryNo) {
		return sqlSession.delete("blog.deleteCategory", categoryNo);
	}

	public int insertPost(PostVo postVo) {
		return sqlSession.insert("blog.insertPost", postVo);
	}

	public int updateBlog(BlogVo blogVo) {
		return sqlSession.update("blog.updateBlog", blogVo);
	}

}
