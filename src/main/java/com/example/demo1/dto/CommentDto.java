package com.example.demo1.dto;

import com.example.demo1.model.User;
import lombok.Data;

@Data
public class CommentDto {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentor;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private Integer commentCount;
    private User user;
}
