package com.example.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired //HelloRepositoryクラスのメソッドが使えるようになる
    private HelloRepository repository;

    /** 従業員を1人取得する */
    public Employee getEmployee(String id) {
        // 検索
        Map<String, Object> map = repository.findById(id); //RepositoryクラスのfindByIdメソッドを呼び出している。SQLで検索してきたデータをMap型の変数mapへ代入している。

        // Mapから値を取得
        String employeeId = (String) map.get("id"); //変数mapからgetメソッドを使って"id"キーに指定された値をString型に変換して変数employeeIdに代入。
        String name = (String) map.get("name"); //変数mapからgetメソッドを使って"name"キーに指定された値をString型に変換して変数nameに代入。
        int age = (Integer) map.get("age"); //変数mapからgetメソッドを使って"age"キーに指定された値をInteger型に変換して変数ageに代入。

        // Employeeクラスに値をセット
        Employee employee = new Employee(); //インスタンスを生成して代入
        employee.setEmployeeId(employeeId); //Employeeクラスのフィールド変数employeeIdにDBから持ってきた値をセットしている。27行28行も変数は別だが同じ処理。
        employee.setEmployeeName(name); 
        employee.setEmployeeAge(age);

        return employee; //新しく生成して値をセットした状態のemployeeを戻り値として返す。
    }
}
