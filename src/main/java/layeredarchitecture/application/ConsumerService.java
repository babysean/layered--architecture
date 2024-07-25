package layeredarchitecture.application;

import layeredarchitecture.dto.ConsumerDto;
import layeredarchitecture.infrastructure.ConsumerRepository;
import layeredarchitecture.presentation.exception.CustomException;
import layeredarchitecture.presentation.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    /**
     * 소비자 정보 조회
     *
     * @param id 소비자 ID
     * @return ConsumerDto
     */
    @Transactional(readOnly = true)
    public ConsumerDto getConsumerInfo(Long id) {
        return consumerRepository.findById(id)
                                 .map(consumerEntity -> ConsumerDto.builder()
                                                                   .id(consumerEntity.getId())
                                                                   .name(consumerEntity.getName())
                                                                   .build())
                                 .orElseThrow(() -> new CustomException(ErrorCode.CONSUMER_NOT_FOUND));
    }

}
