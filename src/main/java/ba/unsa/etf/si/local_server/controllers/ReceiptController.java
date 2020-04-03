package ba.unsa.etf.si.local_server.controllers;

import ba.unsa.etf.si.local_server.models.transactions.Receipt;
import ba.unsa.etf.si.local_server.requests.ReceiptRequest;
import ba.unsa.etf.si.local_server.responses.ReceiptResponse;
import ba.unsa.etf.si.local_server.security.CurrentUser;
import ba.unsa.etf.si.local_server.security.UserPrincipal;
import ba.unsa.etf.si.local_server.services.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
public class ReceiptController {
    private final ReceiptService receiptService;

    @PostMapping("/api/receipts")
    public ResponseEntity<?> saveReceipt(@Valid @RequestBody ReceiptRequest receiptRequest) {
        String responseMessage = receiptService.checkRequest(receiptRequest);
        return ResponseEntity.ok(new ReceiptResponse(responseMessage));
    }

    @GetMapping("/api/receipts")
    public ResponseEntity<?> getReceipts(@RequestParam(name = "cash_register_id", required = false) Long cashRegisterId) {
        List<Receipt> receipts = receiptService.getReceipts(cashRegisterId);
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/api/receipts/{id}")
    public ResponseEntity<?> getReceipt(@PathVariable String id) {
        Receipt receipt = receiptService.getReceipt(id);
        return ResponseEntity.ok(receipt);
    }

    @DeleteMapping("/api/receipts/{id}")
    public ResponseEntity<?> reverseReceipt(@PathVariable String id){
        String responseMessage = receiptService.reverseReceipt(id);
        return ResponseEntity.ok(new ReceiptResponse(responseMessage));
    }

}
