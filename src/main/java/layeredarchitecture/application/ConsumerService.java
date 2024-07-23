package layeredarchitecture.application;

import layeredarchitecture.dto.ConsumerDto;
import layeredarchitecture.infrastructure.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    @Transactional(readOnly = true)
    public ConsumerDto getConsumerInfo(Long id) {
        return consumerRepository.findById(id)
                .map(consumerEntity -> ConsumerDto.builder()
                        .id(consumerEntity.getId())
                        .name(consumerEntity.getName())
                        .build())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "데이터가 없습니다"));
    }

}
