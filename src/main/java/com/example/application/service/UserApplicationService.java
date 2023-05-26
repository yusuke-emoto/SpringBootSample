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
    public Map<String, Integer> getGenderMap(Locale locale) {
        Map<String, Integer> genderMap = new LinkedHashMap<>(); //LinkedHashMapインスタンスを生成して変数genderMapに代入。
        String male = messageSource.getMessage("male", null, locale); //MessageSourceのgetMessageメソッドを用いて、messages.propertiesから値を取得して変数maleに代入している
        String female = messageSource.getMessage("female", null, locale); //getMessageメソッドの補足説明、第1引数にmessages.propertiesのキー、第2引数に埋め込みパラメーター（動的に設定する値がない場合はnull）、第3引数で国（地域を指定する）
        genderMap.put(male, 1);  //Map型の変数genderMapにputメソッドを使って、キー名"male",値を１で登録している。
        genderMap.put(female, 2); //Map型の変数genderMapにputメソッドを使って、キー名"female",値を2で登録している。
        return genderMap; // 22,23行の値を登録した変数genderMapを戻り値として返す。
    }
}
