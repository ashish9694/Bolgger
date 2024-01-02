package com.blog.blogapp.service.Impl;

import com.blog.blogapp.entities.Comment;
import com.blog.blogapp.entities.Post;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.payload.CommentDto;
import com.blog.blogapp.repository.CommentRepository;
import com.blog.blogapp.repository.PostRepository;
import com.blog.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private PostRepository postRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = mapToComment(commentDto);
        comment.setPost(post);
        Comment newDto = commentRepo.save(comment);
        return mapToDto(newDto);
     }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<Comment> comments = commentRepo.findByPostId(postId);
        List<CommentDto> collect = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        int size = collect.size();

        System.out.println(collect);
        return collect;
    }

    @Override
    public CommentDto updateCommentDtoById(long postId, long id, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment newDto = commentRepo.save(comment);
        return mapToDto(newDto);
    }

    @Override
    public void deleteCommentById(long postId, long id, CommentDto commentDto) {
        postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", "id", id));
        commentRepo.delete(comment);
    }

    Comment mapToComment(CommentDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);
        /*Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());*/
        return comment;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
   /*CommentDto commentDto = new CommentDto();
   commentDto.setId(comment.getId());
   commentDto.setName(comment.getName());
   commentDto.setEmail(comment.getEmail());
   commentDto.setBody(comment.getBody());*/
   return commentDto;
    }
}
