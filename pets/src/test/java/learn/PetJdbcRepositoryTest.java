import learn.pets.data.models.Pet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


void main() {
}

@Test
void shouldFindAll() {
    List<Pet> pets = PetJdbcRepository.findAll();
    // Not really part of the test.
    // But it's nice to confirm in the console.
    System.out.println(pets);
    assertNotNull(pets);
    assertEquals(3, pets.size());
}

