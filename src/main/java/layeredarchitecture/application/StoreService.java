package layeredarchitecture.application;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import layeredarchitecture.dto.StoreDto;
import layeredarchitecture.dto.StoreFruitListDto;
import layeredarchitecture.infrastructure.StoreFruitListRepository;
import layeredarchitecture.infrastructure.StoreRepository;
import layeredarchitecture.infrastructure.entity.StoreEntity;
import layeredarchitecture.infrastructure.entity.StoreFruitListEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    private final StoreFruitListRepository storeFruitListRepository;

    /**
     * 전체 상점 조회
     *
     * @return List<StoreDto>
     */
    @Transactional(readOnly = true)
    public List<StoreDto> getStores() {
        List<StoreEntity> storeEntities = storeRepository.findAll();

        if (!storeEntities.isEmpty()) {
            return storeEntities.stream()
                    .map(storeEntity -> StoreDto.builder()
                            .id(storeEntity.getId())
                            .name(storeEntity.getName())
                            .build())
                    .collect(Collectors.toList());
        }

        return null;
    }

    /**
     * 특정 상점의 판매 과일 목록 조회
     *
     * @param storeId 상점 ID
     *
     * @return List<StoreDto>
     */
    @Transactional(readOnly = true)
    public List<StoreFruitListDto> getStoreFruitListByStoreId(Long storeId) {
        List<StoreFruitListEntity> storeFruitListEntity = storeFruitListRepository.findByStoreId(storeId);

        if (!storeFruitListEntity.isEmpty()) {
            return storeFruitListEntity.stream()
                    .map(storeFruit -> StoreFruitListDto.builder()
                            .id(storeFruit.getId())
                            .storeId(storeFruit.getStoreEntity()
                                    .getId())
                            .fruitId(storeFruit.getFruitEntity()
                                    .getId())
                            .fruitName(storeFruit.getFruitEntity()
                                    .getName())
                            .price(storeFruit.getPrice())
                            .quantity(storeFruit.getQuantity())
                            .build())
                    .collect(Collectors.toList());
        }

        return null;

    }

    /**
     * 과일의 판매 개수 반환
     *
     * @param storeId 상점 ID
     * @param fruitId 과일 ID
     *
     * @return Long
     */
    public Long getFruitQuantity(Long storeId, Long fruitId) {
        Optional<StoreFruitListEntity> storeFruitListEntityOptional = storeFruitListRepository.findByStoreIdAndFruitId(storeId, fruitId);

        return storeFruitListEntityOptional.map(StoreFruitListEntity::getQuantity)
                .orElse(0L);
    }
}
