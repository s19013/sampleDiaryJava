package com.example.sampleDiaryJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //フロントにデータを渡す場合は@controller restapiをしたければ@RestController
@RequestMapping("diary")
public class DiaryController {
    //Springでは、@Autowiredを付けるとプログラマがリポジトリクラスやサービスクラスをnewしなくても、
    // オブジェクトを自動でセット（注入）してくれます。
    @Autowired
    DiaryRepository diaryRepository;

    //日記一覧情報の取得
    @GetMapping("summary")
    public String summary(Model model){
        //このIterableとは何だ?
        //laravelでいうmodelクラスを使ったsql操作みたいなことをしてると思う
        //つまり N + 1問題が起きるということだよね?
        Iterable<Diary> diarys = diaryRepository.findAll();

        //laravel でいう Viewにwithみたいなことしてる
        model.addAttribute("diarys",diarys);
        return "summary";
    }

    //指定されたidの日記を削除
    @PostMapping("delete")
    public String delete(@RequestParam Integer id){
        diaryRepository.deleteById(id);
        return "redirect:/diary/summary";
    }
}
