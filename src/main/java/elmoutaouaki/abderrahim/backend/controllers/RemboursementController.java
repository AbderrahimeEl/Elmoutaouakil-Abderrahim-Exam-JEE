package elmoutaouaki.abderrahim.backend.controllers;

import elmoutaouaki.abderrahim.backend.dtos.RemboursementDTO;
import elmoutaouaki.abderrahim.backend.services.RemboursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
@CrossOrigin("*")
public class RemboursementController {

    private final RemboursementService remboursementService;

    @Autowired
    public RemboursementController(RemboursementService remboursementService) {
        this.remboursementService = remboursementService;
    }

    @GetMapping
    public ResponseEntity<List<RemboursementDTO>> getAllRemboursements() {
        return ResponseEntity.ok(remboursementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemboursementDTO> getRemboursementById(@PathVariable Long id) {
        return remboursementService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/credit/{creditId}")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByCreditId(@PathVariable Long creditId) {
        return ResponseEntity.ok(remboursementService.findByCreditId(creditId));
    }

    @PostMapping
    public ResponseEntity<RemboursementDTO> createRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(remboursementService.save(remboursementDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemboursementDTO> updateRemboursement(@PathVariable Long id, @RequestBody RemboursementDTO remboursementDTO) {
        if (!remboursementService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        remboursementDTO.setId(id);
        return ResponseEntity.ok(remboursementService.save(remboursementDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        if (!remboursementService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        remboursementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}