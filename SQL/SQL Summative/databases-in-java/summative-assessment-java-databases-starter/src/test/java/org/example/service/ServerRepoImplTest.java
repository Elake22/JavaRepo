package org.example.service;

import org.example.data.ServerRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.ServerRepoImpl;
import org.example.model.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(ServerRepoImpl.class)  // Tells Spring to use our JDBC-based implementation
public class ServerRepoImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ServerRepo serverRepo;

    @BeforeEach
    void resetDatabase() {
        // Call the stored procedure or use inline cleanup SQL
        jdbcTemplate.execute("CALL sp_reset_data();"); // replace with actual name if different
    }

    @Test
    void getServerById_validId_returnsServer() throws Exception {
        Server server = serverRepo.getServerById(1);
        assertNotNull(server);
        assertEquals(1, server.getServerID());
        assertEquals("Mersey", server.getFirstName()); // adjust name based on seed data
    }

    @Test
    void getServerById_invalidId_throwsRecordNotFoundException() {
        assertThrows(RecordNotFoundException.class, () -> serverRepo.getServerById(9999));
    }

    @Test
    void getAllAvailableServers_validDate_returnsResults() throws Exception {
        LocalDate testDate = LocalDate.of(2022, 12, 7); // adjust date to fall within valid Hire/Term
        List<Server> servers = serverRepo.getAllAvailableServers(testDate);
        assertNotNull(servers);
        assertFalse(servers.isEmpty());

        // Optional: validate one known result
        boolean containsExpected = servers.stream()
                .anyMatch(s -> s.getFirstName().equals("Zita"));
        assertTrue(containsExpected);
    }
}
