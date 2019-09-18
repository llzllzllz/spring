package com.example.demo1.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String login;
    private Long id;
    private String node_id;
    private String avatar_url;
}
