package layeredarchitecture.architecture.application;

import layeredarchitecture.architecture.domain.Store;
import layeredarchitecture.architecture.domain.StoreFruitList;
import layeredarchitecture.architecture.infrastructure.StoreFruitListRepository;
import layeredarchitecture.architecture.infrastructure.StoreRepository;
import layeredarchitecture.common.dto.StoreDto;
import layeredarchitecture.common.dto.StoreFruitListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<Store> storeEntities = storeRepository.findAll();

        if (!storeEntities.isEmpty()) {
            return storeEntities.stream()
                                .map(store -> StoreDto.builder()
                                                      .id(store.getId())
                                                      .name(store.getName())
                                                      .build())
                                .collect(Collectors.toList());
        }

        return null;
    }

    /**
     * 특정 상점의 판매 과일 목록 조회
     *
     * @param storeId 상점 ID
     * @return List<StoreFruitListDto>
     */
    @Transactional(readOnly = true)
    public List<StoreFruitListDto> getStoreFruitListByStoreId(Long storeId) {
        List<StoreFruitList> storeFruitList = storeFruitListRepository.findByStoreId(storeId);

        if (!storeFruitList.isEmpty()) {
            return storeFruitList.stream()
                                 .map(storeFruit -> StoreFruitListDto.builder()
                                                                     .id(storeFruit.getId())
                                                                     .storeId(storeFruit.getStore()
                                                                                        .getId())
                                                                     .fruitId(storeFruit.getFruit()
                                                                                        .getId())
                                                                     .fruitName(storeFruit.getFruit()
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
     * @return Long
     */
    @Transactional(readOnly = true)
    public Long getFruitQuantity(Long storeId, Long fruitId) {
        Optional<StoreFruitList> storeFruitListEntityOptional = storeFruitListRepository.findByStoreIdAndFruitId(storeId, fruitId);

        return storeFruitListEntityOptional.map(StoreFruitList::getQuantity)
                                           .orElse(0L);
    }

}
