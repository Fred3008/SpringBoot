package fr.diginamic.resttp05.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="assurancevie")
public class AssuranceVie extends Compte {
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	private Double taux;

	public AssuranceVie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssuranceVie(Date dateFin, Double taux) {
		super();
		this.dateFin = dateFin;
		this.taux = taux;
	}
	
	public AssuranceVie(String numero, Double  solde, Date dateFin, Double taux) {
		super(numero, solde);
		this.dateFin =dateFin;
		this.taux = taux;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		return "AssuranceVie [dateFin=" + dateFin + ", taux=" + taux + "]";
	}

}
