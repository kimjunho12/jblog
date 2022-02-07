package com.poscoict.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.security.Auth;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Controller
@RequestMapping("/{blogId:(?!assets|images).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping(value = { "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String blogMain(
			@PathVariable("blogId") String blogId,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2,
			Model model) {
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}

		model.addAttribute("blogVo", blogService.getBlog(blogId)); // 추후 인터셉터로 이동
		model.addAttribute("categoryList", blogService.getCategoryList(blogId)); // 얘도 이동해야할듯

		model.addAttribute("postList", blogService.getPostList(blogId, categoryNo));
		model.addAttribute("post", blogService.getPost(blogId, categoryNo, postNo));

		model.addAttribute("blogId", blogId);

		return "blog/blog-main";
	}

	@Auth
	@RequestMapping(value = { "/admin", "/admin/basic" }, method = RequestMethod.GET)
	public String blogAdmin(@PathVariable("blogId") String blogId, Model model) {
		model.addAttribute("blogVo", blogService.getBlog(blogId)); // 추후 인터셉터로 이동

		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping(value = "/admin/basic/update", method = RequestMethod.POST)
	public String blogAdmin(
			@PathVariable("blogId") String blogId,
			@RequestParam(value = "logo-file") MultipartFile multipartFile,
			BlogVo blogVo) {
		blogVo.setUserId(blogId);
		String url = fileUploadService.restore(multipartFile);
		if (url != null) {
			blogVo.setLogo(url);
		}
		if (blogService.updateBlog(blogVo)) {
			
		}
		return "redirect:/" + blogId + "/admin/basic";
	}

	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String blogCategory(@PathVariable("blogId") String blogId, Model model) {
		model.addAttribute("blogVo", blogService.getBlog(blogId)); // 추후 인터셉터로 이동

		List<CategoryVo> categoryList = blogService.getCategoryList(blogId);
		model.addAttribute("categoryList", categoryList);

		Map<Long, Object> postCount = new HashMap<>();
		for (CategoryVo category : categoryList) {
			postCount.put(category.getNo(), blogService.getPostList(blogId, category.getNo()).size());
		}
		model.addAttribute("postCount", postCount);

		return "blog/blog-admin-category";
	}

	@Auth
	@RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
	public String blogCategoryAdd(@PathVariable("blogId") String blogId, CategoryVo categoryVo) {
		categoryVo.setBlogId(blogId);
		if (blogService.addCategory(categoryVo)) {

		}

		return "redirect:/" + blogId + "/admin/category";
	}
	
	@Auth
	@RequestMapping(value = "/admin/category/delete/{categoryNo}", method = RequestMethod.GET)
	public String blogCategoryDelete(@PathVariable("blogId") String blogId, @PathVariable("categoryNo") Long categoryNo) {
		if (blogService.deleteCategory(categoryNo)) {
			
		}
		
		return "redirect:/" + blogId + "/admin/category";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String blogWrite(@PathVariable("blogId") String blogId, Model model) {
		model.addAttribute("blogVo", blogService.getBlog(blogId)); // 추후 인터셉터로 이동
		model.addAttribute("categoryList", blogService.getCategoryList(blogId));

		return "blog/blog-admin-write";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String blogWrite(@PathVariable("blogId") String blogId, PostVo postVo) {
		if (blogService.writePost(postVo)) {

		}
		return "redirect:/" + blogId;

	}

}
