package fr.diginamic.resttp05.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.resttp05.model.Operation;

public interface iCrudOperation extends CrudRepository<Operation, Integer> {

}
