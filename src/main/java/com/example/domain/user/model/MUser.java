package com.example.domain.user.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @ManyToOne(optional = true)//多対一のテーブル結合するときに@ManyToOneをつける。departmentが一の方。trueの場合nullを許可する
    @JoinColumn(insertable = false, updatable = false, name = "departmentId")//@JoinColumnで結合するカラムを指定する。name属性に結合先のカラム名を指定する
    private Department department;
    @Transient
    private List<Salary> salaryList;
}
