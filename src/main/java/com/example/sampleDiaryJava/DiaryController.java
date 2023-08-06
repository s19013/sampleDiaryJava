package com.example.sampleDiaryJava;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller //フロントにデータを渡す場合は@controller restapiをしたければ@RestController
@RequestMapping("diary")
public class DiaryController {
    //Springでは、@Autowiredを付けるとプログラマがリポジトリクラスやサービスクラスをnewしなくても、
    // オブジェクトを自動でセット（注入）してくれます。
    @Autowired
    DiaryRepository diaryRepository;

    //日記一覧情報の取得
    // フォームのエラーを表示するためにNewDiaryFormを追加
    @GetMapping("summary")
    public String summary(Model model ,NewDiaryForm newDiaryForm){
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

    //新規登録
    @PostMapping("add")
    public String add(
            Model model,
            @Valid NewDiaryForm newDiaryForm,
            BindingResult bindingResult
    ){
        // バリデーションはすでに実行されているもよう｡おそらく @Validの時点で実行ずみ?
        // エラーがあるなら新規登録をせずに一覧表示メソッドを呼び出す
        if (bindingResult.hasErrors()) {
            return summary(model,newDiaryForm);
        }

        //chronoUnit.Secondsで秒以下を切り捨て
        // オブジェクトを生成?
        Diary diary = new Diary(
                newDiaryForm.getNewdiary(),
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
        );

        // 保存
        diaryRepository.save(diary);
        return "redirect:/diary/summary";
    }

    // 編集画面を表示
    @GetMapping("edit")
    public String edit(
            Model model,
            EditDiaryForm editDiaryForm
    ){
        Diary diary = diaryRepository.findById(editDiaryForm.getId()).get();
        model.addAttribute("editdiary",diary);
        return "edit";
    }

    // 更新
    @PostMapping("update")
    public String update(
            Model model,
            @Valid EditDiaryForm editDiaryForm,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){ return edit(model,editDiaryForm); }

        // 一番最初にIdを入れているがコンストラクタでは2つしか引数がなかったはず?
        // でも動いてるということはspringBootがなにかしているのか?
        Diary diary = new Diary(
                editDiaryForm.getId(),
                editDiaryForm.getUpdateddiary(),
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
        );
        diaryRepository.save(diary);

        return "redirect:/diary/summary";
    }
}
