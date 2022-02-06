package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> findCategory(String uid) {
		return sqlSession.selectList("blog.findCategory", uid);
	}

	public List<PostVo> findAllPost(String uid) {
		return sqlSession.selectList("blog.findAllPost", uid);
	}

	public PostVo findRecentPost(String uid) {
		return sqlSession.selectOne("blog.findRecentPost", uid);
	}

	public int insertBlog(String userId) {
		return sqlSession.insert("blog.insertBlog", userId);
	}

	public int insertDefaultCategory(String userId) {
		return sqlSession.insert("blog.insertDefaultCategory", userId);
	}
}
