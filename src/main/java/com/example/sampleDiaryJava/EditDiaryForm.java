package com.example.sampleDiaryJava;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditDiaryForm {
    // 編集する日記id
    @NotNull
    private int id;

    // 日記の本文
    @NotNull
    @Size(min=3,max=150,message = "文字数は3~150文字")
    private String updateddiary;

}
