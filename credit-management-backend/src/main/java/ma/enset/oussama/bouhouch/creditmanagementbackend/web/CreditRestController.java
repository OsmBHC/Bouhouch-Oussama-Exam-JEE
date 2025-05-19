package ma.enset.oussama.bouhouch.creditmanagementbackend.web;

import ma.enset.oussama.bouhouch.creditmanagementbackend.dtos.CreditDTO;
import ma.enset.oussama.bouhouch.creditmanagementbackend.services.CreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credits")
@CrossOrigin("*")
public class CreditRestController {
    private final CreditService creditService;

    public CreditRestController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public ResponseEntity<List<CreditDTO>> getAllCredits() {
        return ResponseEntity.ok(creditService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.findById(id));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditDTO>> getCreditsByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.findByClientId(clientId));
    }

    @PostMapping
    public ResponseEntity<CreditDTO> createCredit(@RequestBody CreditDTO creditDTO) {
        return ResponseEntity.ok(creditService.create(creditDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditDTO> updateCredit(@PathVariable Long id, @RequestBody CreditDTO creditDTO) {
        return ResponseEntity.ok(creditService.update(id, creditDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.delete(id);
        return ResponseEntity.noContent().build();
    }
}