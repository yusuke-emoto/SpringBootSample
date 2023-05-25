package com.example.controller;

import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user") //@RequestMappingをつけると引数が接頭辞（プリフィックス）になる。
@Slf4j
public class SignupController {

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /** ユーザー登録画面を表示 */
    @GetMapping("/signup") //@RequestMapping("/user")がクラスについているので/user/signupで来た場合、受け取る。
    public String getSignup(Model model/*, Locale locale,
            @ModelAttribute SignupForm form*/) {
        // 性別を取得
        Map<String, Integer> genderMap = userApplicationService.getGenderMap(/*locale*/); //userApplicationServiceのgetGenderMapメソッドを使い、返り値を変数 genderMapに代入。
        model.addAttribute("genderMap", genderMap); //キー名"genderMap"で値を変数genderMapでModelに登録。

        // ユーザー登録画面に遷移
        return "user/signup"; //userフォルダのsignup.htmlを返す。
    }

    /** ユーザー登録処理 */
    @PostMapping("/signup")
    public String postSignup(/*Model model, Locale locale,
            @ModelAttribute @Validated(GroupOrder.class) SignupForm form,
            BindingResult bindingResult */) {

        // 入力チェック結果
       /* if (bindingResult.hasErrors()) {
            // NG:ユーザー登録画面に戻ります
            return getSignup(model, locale, form);
        }

        log.info(form.toString());

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);

        // ユーザー登録
        userService.signup(user); */

        // ログイン画面にリダイレクト
        return "redirect:/login"; //PRGパターンに則り、リダイレクトしてからGetメソッドで/loginにリクエストしている。リダイレクトしない場合、遷移先のloginで更新を押すと登録処理のメソッド（今回の場合、postSignup）が動いてしまう為、2重登録されてしまう。
    }

    /** データベース関連の例外処理 */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        // 空文字をセット
        model.addAttribute("error", "");

        // メッセージをModelに登録
        model.addAttribute("message", "SignupControllerで例外が発生しました");

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    /** その他の例外処理 */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        // 空文字をセット
        model.addAttribute("error", "");

        // メッセージをModelに登録
        model.addAttribute("message", "SignupControllerで例外が発生しました");

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }
}
