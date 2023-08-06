package com.example.sampleDiaryJava;

import org.springframework.data.repository.CrudRepository;

//Spring Data JPAではこのインタフェースを実装したクラスを自動で作ってくれるため、
//プログラマはこのDiaryRepository.javaを使ってDiaryテーブルの簡単なCRUD操作ができるようになるらしい。
public interface DiaryRepository extends CrudRepository<Diary,Integer> {

}
