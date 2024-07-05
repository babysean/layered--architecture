package layeredarchitecture.ui;

import layeredarchitecture.repository.model.MemberResponse;
import layeredarchitecture.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<?> getUser(@RequestParam("id") Long id) {
        System.out.println("id = " + id);
        MemberResponse memberResponse = memberService.getMemberInfo(id);
        return ResponseEntity.ok(memberResponse);
    }
}
