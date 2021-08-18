package roomBooking.api.client;

import org.springframework.stereotype.Service;
import roomBooking.api.booking.BookingRepository;
import roomBooking.api.comment.CommentRepository;
import roomBooking.api.exceptions.WrongDataFormatException;
import roomBooking.api.utils.DataFormatValidator;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientDTOMapper clientDTOMapper;
    private final DataFormatValidator dataFormatValidator;
    private final BookingRepository bookingRepository;
    private final CommentRepository commentRepository;

    public ClientService(ClientRepository clientRepository,
                         ClientDTOMapper clientDTOMapper,
                         DataFormatValidator dataFormatValidator,
                         BookingRepository bookingRepository,
                         CommentRepository commentRepository) {
        this.clientRepository = clientRepository;
        this.clientDTOMapper = clientDTOMapper;
        this.dataFormatValidator = dataFormatValidator;
        this.bookingRepository = bookingRepository;
        this.commentRepository = commentRepository;
    }

    public Client createOrUpdateClient(Client client) {
        if (!dataFormatValidator.validateEmail(client.getEmail())) {
            throw new WrongDataFormatException("Wrong email format: " + client.getEmail() + ".");
        }
        client.setRegistrationDate(Date.valueOf(LocalDate.now()));
        clientRepository.save(client);
        return getClientById(client.getId());
    }


    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public ClientDTO getClientDTOById(Long id) {
        return clientDTOMapper.from(getClientById(id));
    }

    public Client getClientByEmail(String email) {
        return clientRepository.getClientByEmail(email);
    }

    public ClientDTO getClientDTOByEmail(String email) {
        return clientDTOMapper.from(getClientByEmail(email));
    }

    public List<ClientDTO> getAllClientsDTO() {
        List<Client> clients = clientRepository.findAll();
        return clients
                .stream()
                .map(clientDTOMapper::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public int deleteClientById(Long id) {
        commentRepository.deleteAllCommentByClientId(id);
        bookingRepository.deleteAllBookingsByClientId(id);
        return clientRepository.deleteClientById(id);
    }

    @Transactional
    public int deleteClientByEmail(String email) {
        Long clientId = getClientByEmail(email).getId();
        commentRepository.deleteAllCommentByClientId(clientId);
        bookingRepository.deleteAllBookingsByClientId(clientId);
        return clientRepository.deleteClientByEmail(email);
    }
}
