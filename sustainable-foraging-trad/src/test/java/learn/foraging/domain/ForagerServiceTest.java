package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.data.ItemRepositoryDouble;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForagerServiceTest {

    ForagerService service = new ForagerService(new ForagerRepositoryDouble());

    @Test
    void shouldNotAddNullFirstName() throws DataException {
        Forager forager = new Forager();
        forager.setLastName("Piper");
        forager.setState("Soviet Union");

        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddNullLastName() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Peter");
        forager.setState("Soviet Union");

        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddNullState() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Pyotr");
        forager.setLastName("Piper");


        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddBlankFirstName() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("");
        forager.setLastName("Piper");
        forager.setState("Soviet Union");

        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddBlankLastName() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Peter");
        forager.setLastName("");
        forager.setState("Soviet Union");

        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddBlankState() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Pyotr");
        forager.setLastName("Piper");
        forager.setState("");


        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldAddForager() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Pyotr");
        forager.setLastName("Wrangel");
        forager.setState("RA");

        Result<Forager> result = service.add(forager);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddDuplicateForager() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Pyotr");
        forager.setLastName("Wrangel");
        forager.setState("RA");

        Forager foragerTwo = new Forager("Pyotr", "Wrangel", "RA");

        service.add(forager);

        Result<Forager> result = service.add(foragerTwo);
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldAddForagerWithDuplicateFirstName() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Pyotr");
        forager.setLastName("Rangler");
        forager.setState("RA");

        Forager foragerTwo = new Forager("Pyotr", "Wrangel", "RA");

        service.add(forager);

        Result<Forager> result = service.add(foragerTwo);
        assertTrue(result.isSuccess());

    }

}
