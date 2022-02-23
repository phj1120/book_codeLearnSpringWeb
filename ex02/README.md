#구글링
##2022.02.23
### 리다이렉트 시 파라미터 전달이 안된 이유

```java

[GET] board/modify 의 요청에 들어온 파라미터를
BoardController 의 read 메서드에서 모델 담아 modify.jsp 실행

수정 버튼 클릭
현재 form 에 있는 값 활용해 [POST] board/modify 에 요청 전송
BoardController 의 modify 메서드에서 파라미터를 받고
이를 활용해 필요한 값을 RedirectAttributes 에 담고
[GET] /board/list 요청 전송

수정 버튼 클릭 시 바로 /board/list 나오니까 
파라미터랑 모델이 어떻게 처리 되는건지 헷갈렸음

Redirect 를 할 경우 RedirectAttributes 를 쓴다 해서 
addFlashAttribute 밖에 없는 줄 알았음
그래서 계속 addFlashAttribute 로 jsp 에 값을 전달해주려해도
URL 파라미터로 전송이 안 됐음 

addAttribute
URL 파라미터로 전송됨
리프레시 해도 데이터가 유지 됨

addFlashAttribute
URL 파라미터로 전송 안됨
리프레시하면 데이터 소멸 (일회성)
2개 이상 쓸 경우 데이터 소멸 -> 맵을 이용해 한 번에 값을 전달해야함

-> form 으로 전송 된 (URL 에 표시 X) 경우에 일반적이라면 
컨트롤러에서 알아서 매핑해주고 오류 발생 안하는데 
왜 리다이렉트는 오류가 발생해서 사람 헷갈리게 만들어

아 리다이렉트는 get 이라 http body 가 없음
그래서 form 으로 전송이 안되고 쿼리 파라미터로만 받아야했던거임

addAttribute -> get 방식
addFlashAttribute -> post 방식 (세션 이용하는 것으로 알고 있음)

https://aorica.tistory.com/145
https://roqkffhwk.tistory.com/121
[https://bactoria.tistory.com/entry/스프링-addAttribute-addFlashAttribute-차이점-RedirectAttributes-rttr-리다이렉트](https://bactoria.tistory.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81-addAttribute-addFlashAttribute-%EC%B0%A8%EC%9D%B4%EC%A0%90-RedirectAttributes-rttr-%EB%A6%AC%EB%8B%A4%EC%9D%B4%EB%A0%89%ED%8A%B8)
```

### rttr 에 객체가 아니라 string 전달하는 이유

```java
왜
rttr.addAttribute("cri", cri);
이렇게 한 번에 안주고

나눠서주지? 
rttr.addAttribute("pageNum", cri.getPageNum());
rttr.addAttribute("amount", cri.getAmount());

한번에 줘보니까 에러 발생...

구글링 결과
https://wwwnghks.tistory.com/152
아래의 방식으로 전송 성공
근데 지금 처럼 파라미터가 2개 밖에 없는 상황에서 굳이 이렇게까지 할 필요 있을까 싶었음

@GetMapping("/list") // http://localhost/board/list
public String list(HttpServletRequest request, Criteria criteria, Model m) {
	log.info("[list]");
	
	Map<String, ?> flashMap =RequestContextUtils.getInputFlashMap(request);
    if(flashMap!=null) {
    	Criteria cri =(Criteria)flashMap.get("cri");
        m.addAttribute("list", service.getList(cri));
        m.addAttribute("pageMaker", new PageDTO(cri, 114));
    }else {
    	log.info("[list] : " +service.getList(criteria));
    	m.addAttribute("list", service.getList(criteria));
	    m.addAttribute("pageMaker", new PageDTO(criteria, 114));
    }
	return "/board/list";
}

@PostMapping("modify")
public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
	log.info("[modify]");

	rttr.addFlashAttribute("cri", cri);
	log.info("[modify] cri : "+cri);
	log.info("[modify] cri.getAmount() : "+cri.getAmount());
	return "redirect:/board/list";
}
```