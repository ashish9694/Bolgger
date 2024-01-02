package com.blog.blogapp.controller;

import com.blog.blogapp.payload.PostDto;
import com.blog.blogapp.payload.PostResponse;
import com.blog.blogapp.service.PostService;
import com.blog.blogapp.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {this.postService = postService;}//@Autowired
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
        }
       return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/
    @GetMapping
    public PostResponse getAllPosts(
        @RequestParam(value = "pageNo",defaultValue = AppConstants.Default_pageNo,required = false) int pageNo,
         @RequestParam(value = "pageSize",defaultValue = AppConstants.Default_pageSize,required = false) int pageSize,
        @RequestParam(value = "sortBy",defaultValue = AppConstants.Default_sortBy,required = false)String sortBy,
        @RequestParam(value = "sortDir",defaultValue = AppConstants.Default_sortDir,required = false)String sortDir

    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        return ResponseEntity.ok(postService.getPostById(id));

    }

    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("id") long id){
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>((dto),HttpStatus.OK);
    }
@DeleteMapping("{id}")
    public ResponseEntity<String> deletePOst(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }
}
