package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect //AOPクラスには@Aspectと@Componentアノテーションをつける
@Component
@Slf4j
public class LogAspect {

    /**
     * サービスの実行前にログ出力する.
     * 対象:[UserService]をクラス名に含んでいる.
     */
    @Before("execution(* *..*.*UserService.*(..))") //@Beforeはメソッドの発動タイミングを指定している。この場合はメソッドの発動前。executionでどの範囲かを指定している
    public void startLog(JoinPoint jp) {
        log.info("メソッド開始: " + jp.getSignature());//JoinPointクラスのgetSignatureメソッドを使ってメソッド名、引数の型、引数の数を表示させる
    }

    /**
     * サービスの実行後にログ出力する.
     * 対象:[UserService]をクラス名に含んでいる.
     */
    @After("execution(* *..*.*UserService.*(..))")//@After なので対象のメソッド処理が終わった後に処理が動く
    public void endLog(JoinPoint jp) {
        log.info("メソッド終了: " + jp.getSignature());
    }

    /** コントローラーの実行前後にログ出力する */
    //@Around("bean(*Controller)")
    //@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {

        // 開始ログ出力
        log.info("メソッド開始: " + jp.getSignature());

        try {
            // メソッド実行
            Object result = jp.proceed();

            // 終了ログ出力
            log.info("メソッド終了: " + jp.getSignature());

            // 実行結果を呼び出し元に返却
            return result;

        } catch (Exception e) {
            // エラーログ出力
            log.error("メソッド異常終了: " + jp.getSignature());

            // エラーの再スロー
            throw e;
        }
    }
}
