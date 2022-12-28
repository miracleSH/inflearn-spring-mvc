package hello.servlet.web.frontController.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4{

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String userName = paramMap.get("userName");
        String age = paramMap.get("age");
        Member member = new Member(userName, age);
        memberRepository.save(member);

        model.put("member", member);

        return "save-result";
    }
}
