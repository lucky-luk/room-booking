package roomBooking.api.property;

import org.springframework.stereotype.Component;
import roomBooking.api.utils.DTOMapper;

@Component
public class AddressDTOMapper implements DTOMapper<Address, AddressDTO> {
    @Override
    public AddressDTO from(Address from) {
        AddressDTO addressDTO = AddressDTO
                .builder()
                .country(from.getCountry())
                .city(from.getCity())
                .postalCode(from.getPostalCode())
                .street(from.getStreet())
                .houseNumber(from.getHouseNumber())
                .build();
        return addressDTO;
    }
}
