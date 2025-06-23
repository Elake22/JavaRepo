package com.example.repository;

import com.example.model.Party;

import java.util.ArrayList;

public interface WaitlistRepository {

        void load();
        void save();
        void add(Party party);
        Party remove(int index);
        Party callNext();
        ArrayList<Party> getAll();
}

    /* CRUD
     * Create (add new)
     * Retrieve (GetAll(), GetByX()
     * Update (replace)
     * Delete (delete)
     *
     */

