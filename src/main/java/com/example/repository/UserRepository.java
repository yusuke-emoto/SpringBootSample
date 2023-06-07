package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.user.model.MUser;

public interface UserRepository extends JpaRepository<MUser, String>{
	//JPAはjavaの標準のO/RマッパーでSQLを記載しなくても自動で生成してくれる
	//ここではリポジトリー（保管場所）を生成している
	//JPAでリポジトリーを作成する場合、JpaRepositoryを継承する必要がある
}
