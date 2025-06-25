// src/main/java/repository/MemoryRepository.java
package repository;

import domain.Memory;
import java.util.List;

public interface MemoryRepository {
    void add(Memory memory);
    boolean update(Memory memory);
    boolean deleteById(String id);
    Memory findById(String id);
    List<Memory> findAll();
}