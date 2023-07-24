package com.example.demo.post.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.post.model.Post;

@RestController
@RequestMapping("/post")
public class PostController {

	@GetMapping("/{postId}")
	public Post getPost(@PathVariable("postId") String postId) {

		Post postOne = new Post("1", "Post description");
		return postOne;

	}

}
