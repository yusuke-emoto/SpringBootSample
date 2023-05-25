package com.example.application.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

    @Autowired
    private MessageSource messageSource;

    /** 性別のMapを生成する */
    public Map<String, Integer> getGenderMap(/*Locale locale*/) {
        Map<String, Integer> genderMap = new LinkedHashMap<>(); //LinkedHashMapインスタンスを生成して変数genderMapに代入。
        //String male = messageSource.getMessage("male", null, locale);
       // String female = messageSource.getMessage("female", null, locale);
        genderMap.put("男性", 1);  //Map型の変数genderMapにputメソッドを使って、キー名"男性",値を１で登録している。
        genderMap.put("女性", 2); //Map型の変数genderMapにputメソッドを使って、キー名"女性",値を2で登録している。
        return genderMap; // 22,23行の値を登録した変数genderMapを戻り値として返す。
    }
}
