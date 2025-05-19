package elmoutaouaki.abderrahim.backend.controllers;

import elmoutaouaki.abderrahim.backend.dtos.CreditDTO;
import elmoutaouaki.abderrahim.backend.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@CrossOrigin("*")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public ResponseEntity<List<CreditDTO>> getAllCredits() {
        return ResponseEntity.ok(creditService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        return creditService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CreditDTO>> getCreditsByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.findByClientId(clientId));
    }

    @PostMapping
    public ResponseEntity<CreditDTO> createCredit(@RequestBody CreditDTO creditDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(creditService.save(creditDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditDTO> updateCredit(@PathVariable Long id, @RequestBody CreditDTO creditDTO) {
        if (!creditService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        creditDTO.setId(id);
        return ResponseEntity.ok(creditService.save(creditDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        if (!creditService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        creditService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}