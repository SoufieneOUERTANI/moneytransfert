package com.paymybuddy.moneytransfert.app.service.impl;

import com.paymybuddy.moneytransfert.app.model.Client;
import com.paymybuddy.moneytransfert.app.repository.ClientRepository;
import com.paymybuddy.moneytransfert.app.service.IClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//SOUETransactional
@Service
public class ClientServiceImpl implements IClientService {

    private static final Logger logger = LogManager.getLogger("ClientServiceImpl");

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClientByClientMail(String clientMail){

        logger.info("SOUE3 >>> clientId : "+clientRepository.findByClientMail(clientMail));

        return clientRepository.findByClientMail(clientMail); }
    @Transactional
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

/*
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
    */

}
