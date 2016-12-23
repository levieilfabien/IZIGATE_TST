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

	/**
	 * Le numéro FFI du dossier saisie si il y a lieu.
	 */
	private String numeroFFI = "";
	

	
	public CasEssaiIzigateBean() {
		super();
	}

	public String getNumeroFFI() {
		return numeroFFI;
	}

	public void setNumeroFFI(String numeroFFI) {
		this.numeroFFI = numeroFFI;
	}

}

