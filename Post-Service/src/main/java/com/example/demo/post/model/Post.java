package com.example.demo.post.model;

public class Post {

	private String postId;
	private String description;

	public String getPostId() {
		return postId;
	}
	

	public Post(String postId, String description) {
		super();
		this.postId = postId;
		this.description = description;
	}


	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", description=" + description + "]";
	}

}
