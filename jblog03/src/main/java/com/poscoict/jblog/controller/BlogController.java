package com.poscoict.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogController {

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public String blogMain(@PathVariable("userId") String uid) {
		return "blog/blog-main";
	}

	@RequestMapping(value = { "/{userId}/admin", "/{userId}/admin/basic" }, method = RequestMethod.GET)
	public String blogAdmin(@PathVariable("userId") String uid) {
		return "blog/blog-admin-basic";
	}

	@RequestMapping(value = "/{userId}/admin/category", method = RequestMethod.GET)
	public String blogCategory(@PathVariable("userId") String uid) {
		return "blog/blog-admin-category";
	}

	@RequestMapping(value = "/{userId}/admin/write", method = RequestMethod.GET)
	public String blogWrite(@PathVariable("userId") String uid) {
		return "blog/blog-admin-write";
	}

}
