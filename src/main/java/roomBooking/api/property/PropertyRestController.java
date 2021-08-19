package roomBooking.api.property;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomBooking.api.exceptions.ClientNotFoundExceptions;
import roomBooking.api.exceptions.PropertyNotFoundException;
import roomBooking.api.exceptions.RequiredNotNullValue;
import roomBooking.api.exceptions.WrongParameterException;
import roomBooking.api.host.HostService;

@RestController
@RequestMapping("api/v1")
public class PropertyRestController {

    private final PropertyService propertyService;
    private final HostService hostService;

    public PropertyRestController(PropertyService propertyService, HostService hostService) {
        this.propertyService = propertyService;
        this.hostService = hostService;
    }

    @PostMapping("/properties")
    public ResponseEntity<Property> createOrUpdateProperty(@RequestBody Property property,
                                                           @RequestParam Long hostId) {
        if (property.isAnyPropertyFieldNull()) {
            throw new RequiredNotNullValue("Property has null field.");
        }
        if (property.getAddress().isAnyAddressFieldNull()) {
            throw new RequiredNotNullValue("Address has null field.");
        }
        if (property.getId() != null) {
            if (propertyService.getPropertyById(property.getId()) == null) {
                throw new PropertyNotFoundException("Property not found by id: " + property.getId() + ".");
            }
            return new ResponseEntity<>(propertyService.createOrUpdateProperty(property, hostId), HttpStatus.OK);
        }
        return new ResponseEntity<>(propertyService.createOrUpdateProperty(property, hostId), HttpStatus.CREATED);
    }

    @GetMapping("/properties")
    public ResponseEntity<?> getProperties(@RequestParam(required = false) Long propertyId,
                                           @RequestParam(required = false) String propertyName,
                                           @RequestParam(required = false) PropertyType propertyType,
                                           @RequestParam(required = false) String city) {
        if (propertyId != null) {
            return new ResponseEntity<>(propertyService.getPropertyDTOById(propertyId), HttpStatus.OK);
        }
        if (propertyName != null) {
            return new ResponseEntity<>(propertyService.getPropertyDTOByName(propertyName), HttpStatus.OK);
        }
        if (propertyType != null) {
            return new ResponseEntity<>(propertyService.getPropertyDTOByType(propertyType), HttpStatus.OK);
        }
        if (city != null) {
            return new ResponseEntity<>(propertyService.getPropertyDTOByCity(city), HttpStatus.OK);
        }
        return new ResponseEntity<>(propertyService.getAllPropertiesDTO(), HttpStatus.OK);
    }

    @DeleteMapping("/properties/delete")
    public ResponseEntity<?> deleteProperty(@RequestParam(required = false) Long id,
                                            @RequestParam(required = false) String propertyName) {
        if (id == null && propertyName == null) {
            throw new WrongParameterException("One of the parameters must be filled in.");
        }
        if (id != null) {
            if (propertyService.getPropertyById(id) == null) {
                throw new PropertyNotFoundException("Property not found by id: " + id + ".");
            }
            propertyService.deletePropertyById(id);
        } else {
            if (propertyService.getPropertyByName(propertyName) == null) {
                throw new ClientNotFoundExceptions("Client not found by property name: " + propertyName + ".");
            }
            propertyService.deletePropertyByName(propertyName);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}