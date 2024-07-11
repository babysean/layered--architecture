package layeredarchitecture.presentation;

import java.util.List;
import layeredarchitecture.application.StoreService;
import layeredarchitecture.dto.StoreDto;
import layeredarchitecture.dto.StoreFruitListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<?> getAllStores() {
        List<StoreDto> stores = storeService.getStores();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{storeId}/fruit")
    public ResponseEntity<?> getFruitOfStore(@PathVariable Long storeId) {
        List<StoreFruitListDto> storeFruitList = storeService.getStoreFruitListByStoreId(storeId);
        return ResponseEntity.ok(storeFruitList);
    }

    @GetMapping("/{storeId}/fruit/{fruitId}/quantity")
    public ResponseEntity<Long> getFruitQuantity(@PathVariable Long storeId, @PathVariable Long fruitId) {
        Long quantity = storeService.getFruitQuantity(storeId, fruitId);
        return ResponseEntity.ok(quantity);
    }

}
