package org.example.data;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Server;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ServerRepo {
    Server getServerById(int id) throws InternalErrorException, RecordNotFoundException;

    //Servers have a HireDate and a TermDate.
    //Return only servers where the given date falls in between the HireDate and TermDate inclusive.
    List<Server> getAllAvailableServers(LocalDate date) throws InternalErrorException;

    List<Server> findAll() throws InternalErrorException;
}

