package com.example.demo1.service;

import com.example.demo1.dto.PageDto;
import com.example.demo1.dto.QuestionDto;
import com.example.demo1.mapper.QuestionMapper;
import com.example.demo1.mapper.UserMapper;
import com.example.demo1.model.Question;
import com.example.demo1.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PageDto list(Integer page, Integer size) {

        PageDto pageDto = new PageDto();

        Integer totalPage;
        Integer totalCount = questionMapper.count();

        if(totalCount % size == 0){
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount/size + 1;
        }

        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }

        pageDto.setPageParam(totalPage, page);

        Integer offset = size*(page - 1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for(Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        pageDto.setQuestions(questionDtoList);

        return pageDto;
    }

    public PageDto list(Integer userId, Integer page, Integer size) {
        PageDto pageDto = new PageDto();

        Integer totalPage;
        Integer totalCount = questionMapper.countByUserID(userId);

        if(totalCount % size == 0){
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount/size + 1;
        }

        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }

        pageDto.setPageParam(totalPage, page);

        Integer offset = size*(page - 1);
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for(Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        pageDto.setQuestions(questionDtoList);

        return pageDto;
    }

    public QuestionDto getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user = userMapper.findById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }  else{
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }
}
