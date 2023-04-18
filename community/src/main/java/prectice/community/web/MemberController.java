package prectice.community.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import prectice.community.domain.Member;
import prectice.community.repository.member.MemberRepository;
import prectice.community.repository.member.MemberRepositoryImpl;
import prectice.community.service.member.MemberService;
import prectice.community.service.member.MemberServiceImpl;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberServiceImpl memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member")Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        member.setJoinDate(LocalDateTime.now().toString());
        memberService.save(member);


        return "redirect:/";
    }





}
