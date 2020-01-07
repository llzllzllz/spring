package com.example.demo1.controller;

import com.example.demo1.dto.TagDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagCache {
    public List<TagDto> get(){
        List<TagDto> tagDtos = new ArrayList<>();
        TagDto program = new TagDto();
        program.setCategoryName("并发语言");
        program.setTags(Arrays.asList("javascript","php","css","html"));
        tagDtos.add(program);

        TagDto framework = new TagDto();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring","flask","express","kob"));
        tagDtos.add(framework);

        TagDto server = new TagDto();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","ubuntu","koa","unix"));
        tagDtos.add(server);

        TagDto db = new TagDto();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","oracle","sqlserver","sqlite"));
        tagDtos.add(db);

        return tagDtos;
    }

}
