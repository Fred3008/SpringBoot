package fr.diginamic.resttp05.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@NotBlank
	private String nom;
	@NotNull
	@NotBlank
	private String prenom;
	
	private LocalDate dateNaissance;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name ="BANQUE_ID")	
	private Banque banque;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "CLIENT_COMPTE", joinColumns = @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "COMPTE_ID", referencedColumnName = "ID"))
	private Set<Compte> comptes;
	
	@NotNull
	@NotBlank
	@Embedded
	private Adresse adresse;

	public Client() {
		comptes = new HashSet<>();
	}

	public Client(String nom, String prenom, LocalDate dateNaissance) {
		this();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

	public Client(String nom, String prenom, LocalDate dateNaissance, Banque banque) {
		this(nom, prenom, dateNaissance);
		this.banque = banque;
	}

	public Client(String nom, String prenom, LocalDate dateNaissance, Banque banque, Adresse adresse) {
		this(nom, prenom, dateNaissance, banque);
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		if(this.banque != null) {
			
		}
		this.banque = banque;
		if(this.banque != null) {
			
		}		
	}
	
	public void addCompte(Compte compte) {
		this.comptes.add(compte);
		
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + "]";
	}



}
