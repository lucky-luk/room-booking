package roomBooking.api.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createOrUpdateClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.createOrUpdateClient(client), HttpStatus.CREATED);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClientDTOById(@RequestParam Long id) {
        return new ResponseEntity<>(clientService.getClientDTOById(id), HttpStatus.OK);
    }

    @GetMapping("/clients/{email}")
    public ResponseEntity<ClientDTO> getClients(@RequestParam(required = false) String email) {
        return new ResponseEntity<>(clientService.getClientByEmail(email), HttpStatus.OK);
    }
}
