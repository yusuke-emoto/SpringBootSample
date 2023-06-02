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
@Slf4j //Lombokのアノテーション。これをつけるとlogという変数、メソッドが使えるようになる
public class SignupController {

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /** ユーザー登録画面を表示 */
    @GetMapping("/signup") //@RequestMapping("/user")がクラスについているので/user/signupで来た場合、受け取る。
    public String getSignup(Model model, Locale locale, //Localeクラスはプログラムを実行している環境のローケルを識別し、オブジェクトとして取得するためのクラス。
            @ModelAttribute SignupForm form) {//@ModelAttributeをつけると返り値がModelに登録される
        // 性別を取得
        Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale); //userApplicationServiceのgetGenderMapメソッドを使い、返り値を変数 genderMapに代入。メソッドの引数にlocaleを渡しているので、実行環境に応じた言語等が指定される
        model.addAttribute("genderMap", genderMap); //キー名"genderMap"で値を変数genderMapでModelに登録。

        // ユーザー登録画面に遷移
        return "user/signup"; //userフォルダのsignup.htmlを返す。
    }

    /** ユーザー登録処理 */
    @PostMapping("/signup")
    public String postSignup(Model model, Locale locale,
            @ModelAttribute @Validated(GroupOrder.class) SignupForm form,//@Validatedをつけるとバリデーションが実行される
            BindingResult bindingResult)  { //バインドエラーやバリデーションエラーはBindingResultのメソッドで確認できる

        // 入力チェック結果
        if (bindingResult.hasErrors()) {//バインドエラーやバリデーションエラーが発生したとき
            // NG:ユーザー登録画面に戻ります
            return getSignup(model, locale, form);//getSignupメソッドにmodel, locale, formの変数を渡してメソッドを動かす
        }

        log.info(form.toString()); //infoレベルのログを出すメソッド。内容はformの内容をtoString()でString型に変換してから出力している。

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class); //modelMapperクラスのmapメソッドを使って、formに入力された内容をMUserクラスにコピーしている

        // ユーザー登録
        userService.signup(user); //formをコピーしたMUserクラスを変数userとしてsignup()メソッドに渡して起動。

        // ログイン画面にリダイレクト
        return "redirect:/login"; //PRGパターンに則り、リダイレクトしてからGetメソッドで/loginにリクエストしている。リダイレクトしない場合、遷移先のloginで更新を押すと登録処理のメソッド（今回の場合、postSignup）が動いてしまう為、2重登録されてしまう。
    }

    /** データベース関連の例外処理 */
    @ExceptionHandler(DataAccessException.class)//@ExceptionHandlerをつけると例外処理を実装できる（今回はDataAccessExceptionが起きたときの処理）
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        // 空文字をセット
        model.addAttribute("error", "");//エラーコードの概要をModelに登録。今回は空文字

        // メッセージをModelに登録
        model.addAttribute("message", "SignupControllerで例外が発生しました");//エラーメッセージをModelに登録。

        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);//HTTPのエラーコード（500）をModelに登録

        return "error";//error.htmlを返す。error.htmlでそれぞれModelに登録したものを出力する
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
