package com.example.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository //DB操作をするクラスにアノテーション
public class HelloRepository {

    @Autowired //DIを使うためのアノテーション。Springが起動してBean(今回の場合Repository）が付いたクラスを探して、@Autowiredがついているところ（jdbcTemplate)にインスタンスを代入（new）している。実際にnewしている場所はDIコンテナー。
    private JdbcTemplate jdbcTemplate; //依存性の注入をすることでこのクラス（HelloRepository）内でJdbcTemplateクラスのメソッドが使えるようになる。

    public Map<String, Object> findById(String id) {

        // SELECT文
        String query = "SELECT *" //SELECT どの項目（列）からデータを取得するか。　*はすべての意味
                + " FROM employee" //どの表（テーブル）から検索するか指定している
                + " WHERE id=?"; //条件を指定している　この場合、findByIdのidに入っている値がidと一致しているものが条件

        // 検索実行
        Map<String, Object> employee = jdbcTemplate.queryForMap(query, id); //queryForMapメソッドを使って、1レコード分のデータを変数employeeに代入している。

        return employee; //変数idの1レコード分のデータを戻り値として返却
    }
}