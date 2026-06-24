import com.gaming.msstock.dto.*;
import com.gaming.msstock.service.interfaces.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockResponseDTO> createStock(
            @Valid @RequestBody StockRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(stockService.createStock(request));
    }

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> getAllStock() {

        return ResponseEntity.ok(stockService.getAllStock());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDTO> getStockById(@PathVariable Long id) {

        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @PostMapping
    public ResponseEntity<StockResponseDTO> saveStock(
            @Valid @RequestBody StockRequestDTO request) {

        return ResponseEntity.ok(stockService.saveStock(request));
    }


    @PutMapping("/{id}")
    public ResponseEntity<StockResponseDTO> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody StockRequestDTO request) {

        return ResponseEntity.ok(stockService.updateStock(id, request));
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteStock(@PathVariable Long id) {

        return ResponseEntity.ok(stockService.deleteStock(id));
    }

    
}