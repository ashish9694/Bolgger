package com.blog.blogapp.service;

import com.blog.blogapp.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto updateCommentDtoById(long postId, long id, CommentDto commentDto);

    void deleteCommentById(long postId, long id, CommentDto commentDto);
}
