package com.paymybuddy.moneytransfert.service.impl;

import com.paymybuddy.moneytransfert.model.Client;
import com.paymybuddy.moneytransfert.repository.ClientRepository;
import com.paymybuddy.moneytransfert.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClientByClientId(String clientId){ return clientRepository.findByClientMail(clientId); }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteByClientId(String clientId) {
        clientRepository.deleteById(clientId);
    }

    //Pagination

    @Override
    public Page<Client> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.clientRepository.findAll(pageable);
    }

}
