package layeredarchitecture.architecture.presentation;

import layeredarchitecture.architecture.application.StoreService;
import layeredarchitecture.common.dto.StoreDto;
import layeredarchitecture.common.dto.StoreFruitListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAllStores() {
        List<StoreDto> stores = storeService.getStores();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{storeId}/fruit")
    public ResponseEntity<List<StoreFruitListDto>> getFruitOfStore(@PathVariable Long storeId) {
        List<StoreFruitListDto> storeFruitList = storeService.getStoreFruitListByStoreId(storeId);
        return ResponseEntity.ok(storeFruitList);
    }

    @GetMapping("/{storeId}/fruits/{fruitId}/quantity")
    public ResponseEntity<Long> getFruitQuantity(@PathVariable Long storeId, @PathVariable Long fruitId) {
        Long quantity = storeService.getFruitQuantity(storeId, fruitId);
        return ResponseEntity.ok(quantity);
    }

}