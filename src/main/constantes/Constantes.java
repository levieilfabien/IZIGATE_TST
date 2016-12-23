package main.constantes;

import outils.PropertiesOutil;

/**
 * Ensemble des constantes manipulées par les tests IZIVENTES et les IHMS.
 * Certaines variables pointent directement sur le fichier properties, celui ci doit être dans les resources.
 * @author levieilfa
 *
 */
public class Constantes {
	
	//////////////////////////////////////////////////// INFORMATIONS TECHNIQUES ////////////////////////////////////////////////////////////////
	public static final String EMPLACEMENT_FIREFOX = PropertiesOutil.getInfoConstante("EMPLACEMENT_FIREFOX");
	public static final String EMPLACEMENT_PROFIL = PropertiesOutil.getInfoConstante("EMPLACEMENT_PROFILE");
	
	
	//////////////////////////////////////////////////// INFORMATIONS POUR LES TESTS ////////////////////////////////////////////////////////////
	
	public static final int CAS_CE = 1;
	public static final int CAS_BPCEIOM = 2;
	public static final int CAS_BP = 3;
	
//	public static final String REPERTOIRE_TEST = PropertiesOutil.getInfoConstante("REPERTOIRE_TEST");
//	public static final String REPERTOIRE_TEST2 = PropertiesOutil.getInfoConstante("REPERTOIRE_TEST2");

	public static final String URL_IZIGATE = PropertiesOutil.getInfoConstante("URL_IZIGATE_RECETTE");
	
	public static final String TITRE_PAGE_IZIGATE = "IZIGATE - Login";

	////////////////////////////////////////////////////INFORMATIONS POUR LES PREFERENCES ////////////////////////////////////////////////////////////
	public static final String PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT = "browser.download.dir";
}
