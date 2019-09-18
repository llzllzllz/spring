package com.example.demo1.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDto {
    private List<QuestionDto> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPageParam(Integer totalPage, Integer page) {

        this.totalPage= totalPage;

        this.page = page;

        pages.add(page);
        for(int i = 1; i <= 3;i++){
            if(page - i > 0){
                pages.add(0,page - i);
            }
            if(page + i <= totalPage){
                pages.add(page + i);
            }
        }
        //展示上一页
        if(page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }

        //展示下一页
        if(page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }

        //包含第一页
        if(pages.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }

        //包含最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }
    }
}
