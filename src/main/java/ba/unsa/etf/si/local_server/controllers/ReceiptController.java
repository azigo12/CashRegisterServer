package ba.unsa.etf.si.local_server.controllers;

import ba.unsa.etf.si.local_server.models.Product;
import ba.unsa.etf.si.local_server.models.transactions.Receipt;
import ba.unsa.etf.si.local_server.models.transactions.ReceiptStatus;
import ba.unsa.etf.si.local_server.repositories.ReceiptRepository;
import ba.unsa.etf.si.local_server.requests.LoginRequest;
import ba.unsa.etf.si.local_server.requests.ReceiptRequest;
import ba.unsa.etf.si.local_server.responses.LoginResponse;
import ba.unsa.etf.si.local_server.responses.ReceiptResponse;
import ba.unsa.etf.si.local_server.services.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class ReceiptController {
    private final ReceiptService receiptService;
    private final ReceiptRepository receiptRepository;

    @PostMapping("/api/receipts")
    public ResponseEntity<?> saveReceipt(@Valid @RequestBody ReceiptRequest receiptRequest) {
        //ReceiptStatus receiptStatus = receiptRepository.getOne(receiptRequest.getId()).getReceiptStatus();
        String responseMessage = receiptService.checkRequest(receiptRequest);
        return ResponseEntity.ok(new ReceiptResponse(responseMessage));
    }

    @GetMapping("/api/receipts/{id}")
    public Receipt getReceipt(@PathVariable Long id) {
        return receiptService.getReceipt(id);
    }
    //@PostMapping("/api/receipts/{id}")
    //public ResponseEntity<?> deleteReceipt()

}
