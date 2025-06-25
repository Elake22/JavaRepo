// src/test/java/repository/InMemoryMemoryRepositoryTest.java
package learn.memories.domain;

import domain.Memory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMemoryRepositoryTest {

    private repository.InMemoryMemoryRepository repository;

    @BeforeEach
    void setup() {
        repository = new repository.InMemoryMemoryRepository();
        repository.add(new Memory("1", "Test memory 1"));
        repository.add(new Memory("2", "Test memory 2"));
    }

    @Test
    void testAddAndFindById() {
        Memory memory = new Memory("3", "New memory");
        repository.add(memory);
        assertEquals("New memory", repository.findById("3").getContent());
    }

    @Test
    void testUpdate() {
        Memory updated = new Memory("1", "Updated content");
        assertTrue(repository.update(updated));
        assertEquals("Updated content", repository.findById("1").getContent());
    }

    @Test
    void testDeleteById() {
        assertTrue(repository.deleteById("1"));
        assertNull(repository.findById("1"));
    }

    @Test
    void testFindAll() {
        List<Memory> all = repository.findAll();
        assertEquals(2, all.size());
    }
}
