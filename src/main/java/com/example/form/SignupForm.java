package com.example.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {

    @NotBlank(groups = ValidGroup1.class)//文字列がnull,空文字,空白スペースのみでないことをチェックする
    @Email(groups = ValidGroup2.class)//文字列がメールアドレス形式かどうかをチェックする
    private String userId;

    @NotBlank(groups = ValidGroup1.class)
    @Length(min = 4, max = 100, groups = ValidGroup2.class)//文字列の長さが指定した範囲内かどうかをチェック
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)//指定した正規表現に一致するかどうかチェック
    private String password;

    @NotBlank(groups = ValidGroup1.class)
    private String userName;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(groups = ValidGroup1.class)
    private Date birthday;

    @Min(value = 20, groups = ValidGroup2.class)//値以上かチェック
    @Max(value = 100, groups = ValidGroup2.class)//値以下であるかチェック
    private Integer age;

    @NotNull(groups = ValidGroup1.class)
    private Integer gender;
}