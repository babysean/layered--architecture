package layeredarchitecture.ui;

import layeredarchitecture.service.ConsumerService;
import layeredarchitecture.ui.response.ConsumerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ConsumerController {

    private final ConsumerService consumerService;

    @GetMapping("/consumer")
    public ResponseEntity<?> getUser(@RequestParam("id") Long id) {
        System.out.println("id = " + id);
        ConsumerResponse memberResponse = consumerService.getMemberInfo(id);
        return ResponseEntity.ok(memberResponse);
    }
}
