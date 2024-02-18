package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forager;

import java.util.Objects;
import java.util.stream.Collectors;

import java.util.List;

public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public Result<Forager> add(Forager forager) throws DataException {
        Result<Forager> result = validate(forager);

        if(!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(forager));

        return result;
    }

    public Result<Forager> validate(Forager forager) {
        Result<Forager> result = new Result<>();
        if(forager == null) {
            result.addErrorMessage("Forager must not be null.");
            return result;
        }

        if(forager.getFirstName() == null || forager.getFirstName().isBlank()) {
            result.addErrorMessage("First name is required.");
        }

        if(forager.getLastName() == null || forager.getLastName().isBlank()) {
            result.addErrorMessage("Last name is required.");
        }

        if(forager.getState() == null || forager.getState().isBlank()) {
            result.addErrorMessage("State is required.");
        }

        List<Forager> foragers = repository.findAll();
        for (Forager f : foragers) {
            if (Objects.equals(forager.getFirstName(), f.getFirstName())
                    && Objects.equals(forager.getLastName(), f.getLastName())
                    && Objects.equals(forager.getState(), f.getState())) {
                result.addErrorMessage("duplicate forager is not allowed");
                return result;
            }
        }

        return result;
    }


    public List<Forager> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(i -> i.getLastName().startsWith(prefix))
                .collect(Collectors.toList());
    }
}
