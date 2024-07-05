package layeredarchitecture.service;

import layeredarchitecture.repository.MemberRepository;
import layeredarchitecture.repository.model.Member;
import layeredarchitecture.ui.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse getMemberInfo(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }

}
