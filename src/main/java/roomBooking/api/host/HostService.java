package roomBooking.api.host;

import org.springframework.stereotype.Service;
import roomBooking.api.booking.BookingRepository;
import roomBooking.api.comment.CommentRepository;
import roomBooking.api.exceptions.HostNotFoundExceptions;
import roomBooking.api.exceptions.RequiredNotNullValue;
import roomBooking.api.exceptions.WrongDataFormatException;
import roomBooking.api.property.Property;
import roomBooking.api.property.PropertyRepository;
import roomBooking.api.property.RoomRepository;
import roomBooking.api.utils.DataFormatValidator;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HostService {

    private final HostRepository hostRepository;
    private final HostDTOMapper hostDTOMapper;
    private final PropertyRepository propertyRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final CommentRepository commentRepository;
    private final DataFormatValidator dataFormatValidator;

    public HostService(HostRepository hostRepository,
                       HostDTOMapper hostDTOMapper,
                       PropertyRepository propertyRepository,
                       RoomRepository roomRepository,
                       BookingRepository bookingRepository,
                       CommentRepository commentRepository,
                       DataFormatValidator dataFormatValidator) {
        this.hostRepository = hostRepository;
        this.hostDTOMapper = hostDTOMapper;
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.commentRepository = commentRepository;
        this.dataFormatValidator = dataFormatValidator;
    }

    public Host createOrUpdateHost(Host host) {
        if (host.getNipNumber() == null && host.getRegonNumber() == null) {
            throw new RequiredNotNullValue("One of the field: NIP_NUMBER; REGON_NUMBER, must be filled in.");
        }
        if (host.getNipNumber() != null) {
            if (!dataFormatValidator.validateNipNumber(host.getNipNumber())) {
                throw new WrongDataFormatException("Wrong NIP NUMBER format: " + host.getNipNumber() + ".");
            }
        }
        if (host.getRegonNumber() != null) {
            if (!dataFormatValidator.validateRegonNumber(host.getRegonNumber())) {
                throw new WrongDataFormatException("Wrong REGON NUMBER format: " + host.getRegonNumber() + ".");
            }
        }
        if (!dataFormatValidator.validateEmail(host.getEmail())) {
            throw new WrongDataFormatException("Wrong email format: " + host.getEmail() + ".");
        }
        host.setRegistrationDate(Date.valueOf(LocalDate.now()));
        return hostRepository.save(host);
    }

    public Host getHostById(Long id) {
        Host host = hostRepository.findById(id).orElse(null);
        if (host == null) {
            throw new HostNotFoundExceptions("Host not found by id: " + id + ".");
        }
        return host;
    }

    public HostDTO getHostDTOById(Long id) {
        return hostDTOMapper.from(getHostById(id));
    }

    public HostDTO getHostDTOByCompanyName(String companyName) {
        Host host = hostRepository.getHostByCompanyName(companyName);
        if (host == null) {
            throw new HostNotFoundExceptions("Host not found by company name: " + companyName + ".");
        }
        return hostDTOMapper.from(host);
    }

    public List<HostDTO> getAllHostsDTO() {
        List<HostDTO> hostsDTO = hostRepository.findAll()
                .stream()
                .map(hostDTOMapper::from)
                .collect(Collectors.toList());
        if (hostsDTO.isEmpty()) {
            throw new HostNotFoundExceptions("List of hosts is empty.");
        }
        return hostsDTO;
    }

    @Transactional
    public int deleteHostById(Long id) {
        List<Property> properties = propertyRepository.getAllPropertiesByHostId(id);
        if (!properties.isEmpty()) {
            for (int i = 0; i < properties.size(); i++) {
                Long propertyId = properties.get(i).getId();
                Long addressId = propertyRepository.getPropertyAddressId(propertyId);
                bookingRepository.deleteAllBookingsByPropertyId(propertyId);
                roomRepository.deleteAllRoomsByPropertyId(propertyId);
                commentRepository.deleteAllCommentsByPropertyId(propertyId);
                propertyRepository.deletePropertyById(propertyId);
                propertyRepository.deleteAddressById(addressId);
            }
        }
        return hostRepository.deleteHostById(id);
    }
}
