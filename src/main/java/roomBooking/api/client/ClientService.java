package roomBooking.api.client;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public Client createOrUpdateClient(Client client) {
        client.setRegistrationDate(new Date());
        clientRepository.save(client);
        return getClientById(client.getId());
    }

//    public ClientDTO createOrUpdateClient(ClientDTO clientDTO) {
//        Client c = new Client();
//        c.setName(clientDTO.getName());
//        c.setLastName(clientDTO.getLastName());
//        c.setEmail(clientDTO.getEmail());
//        c.setPhoneNumber(clientDTO.getPhoneNumber());
//        c.setNationality(clientDTO.getNationality());
//        c.setRegistrationDate(clientDTO.getRegistrationDate());
//        clientRepository.save(c);
//        return clientDTO;
//    }

//    public ClientDTO getCilentDTOById(Long id) {
//        Client c = clientRepository.getById(id);
//        return ClientDTO
//                .builder()
//                .name(c.getName())
//                .lastName(c.getLastName())
//                .email(c.getEmail())
//                .phoneNumber(c.getPhoneNumber())
//                .build();
//    }

    public ClientDTO getClientDTOById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return clientMapper.from(client.get());
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public ClientDTO getClientByEmail(String email) {
        return getClientDTOById(clientRepository.getClientByEmail(email).getId());
    }
}
