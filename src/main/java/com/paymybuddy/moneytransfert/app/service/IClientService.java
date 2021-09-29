package com.paymybuddy.moneytransfert.app.service;

import com.paymybuddy.moneytransfert.app.model.Client;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IClientService {

	public List<Client> getClients();

	public Client getClientByClientMail(String clientMail);

	public Client saveClient(Client client);

/*
	public void deleteByClientId(String clientId);
*/

	public Page<Client> findPaginated(int pageNo, int page, String sortField, String sortDirection);

}
