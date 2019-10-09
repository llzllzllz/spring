package com.example.demo1.controller;

import com.example.demo1.dto.CommentCreateDto;
import com.example.demo1.dto.CommentDto;
import com.example.demo1.dto.ResultDto;
import com.example.demo1.enums.CommentTypeEnum;
import com.example.demo1.exception.CustomizeErrorCode;
import com.example.demo1.model.Comment;
import com.example.demo1.model.User;
import com.example.demo1.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDto commentCreateDto,
                       HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDto.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }
        if (commentCreateDto == null || StringUtils.isBlank(commentCreateDto.getContent())){
            return ResultDto.errorOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
        }


        Comment comment = new Comment();
        comment.setParentId(commentCreateDto.getParentId());
        comment.setContent(commentCreateDto.getContent());
        comment.setType(commentCreateDto.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentor(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
//        Map<Object,Object> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("message","成功");
//        return objectObjectHashMap;
        return ResultDto.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDto<List> comments(@PathVariable(name = "id") Integer id){
        List<CommentDto> commentDtos = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDto.okOf(commentDtos);
    }
}
