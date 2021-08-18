package roomBooking.api.host;

import org.springframework.stereotype.Component;
import roomBooking.api.property.PropertyDTO;
import roomBooking.api.property.PropertyDTOMapper;
import roomBooking.api.utils.DTOMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HostDTOMapper implements DTOMapper<Host, HostDTO> {

    private final PropertyDTOMapper propertyDTOMapper;

    public HostDTOMapper(PropertyDTOMapper propertyDTOMapper) {
        this.propertyDTOMapper = propertyDTOMapper;
    }

    @Override
    public HostDTO from(Host from) {

        List<PropertyDTO> properties = from.getProperties()
                .stream()
                .map(propertyDTOMapper::from)
                .collect(Collectors.toList());

        HostDTO hostDTO = HostDTO
                .builder()
                .companyName(from.getCompanyName())
                .nipNumber(from.getNipNumber())
                .regonNumber(from.getRegonNumber())
                .email(from.getEmail())
                .phoneNumber(from.getPhoneNumber())
                .registrationDate(from.getRegistrationDate().toString())
                .properties(properties)
                .build();
        return hostDTO;
    }
}
