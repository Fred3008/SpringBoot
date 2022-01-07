package fr.diginamic.resttp05.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.resttp05.model.Client;

public interface iCrudClient extends CrudRepository<Client, Integer> {

}
