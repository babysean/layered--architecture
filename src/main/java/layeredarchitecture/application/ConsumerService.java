package layeredarchitecture.application;


import java.util.Optional;
import layeredarchitecture.dto.ConsumerDto;
import layeredarchitecture.infrastructure.ConsumerRepository;
import layeredarchitecture.infrastructure.entity.ConsumerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    @Transactional(readOnly = true)
    public ConsumerDto getConsumerInfo(Long id) {
        Optional<ConsumerEntity> consumerOptional = consumerRepository.findById(id);

        if (consumerOptional.isPresent()) {
            ConsumerEntity consumerEntity = consumerOptional.get();

            return ConsumerDto.builder()
                    .id(consumerEntity.getId())
                    .name(consumerEntity.getName())
                    .build();
        }

        return null;
    }

}
