package com.cqjtu.mindassess.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toolgood.words.StringSearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 敏感词配置
 */
@Configuration
public class SensitiveWordConfig {
    @Bean
    public StringSearch getSensitiveWord() {
        List<String> list = new ArrayList<String>();
        String path = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("static/keywords.txt")).getPath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringSearch words = new StringSearch();
        words.SetKeywords(list);
        return words;
    }
}
