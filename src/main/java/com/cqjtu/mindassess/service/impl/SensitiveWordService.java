package com.cqjtu.mindassess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toolgood.words.StringSearch;

/**
 * 敏感词
 * @author zhangzhencheng
 */
@Service
public class SensitiveWordService {

    @Autowired
    StringSearch stringSearch;

    /**
     * 判断是否含有敏感词
     * @param txt
     * @return
     */
    public Boolean judgeSensitivityWord(String txt) {
        return stringSearch.ContainsAny(txt);
    }

    /**
     * 敏感词过滤用*替代
     * @param txt 内容
     * 敏感词替代字符 '*'
     * @return
     */
    public String filterSensitivityWord(String txt){
        return stringSearch.Replace(txt, '*');
    }
}
