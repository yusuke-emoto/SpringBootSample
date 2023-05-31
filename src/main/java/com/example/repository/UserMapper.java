package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.user.model.MUser;

@Mapper //Mybatisでリポジトリー（記憶しとく為の箱みたいなもん）を作るためにはインターフェースに@Mapperをつける
public interface UserMapper {

    /** ユーザー登録 */
    public int insertOne(MUser user); //SQLでINSERT　INTOをして引数として受け取った値をテーブルに登録している

    /** ユーザー取得 */
    public List<MUser> findMany(MUser user);

    /** ユーザー取得(1件) */
    public MUser findOne(String userId);

    /** ユーザー更新(1件) */
    public void updateOne(@Param("userId") String userId, //複数引数を使用する場合、メソッドの引数に@Paramアノテーションをつける
            @Param("password") String password,
            @Param("userName") String userName);

    /** ユーザー削除(1件) */
    public int deleteOne(@Param("userId") String userId);

    /** ログインユーザー取得 */
    public MUser findLoginUser(String userId);
}
