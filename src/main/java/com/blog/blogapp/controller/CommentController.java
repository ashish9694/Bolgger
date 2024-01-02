package com.blog.blogapp.controller;

import com.blog.blogapp.payload.CommentDto;
import com.blog.blogapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllCommentsByPostId(@PathVariable("postId")Long postId){
        List<CommentDto> dto = commentService.getCommentByPostId(postId);
        return dto;
    }
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateCommentDtoById(@PathVariable("postId")long postId,@PathVariable("id") long id,@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateCommentDtoById(postId, id, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("postId")long postId,@PathVariable("id") long id,@RequestBody CommentDto commentDto){
        commentService.deleteCommentById(postId,id,commentDto);
        return new ResponseEntity<>("deleted SuccessFully",HttpStatus.OK);
    }
}
