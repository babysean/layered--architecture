package layeredarchitecture.service;

import layeredarchitecture.repository.ConsumerRepository;
import layeredarchitecture.repository.model.Consumer;
import layeredarchitecture.ui.response.ConsumerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public ConsumerResponse getMemberInfo(Long id) {
        Consumer consumer = consumerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumer not found"));

        return ConsumerResponse.builder()
                .id(consumer.getId())
                .name(consumer.getName())
                .build();
    }

}
