package learn.memories.domain;

import learn.memories.models.Memory;
import learn.memories.data.MockMemoryRepository;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryServiceTest {

    @Test
    public void testAddMemoryWithShortContent() {
        MemoryService service = new MemoryService(new MockMemoryRepository());
        Memory memory = new Memory();
        memory.setFrom("Nesha");
        memory.setContent("Too short");

        MemoryResult result = service.add(memory);

        assertFalse(result.isSuccess());
        assertTrue(result.getErrorMessages().contains("Memory `content` must be at least 10 characters."));
    }
}
