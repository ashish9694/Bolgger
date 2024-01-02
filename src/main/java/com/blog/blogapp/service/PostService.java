package com.blog.blogapp.service;

import com.blog.blogapp.payload.PostDto;
import com.blog.blogapp.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto,long id);

    void deletePost(long id);
}
