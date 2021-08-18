package roomBooking.api.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomBooking.api.exceptions.ClientNotFoundExceptions;
import roomBooking.api.exceptions.RequiredNotNullValue;
import roomBooking.api.exceptions.WrongParameterException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createOrUpdateClient(@RequestBody Client client) {
        if (client.isAnyClientFieldNull()) {
            throw new RequiredNotNullValue("Client has null field.");
        }
        if (client.getId() == null) {
            return new ResponseEntity<>(clientService.createOrUpdateClient(client), HttpStatus.CREATED);
        }
        if (clientService.getClientById(client.getId()) == null) {
            throw new ClientNotFoundExceptions("Client not found by id: " + client.getId() + ".");
        }
        return new ResponseEntity<>(clientService.createOrUpdateClient(client), HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClientDTOById(@PathVariable Long id) {
        if (clientService.getClientById(id) == null) {
            throw new ClientNotFoundExceptions("Client not found by id: " + id + ".");
        }
        return new ResponseEntity<>(clientService.getClientDTOById(id), HttpStatus.OK);
    }

    @GetMapping("/clients/")
    public ResponseEntity<?> getClientsDTO(@RequestParam(required = false) String email) {
        if (email != null) {
            if (clientService.getClientByEmail(email) == null) {
                throw new ClientNotFoundExceptions("Client not found by email: " + email + ".");
            }
            return new ResponseEntity<>(clientService.getClientDTOByEmail(email), HttpStatus.OK);
        }
        List<ClientDTO> clientsDTO = clientService.getAllClientsDTO();
        if (clientsDTO.isEmpty()) {
            throw new ClientNotFoundExceptions("List of clients is empty.");
        }
        return new ResponseEntity<>(clientsDTO, HttpStatus.OK);
    }

    @DeleteMapping("clients/delete")
    public ResponseEntity<?> deleteClient(@RequestParam(required = false) Long id,
                                          @RequestParam(required = false) String email) {
        if (id == null && email == null) {
            throw new WrongParameterException("One of the parameters must be filled in.");
        }
        if (id != null) {
            if (clientService.getClientById(id) == null) {
                throw new ClientNotFoundExceptions("Client not found by id: " + id + ".");
            }
            if (clientService.deleteClientById(id) > 0) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            if (clientService.getClientByEmail(email) == null) {
                throw new ClientNotFoundExceptions("Client not found by email: " + email + ".");
            }
            if (clientService.deleteClientByEmail(email) > 0) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        }
    }
}
