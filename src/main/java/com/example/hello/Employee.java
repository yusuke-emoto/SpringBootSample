package com.example.hello;

import lombok.Data;

@Data //主にgetterやsetterを作ってくれる便利なアノテーション。フィールドを変更したとしてもgetterやsetterを作り直す手間が省ける。
public class Employee {
    private String employeeId;
    private String employeeName;
    private int employeeAge;
}
