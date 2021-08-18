package roomBooking.api.host;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomBooking.api.exceptions.HostNotFoundExceptions;
import roomBooking.api.exceptions.RequiredNotNullValue;

@RestController
@RequestMapping("api/v1")
public class HostRestController {

    private final HostService hostService;

    public HostRestController(HostService hostService) {
        this.hostService = hostService;
    }

    @PostMapping("/hosts")
    public ResponseEntity<Host> createOrUpdateHost(@RequestBody Host host) {
        if (host.isAnyHostFieldNull()) {
            throw new RequiredNotNullValue("Host has null field.");
        }
        if (host.getId() == null) {
            return new ResponseEntity<>(hostService.createOrUpdateHost(host), HttpStatus.CREATED);
        }
        if (hostService.getHostById(host.getId()) == null) {
            throw new HostNotFoundExceptions("Host not found by id: " + host.getId() + ".");
        }
        return new ResponseEntity<>(hostService.createOrUpdateHost(host), HttpStatus.OK);
    }

    @GetMapping("/hosts")
    public ResponseEntity<?> getHostsDTO(@RequestParam(required = false) Long id,
                                         @RequestParam(required = false) String companyName) {
        if (id != null) {
            return new ResponseEntity<>(hostService.getHostDTOById(id), HttpStatus.OK);
        }
        if (companyName != null) {
            return new ResponseEntity<>(hostService.getHostDTOByCompanyName(companyName), HttpStatus.OK);
        }
        return new ResponseEntity<>(hostService.getAllHostsDTO(), HttpStatus.OK);
    }

    @DeleteMapping("/hosts/{id}/delete")
    public ResponseEntity<?> deleteHostById(@PathVariable Long id) {
        if (hostService.getHostById(id) == null) {
            throw new HostNotFoundExceptions("Host not found by id: " + id + ".");
        }
        if (hostService.deleteHostById(id) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
