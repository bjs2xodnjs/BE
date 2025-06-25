package com.green.fristserver;

import com.green.fristserver.model.MemoGetOnRes;
import com.green.fristserver.model.MemoGetRes;
import com.green.fristserver.model.MemoPostReq;
import com.green.fristserver.model.MemoPutReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 빈<Bean> 등록 스프링 컨테이너 객체 생성을 대리로 맡긴다. 요청/ 응답 담당자
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    // DI 받는 방법 3가지
    //1. 필드 주입
    //2. setter 주입 (메소드 주입)
    //3. 생성자 주입
//    public MemoController(MemoService memoService) {
//        this.memoService = memoService;
//    }

    @GetMapping("/memo")
    public List<MemoGetRes> getMemo() {
        return memoService.selMemoList();
    }
    @GetMapping("/memo/{id}")
    public MemoGetOnRes getMemo(@PathVariable int id) {
        System.out.println("getMemo: " + id);
        return memoService.selMemo(id);
    }
    @PostMapping("/memo")
    public String postMemo(@RequestBody MemoPostReq req) { //@RequestBody 는 json 을 받을거야
        System.out.println("postMemo: " + req);
        int result = memoService.insMemo(req);
        return result == 1 ?"성공" : "실패";
    }
    @PutMapping("/memo")
    public String putMemo( @RequestBody MemoPutReq req) {
        System.out.println("putMemo: " + req);
        int result = memoService.updMemo(req);
        return result == 1 ? "성공" : "실패";
    }
    @DeleteMapping("/memo")
    public String deleteMemo(@RequestParam int id) { // 인트로 안 받고 객체로 받는다.
        System.out.println("deleteMemo: " + id);
        int result = memoService.delMemo(id);
        return result == 1 ? "성공" : "실패";
    }

}
