package com.example.demo1.controller;

import com.example.demo1.dto.QuestionDto;
import com.example.demo1.mapper.QuestionMapper;
import com.example.demo1.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id,
                           Model model){

        QuestionDto questionDto = questionService.getById(id);
        model.addAttribute("question",questionDto);
        return "question";
    }
}
