package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @Autowired //依存性の注入（説明は別のコメントでしてあるため省く）
    private HelloService service;

    @GetMapping("/hello") //Getリクエストで送られてくる/helloを受け取って、メソッドが動く。ここではhello.htmlを返している
    public String getHello() {
        // hello.htmlに画面遷移
        return "hello";
    }

    @PostMapping("/hello") //Postリクエストで送られてくる/helloを受け取ることでメソッドが動く。
    public String postRequest(@RequestParam("text1") String str, Model model) { //@RequestParamnをつけることで画面から入力された情報を受け取れる。この場合、"text1"というnameで入力さてた内容をstrに入れている。
        // 画面から受け取った文字列をModelに登録
        model.addAttribute("sample", str); //ModelクラスのaddAttributeでModelにキー名と値を登録する。

        // response.htmlに画面遷移
        return "hello/response"; //helloフォルダのresponse.htmlを呼び出す。
    }

    @PostMapping("/hello/db") //"/hello/db"に対してPOSTメソッドで送られてくるリクエストに対応する
    public String postDbRequest(@RequestParam("text2") String id, Model model) { //HTML側でname="text2"に指定した所で入力された値がidに入る

        // 1件検索
        Employee employee = service.getEmployee(id); //サービスクラスのgetEmployeeメソッドが動く。その戻り値を変数employeeへ代入

        // 検索結果をModelに登録
        model.addAttribute("employee", employee); //employeeをキー名とともにModelに登録

        // db.htmlに画面遷移
        return "hello/db"; //helloフォルダのdb.htmlを返す。
    }
}
