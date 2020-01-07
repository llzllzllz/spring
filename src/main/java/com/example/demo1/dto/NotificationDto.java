package com.example.demo1.dto;

import com.example.demo1.model.User;
import lombok.Data;

@Data
public class NotiicationDto {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private User notifier;
    private String outerTitle;
    private String type;
}
