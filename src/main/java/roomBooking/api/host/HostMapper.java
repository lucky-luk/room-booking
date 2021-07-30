package roomBooking.api.host;

import org.springframework.stereotype.Component;
import roomBooking.api.utils.DTOMapper;

@Component
public class HostMapper implements DTOMapper<Host, HostDTO> {

    @Override
    public HostDTO from(Host from) {
        HostDTO hostDTO = HostDTO
                .builder()
                .companyName(from.getCompanyName())
                .nipNumber(from.getNipNumber())
                .regonNumber(from.getRegonNumber())
                .email(from.getEmail())
                .phoneNumber(from.getPhoneNumber())
                .registrationDate(from.getRegistrationDate())
                .properties(from.getProperties())
                .build();
        return hostDTO;
    }
}
