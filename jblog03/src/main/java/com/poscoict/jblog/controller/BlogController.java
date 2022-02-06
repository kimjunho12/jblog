package com.poscoict.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.CategoryVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
	public String blogMain(@PathVariable("blogId") String blogId, Model model) {
		if (blogService.getBlog(blogId) == null) {
			return "redirect:/";
		}
		model.addAttribute("categoryList", blogService.getCategoryList(blogId));
		model.addAttribute("postList", blogService.getAllPostList(blogId));
		model.addAttribute("post", blogService.getRecentPost(blogId));
		model.addAttribute("blogId", blogId);
		model.addAttribute("blogVo", blogService.getBlog(blogId)); // 추후 인터셉터로 이동
		return "blog/blog-main";
	}

	@RequestMapping(value = { "/{blogId}/admin", "/{blogId}/admin/basic" }, method = RequestMethod.GET)
	public String blogAdmin(@PathVariable("blogId") String blogId, Model model) {
		model.addAttribute("blogVo", blogService.getBlog(blogId)); // 추후 인터셉터로 이동

		return "blog/blog-admin-basic";
	}

	@RequestMapping(value = "/{blogId}/admin/category", method = RequestMethod.GET)
	public String blogCategory(@PathVariable("blogId") String blogId, Model model) {
		model.addAttribute("blogVo", blogService.getBlog(blogId)); // 추후 인터셉터로 이동

		List<CategoryVo> categoryList = blogService.getCategoryList(blogId);
		model.addAttribute("categoryList", categoryList);

		Map<Long, Object> postCount = new HashMap<>();
		for (CategoryVo category : categoryList) {
			postCount.put(category.getNo(), blogService.getCategoryPostList(blogId, category.getNo()).size());
		}
		model.addAttribute("postCount", postCount);

		return "blog/blog-admin-category";
	}

	@RequestMapping(value = "/{blogId}/admin/category/add", method = RequestMethod.POST)
	public String blogCategoryAdd(@PathVariable("blogId") String blogId, CategoryVo categoryVo) {
		categoryVo.setBlogId(blogId);
		if (blogService.insertCategory(categoryVo)) {

		}

		return "redirect:/" + blogId + "/admin/category";
	}

	@RequestMapping(value = "/{blogId}/admin/write", method = RequestMethod.GET)
	public String blogWrite(@PathVariable("blogId") String blogId, Model model) {
		model.addAttribute("blogVo", blogService.getBlog(blogId)); // 추후 인터셉터로 이동
		model.addAttribute("categoryList", blogService.getCategoryList(blogId));

		return "blog/blog-admin-write";
	}

}
