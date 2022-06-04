package com.cqjtu.mindassess.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cqjtu.mindassess.annotation.LogOperation;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.entity.Subject;
import com.cqjtu.mindassess.pojo.vo.subject.TextSubject;
import com.cqjtu.mindassess.pojo.vo.subject.TextSubjectVo;
import com.cqjtu.mindassess.service.ISubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhangning
 * @since 2022-05-12
 */
@Api(tags = {"测试题目控制器"})
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private final ISubjectService subjectService;

    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * 文本题目类型
     */
    private static final Integer TEXT_TYPE = 1;


    @ApiOperation("获取文本测试的题目")
    @LogOperation("获取文本测试的题目")
    @GetMapping("/text")
    public ApiResponse<?> textSubject() {
        List<Subject> subjectList = subjectService.list(new LambdaQueryWrapper<Subject>().eq(Subject::getType, TEXT_TYPE));
        if (ObjectUtils.isEmpty(subjectList)) {
            return ApiResponse.fail(200, "没有题目");
        }
        TextSubjectVo textSubjectVo = new TextSubjectVo();
        textSubjectVo.setType("text");
        List<TextSubject> textSubjects = subjectList.stream()
                .map(subject -> {
                    TextSubject ts = new TextSubject();
                    ts.setId(subject.getId());
                    ts.setSubject(subject.getSubject());
                    return ts;
                }).collect(Collectors.toList());
        textSubjectVo.setSubjects(textSubjects);
        return ApiResponse.success(textSubjectVo);
    }
}
