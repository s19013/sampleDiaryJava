package com.example.sampleDiaryJava;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewDiaryForm {
    //新規投稿された日記本文
    @NotNull
    @Size(min=3,max=150,message = "文字数は3~150文字")
    // 検査する値
    private  String newdiary;

    // なくてもspringBootの機能で動くけど intellijがエラー吐くので
    public String getNewdiary() { return this.newdiary; }
}
