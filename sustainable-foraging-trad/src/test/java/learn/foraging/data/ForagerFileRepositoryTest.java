package learn.foraging.data;

import learn.foraging.data.ForagerFileRepository;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForagerFileRepositoryTest {

    ForagerFileRepository repo = new ForagerFileRepository(SEED_FILE_PATH);

    static final String SEED_FILE_PATH = "./data/foragers-test";
    static final String TEST_FILE_PATH = "./data/foragers.csv";
    static final String TEST_DIR_PATH = "./data/forage_data_test";
    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }
    @Test
    void shouldFindAll() {

        List<Forager> all = repo.findAll();
        assertEquals(1000, all.size());
    }

    @Test
    void shouldAddForager() throws DataException {

        Forager expected = new Forager();
        expected.setFirstName("Tim");
        expected.setLastName("Curry");
        expected.setState("Alaska");
        expected.setId("1903724r-1094525-kdjfgq45");

        Forager actual = repo.add(expected);

        assertEquals(expected, actual);

    }

}