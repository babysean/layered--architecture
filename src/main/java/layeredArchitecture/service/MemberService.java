package layeredArchitecture.service;

import layeredArchitecture.repository.MemberRepository;
import layeredArchitecture.repository.model.Member;
import layeredArchitecture.repository.model.MemberResponse;
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
