package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.user.model.MUser;

public interface UserRepository extends JpaRepository<MUser, String>{
	//JPAはjavaの標準のO/RマッパーでSQLを記載しなくても自動で生成してくれる
	//ここではリポジトリー（保管場所）を生成している
	//JPAでリポジトリーを作成する場合、JpaRepositoryを継承する必要がある
	
	/** ログインユーザー検索 */
	@Query("select user" + " from MUser user" + " where userId = :userId")//@Queryをつけると任意のSQLを実行できる
	public MUser findLoginUser(@Param("userId") String userId);//@Paramを使うとメソッドの引数の名称を変えることができる
	
	/** ユーザー更新 */
	@Modifying//insertとupdateとdeleteを実行する場合、@Modifyingをつけないといけない
	@Query("update MUser" + " set" + " password = :password" + " , userName = :userName" + " where" + " userId = :userId")
	public Integer updateUser(@Param("userId") String userId, @Param("password") String password, @Param("userName") String userName);
}
