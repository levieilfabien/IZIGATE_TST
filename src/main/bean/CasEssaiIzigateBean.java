package main.bean;

import beans.CasEssaiBean;

/**
 * Classe d'extension des cas d'essai classique pour IZIGATE
 * @author levieilfa
 *
 */
public class CasEssaiIzigateBean extends CasEssaiBean {

	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;
	
	private int distributeur;

	/**
	 * Le numéro FFI du dossier saisie si il y a lieu.
	 */
	private String numeroFFI = "";
	
	private String numeroDossierUnited = "";

	public CasEssaiIzigateBean() {
		super();
	}
	
	public String getNumeroDossierUnited() {
		return numeroDossierUnited;
	}

	public void setNumeroDossierUnited(String numeroDossierUnited) {
		this.numeroDossierUnited = numeroDossierUnited;
	}

	public int getDistributeur() {
		return distributeur;
	}

	public void setDistributeur(int distributeur) {
		this.distributeur = distributeur;
	}
	public String getNumeroFFI() {
		return numeroFFI;
	}

	public void setNumeroFFI(String numeroFFI) {
		this.numeroFFI = numeroFFI;
	}

}

