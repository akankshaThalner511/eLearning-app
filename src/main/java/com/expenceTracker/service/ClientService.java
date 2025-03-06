package com.expenceTracker.service;

import com.expenceTracker.entity.Client;

public interface ClientService {
    void saveClient(Client client);
    Client findClientById(int id);
}