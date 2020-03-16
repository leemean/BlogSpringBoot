package com.liming.controller;

import com.liming.common.constant.Base;
import com.liming.common.constant.ResultCode;
import com.liming.common.result.Result;
import com.liming.entity.Tag;
import com.liming.service.TagService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/create")
    //@RequiresRoles(Base.ROLE_ADMIN)
    public Result saveTag(@Validated @RequestBody Tag tag){
        Integer id = tagService.saveTag(tag);
        Result r = Result.success();
        r.simple().put("tagId",id);
        return r;
    }

    @PostMapping("/update")
    @RequiresRoles(Base.ROLE_ADMIN)
    public Result updateTag(@Validated @RequestBody Tag tag){
        Result r = new Result();

        if(null == tag.getId()){
            r.setResultCode(ResultCode.USER_NOT_EXIST);
            return r;
        }

        Integer id = tagService.updateTag(tag);
        r.setResultCode(ResultCode.SUCCESS);
        r.simple().put("tagId",id);
        return r;
    }

    @PostMapping("/delete/{id}")
    @RequiresRoles(Base.ROLE_ADMIN)
    public Result deleteTagById(@PathVariable("id") Integer id){
        Result r = new Result();
        if(null == id){
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }
        tagService.deleteTagById(id);
        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }
}
