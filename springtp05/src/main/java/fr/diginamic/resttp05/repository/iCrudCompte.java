package fr.diginamic.resttp05.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.resttp05.model.AssuranceVie;
import fr.diginamic.resttp05.model.Client;
import fr.diginamic.resttp05.model.Compte;
import fr.diginamic.resttp05.model.CompteCourant;
import fr.diginamic.resttp05.model.LivretA;
import fr.diginamic.resttp05.model.Operation;

public interface iCrudCompte extends CrudRepository<Compte, Integer> {

	@Query("select o from Operation o where o.compte.id = :id")
	public Iterable<Operation> findByOperation(int id);
	
	  @Query("select c from Client c where :cpt MEMBER OF c.comptes")
	     public Iterable<Client> findByClient(Compte cpt);

	  @Query("select c from CompteCourant c ")
	  public Iterable<CompteCourant> getAllComptes();
	
	  
	  @Query("select a from AssuranceVie a ")
	  public Iterable<AssuranceVie> getAllAssuranceVies();
	  
	  @Query("select c from LivretA c ")
	  public Iterable<LivretA> getAllLivretA();
	  
	  
	  
}
