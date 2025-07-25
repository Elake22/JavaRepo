package org.example.data;

import org.example.model.ItemCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemCategoryRepo {
    List<ItemCategory> findAll();
    Optional<ItemCategory> findById(int id);
}
