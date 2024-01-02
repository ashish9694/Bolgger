package com.blog.blogapp.payload;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private long id;

//    @NotBlank(message = "title should not be blank")
    @NotNull
    @Size(min = 2,message = "Post Title should have at least 2 characters")
    private String title;
    @NotNull
    @Size(min =5,message = "Post Description should have at least 5 characters")
    private String description;

//    @NotEmpty(message = "NO Content Found")
    @NotNull
    private String content;

}
