package test.java;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import beans.ObjectifBean;
import exceptions.SeleniumException;
import main.bean.CasEssaiIzigateBean;
import main.constantes.CiblesIzigate;
import main.constantes.Constantes;
import main.outils.IZIGATEOutils;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;
import outils.SeleniumOutils;

	/**
	 * Scénario modularisé des tests automatisés sur Izizgate - 12/2016
	 * @author levieilfa bardouma
	 */
	public class SCConsultation extends SC00TestIzigate {
		
	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;
	
	public CasEssaiIzigateBean lancementTestIzigate(CasEssaiIzigateBean scenario) throws SeleniumException {
		
		//Description du scénario
		CasEssaiIzigateBean scenario0 = scenario;
		//Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		//Création et configuration du repertoire de téléchargement
		//File repertoireTelechargement = new File(".\\" + scenario0.getNomCasEssai());
		//repertoireTelechargement.mkdir();
		//profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
		String repertoire = creerRepertoireTelechargement(scenario0, profile);
		scenario0.setRepertoireTelechargement(repertoire);
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		driver.get(Constantes.URL_IZIGATE);
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario0.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario0.getNomCasEssai() + scenario0.getTime()));
	   
	    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
	    outil.setRepertoireRacine(scenario0.getRepertoireTelechargement());
	    
	    try {
			//CT09 - Initialisation et accès à Izigate
			//CT10 - Consultation d'un dossier
			scenario0.getTests().add(CT09InitialisationIzigate(scenario0, outil));
			scenario0.getTests().add(CT10ConsultationIzigate(scenario0, outil));
			
		} catch (SeleniumException ex) {
			// Finalisation en erreur du cas de test.
			finaliserTestEnErreur(outil, scenario0, ex, scenario0.getNomCasEssai() + scenario0.getDateCreation().getTime());
			throw ex;
		}
		// Finalisation normale du cas de test.
	    finaliserTest(outil, scenario0, scenario0.getNomCasEssai() + scenario0.getDateCreation().getTime());
	    
	    return scenario0;
	}

	/**
	 * Partie du scenario0 regroupant la saisie, l'instruction et la validation d'un dossier dans IZIVENTE CE.
	 * @param scenario le scenario englobant.
	 * @param outil l'outil de manipulation du navigateur.
	 * @return le cas d'essai documenté pour ajout au scénario.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIzigateBean CT09InitialisationIzigate(CasEssaiIzigateBean scenario, SeleniumOutils outil) throws SeleniumException {
		//Paramètrage du CT01
		CasEssaiIzigateBean CT01 = new CasEssaiIzigateBean();
		//Information issues du scénario.
		//Gestion des steps
		CT01.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT01.getNomCasEssai() + CT01.getTime()));
		CT01.ajouterStep("Lancement de firefox et de l'url d'accès à Izigate", "Acces accueil", "Affichage de la page d'accueil d'Izigate");
		CT01.ajouterStep("Inscription des identifiants d'accès à Izigate et validation", "Acces Izigate", "Affichage de l'écran principal d'Izigate");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// ACCES IZIGATE ET INITIALISATION
		//Steps 1 et 2 : Acces à la page d'accueil, inscription des identifiants et accès à l'écran principal d'Izigate
		String retour = accesIzigate(outil);
		System.out.println(retour);
		CT01.validerObjectif(outil.getDriver(), "Acces accueil", true);
		CT01.validerObjectif(outil.getDriver(), "Acces Izigate", true);
		CT01.validerObjectif(outil.getDriver(), CT01.getNomCasEssai() + CT01.getTime(),true);
		return CT01;
	}
	
	public CasEssaiIzigateBean CT10ConsultationIzigate(CasEssaiIzigateBean scenario, SeleniumOutils outil) throws SeleniumException {
		//Paramètrage du CT02
		CasEssaiIzigateBean CT02 = new CasEssaiIzigateBean();
		//Information issues du scénario.
		//Gestion des steps
		CT02.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT02.getNomCasEssai() + CT02.getTime()));
		CT02.ajouterStep("Sélectionner l'onglet correspondant au distributeur voulu", "Sélection du distributeur", "Affichage de la page de consultation d'un dossier pour le distributeur voulu");
		CT02.ajouterStep("Vérifier l'intitulé de la page", "Page de consultation", "Affichage de l'écran de consultation ok");
		CT02.ajouterStep("Renseigner le numéro FFI et lancer la recherche", "Renseignement du numéro FFI et envoi", "Recheche lancé et affichage des données du dossier en consultation");
		CT02.ajouterStep("Vérifier le contenu des données du dossier en consultation", "Vérification des données", "Données conformes à l'attendu");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// CONSULTATION D'UN DOSSIER
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		//Steps 1 et 2 : Sélection du distributeur et accès à la page de recherche d'un dossier en consultation
		switch (scenario.getDistributeur()){
			case Constantes.CAS_CE :
				outil.attendreEtCliquer(CiblesIzigate.BOUTON_ONGLET_CE);
				CT02.validerObjectif(outil.getDriver(), "Sélection du distributeur", true);
				outil.attendrePresenceTexte("Consultation base CE");
				CT02.validerObjectif(outil.getDriver(), "Page de consultation", true);
			break;
			case Constantes.CAS_BP :
				outil.attendreEtCliquer(CiblesIzigate.BOUTON_ONGLET_BPOP);
				outil.attendrePresenceTexte("Consultation base BP");
				CT02.validerObjectif(outil.getDriver(), "Page de consultation", true);
				System.out.println("Sélection du distributeur BP");
			break;
		}
		//Step 3 : Renseignement d'un numéro FFI et lancement d'une recherche pour consultation
		outil.attendreChargementElement(CiblesIzigate.SAISIE_FFI_CONSULT);
		outil.viderEtSaisir(scenario.getNumeroFFI(), CiblesIzigate.SAISIE_FFI_CONSULT);
		outil.attendreChargementElement(CiblesIzigate.BOUTON_ENVOI_RECHERCHE);
		outil.cliquer(CiblesIzigate.BOUTON_ENVOI_RECHERCHE);
		CT02.validerObjectif(outil.getDriver(), "Renseignement du numéro FFI et envoi", true);
		//TODO faire une vérification des données sur le numéro FFI ouvert "N° de dossier SM : FFI639214913"
		outil.changerDeFenetre();
		outil.attendreChargementElement(CiblesIzigate.ELEMENT_SPAN_NODOSS_UNITED);
		scenario.setNumeroDossierUnited(outil.obtenirValeur(CiblesIzigate.ELEMENT_SPAN_NODOSS_UNITED));
		CT02.validerObjectif(outil.getDriver(), CT02.getNomCasEssai() + CT02.getTime(),true);
		return CT02;
	}

}