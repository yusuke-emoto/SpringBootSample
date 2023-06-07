package com.example.domain.user.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity //@Entityをつけるとデータベースのテーブルとクラスをマッピングしてくれる。クラス名と同じテーブルを作成してくれる
@Table(name="m_user")//クラス名とテーブル名が異なる場合、@Tableをつける。name属性にマッピングしたいテーブル名を設定する
public class MUser {
	@Id//主キーのアノテーションにつける
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Integer departmentId;
    private String role;
    @Transient//ORマッピングしたくないフィールドには@Transientをつける
    private Department department;
    @Transient
    private List<Salary> salaryList;
}
