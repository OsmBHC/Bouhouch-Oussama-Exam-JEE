package ma.enset.oussama.bouhouch.creditmanagementbackend.web;

import ma.enset.oussama.bouhouch.creditmanagementbackend.dtos.ClientDTO;
import ma.enset.oussama.bouhouch.creditmanagementbackend.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin("*")
public class ClientRestController {
    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients(@RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return ResponseEntity.ok(clientService.searchClients(keyword));
        }
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.create(clientDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.update(id, clientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}