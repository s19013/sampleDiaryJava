package com.example.sampleDiaryJava;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//laravelで言うマイグレーションとかモデルみたいのを作ってると思う

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity //エンティティクラスであることを示すため
/**
 * @param string bodytext
 *  @param localDateTime createDateTime
 */
public class Diary {
    public Diary(String bodytext,LocalDateTime createDateTime){
        this.bodytext = bodytext;
        this.createDatetime = createDateTime;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 連番機能
    private  Integer id;
    @Column(nullable = false)
    private  String bodytext;
    //投稿日時
    @Column(name = "create_datetime",nullable = false)
    private LocalDateTime createDatetime;
}
