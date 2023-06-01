package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserListForm;

@Controller
@RequestMapping("/user")
public class UserListController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /** ユーザー一覧画面を表示 */
    @GetMapping("/list")
    public String getUserList(@ModelAttribute UserListForm form, Model model) {

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);

        // ユーザー一覧取得
        List<MUser> userList = userService.getUsers(user); //UserServiceクラスのgetUsersメソッドの返り値をuserListに代入している。

        // Modelに登録
        model.addAttribute("userList", userList); //取得してきた値userListをキー名"userList"と一緒にModelに登録

        // ユーザー一覧画面を表示
        return "user/list";
    }

    /** ユーザー検索処理 */
    @PostMapping("/list")
    public String postUserList(@ModelAttribute UserListForm form, Model model) {

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class); //UserListFormクラスの変数formの値をmodelMapperのmapメソッドを使ってコピーして変数userへ代入

        // ユーザー検索
        List<MUser> userList = userService.getUsers(user); //userServiceクラスのgetUsersに引数userを渡して条件に当てはまるものを検索して変数userListへ代入

        // Modelに登録
        model.addAttribute("userList", userList);//検索された値が入ったuserListをModelに登録

        // ユーザー一覧画面を表示
        return "user/list";
    }
}
