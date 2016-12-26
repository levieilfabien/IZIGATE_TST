package test.java;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import beans.ObjectifBean;
import exceptions.SeleniumException;
import main.bean.CasEssaiIzigateBean;
import main.constantes.Cibles;
import main.constantes.Constantes;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;
import outils.PropertiesOutil;
import outils.SeleniumOutils;

/**
 * Sc�nario modularis� des tests automatis�s sur Izizgate - 12/2016
 * @author levieilfa bardouma
 */
public class FTSC00 extends SC00Test {
	
//D�finir le distributeur (CE, BP ou CEBPIOM)
int distributeur = Constantes.CAS_BP;
//D�finir le num�ro FFI � rechercher
String numFFI = "FFI639214913";
//D�finir l'action � r�aliser sur le num�ro FFI (null pour consultation et murissement, ou )
String typeAction = "consultation";
/**
 * Id de s�rialisation par d�faut.
 */
private static final long serialVersionUID = 1L;

@Test
public void initialisationTest() throws SeleniumException {
	
	//Description du sc�nario
	CasEssaiIzigateBean scenario0 = new CasEssaiIzigateBean();
	//Configuration du driver
	FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
	FirefoxProfile profile = configurerProfilNatixis();
	//Cr�ation et configuration du repertoire de t�l�chargement
	//File repertoireTelechargement = new File(".\\" + scenario0.getNomCasEssai());
	//repertoireTelechargement.mkdir();
	//profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
	String repertoire = creerRepertoireTelechargement(scenario0, profile);
	scenario0.setRepertoireTelechargement(repertoire);
	// Initialisation du driver
	FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
	driver.get(Constantes.URL_IZIGATE);
	// LISTE DES OBJECTIFS DU CAS DE TEST
	scenario0.ajouterObjectif(new ObjectifBean("Test arriv� � terme", scenario0.getNomCasEssai() + scenario0.getTime()));
   
    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
    outil.setRepertoireRacine(scenario0.getRepertoireTelechargement());
    
    try {
		//CT01 - 
		//CT02 - 
		//CT03 - 
		scenario0.getTests().add(CT01Initialisation(scenario0, outil));
		//scenario0.getTests().add(CT02Consultation(scenario0, outil));
		//scenario0.getTests().add(CT03Murissement(scenario0, outil));
		
	} catch (SeleniumException ex) {
		// Finalisation en erreur du cas de test.
		finaliserTestEnErreur(outil, scenario0, ex, scenario0.getNomCasEssai() + scenario0.getDateCreation().getTime());
		throw ex;
	}
	// Finalisation normale du cas de test.
    finaliserTest(outil, scenario0, scenario0.getNomCasEssai() + scenario0.getDateCreation().getTime());
}

/**
 * Partie du scenario0 regroupant la saisie, l'instruction et la validation d'un dossier dans IZIVENTE CE.
 * @param scenario le scenario englobant.
 * @param outil l'outil de manipulation du navigateur.
 * @return le cas d'essai document� pour ajout au sc�nario.
 * @throws SeleniumException en cas d'erreur.
 */
public CasEssaiIzigateBean CT01Initialisation(CasEssaiIzigateBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT01
	CasEssaiIzigateBean CT01 = new CasEssaiIzigateBean();
	//Information issues du sc�nario.
	//Gestion des steps
	CT01.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT01.getNomCasEssai() + CT01.getTime()));
	CT01.ajouterStep("Lancement de firefox et de l'url d'acc�s � Izigate", "Acces accueil", "Affichage de la page d'accueil d'Izigate");
	CT01.ajouterStep("Inscription des identifiants d'acc�s � Izigate et validation", "Acces Izigate", "Affichage de l'�cran principal d'Izigate");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// ACCES IZIGATE ET INITIALISATION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Steps 1 et 2 : Acces � la page d'accueil, inscription des identifiants et acc�s � l'�cran principal d'Izigate
	String login = PropertiesOutil.getInfoConstante("IZIGATE.login");
	String password = PropertiesOutil.getInfoConstante("IZIGATE.password");
	String url = Constantes.URL_IZIGATE;
	outil.chargerUrl(url);
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIGATE);
	//outil.viderEtSaisir(login, Cibles.SAISIE_LOGIN);
	//outil.viderEtSaisir(password, Cibles.SAISIE_MDP);
	outil.cliquer(Cibles.BOUTON_VALIDATION_LOGIN);
	outil.attendreChargementElement(Cibles.SAISIE_FFI_CONSULT);
	outil.viderEtSaisir(numFFI, Cibles.SAISIE_FFI_CONSULT);
	outil.attendreChargementElement(Cibles.BOUTON_ENVOI_RECHERCHE);
	outil.cliquer(Cibles.BOUTON_ENVOI_RECHERCHE);
	//outil.attendrePresenceTexte("Consultation base BP");
	//System.out.println("S�lection du distributeur BP");
	CT01.validerObjectif(outil.getDriver(), "Acces accueil", true);
	CT01.validerObjectif(outil.getDriver(), "Acces Izigate", true);
	CT01.validerObjectif(outil.getDriver(), CT01.getNomCasEssai() + CT01.getTime(),true);
	return CT01;
}
/*
 * public CasEssaiIzigateBean CT02Consultation(CasEssaiIzigateBean scenario, SeleniumOutils outil) throws SeleniumException {

	//Param�trage du CT02
	CasEssaiIzigateBean CT02 = new CasEssaiIzigateBean();
	//Information issues du sc�nario.
	//Gestion des steps
	CT02.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT02.getNomCasEssai() + CT02.getTime()));
	CT02.ajouterStep("S�lectionner l'onglet correspondant au distributeur voulu", "S�lection du distributeur", "Affichage de la page de consultation d'un dossier pour le distributeur voulu");
	CT02.ajouterStep("V�rifier l'intitul� de la page", "Page de consultation", "Affichage de l'�cran de consultation ok");
	CT02.ajouterStep("Renseigner le num�ro FFI et lancer la recherche", "Renseignement du num�ro FFI et envoi", "Recheche lanc� et affichage des donn�es du dossier en consultation");
	CT02.ajouterStep("V�rifier le contenu des donn�es du dossier en consultation", "V�rification des donn�es", "Donn�es conformes � l'attendu");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// CONSULTATION D'UN DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//modificateur.emprunteurCasden = true;
	//Steps 1 et 2 : S�lection du distributeur et acc�s � la page de recherche d'un dossier en consultation
//	switch (distributeur){
//	case Constantes.CAS_CE :
//	outil.attendreEtCliquer(Cibles.BOUTON_ONGLET_CE);
//	CT02.validerObjectif(outil.getDriver(), "S�lection du distributeur", true);
//	outil.attendrePresenceTexte("Consultation base CE");
///	CT02.validerObjectif(outil.getDriver(), "Page de consultation", true);
	//break;
	//case Constantes.CAS_BP :
	outil.attendreEtCliquer(Cibles.BOUTON_ONGLET_BPOP);
	outil.attendrePresenceTexte("Consultation base BP");
	CT02.validerObjectif(outil.getDriver(), "Page de consultation", true);
	System.out.println("S�lection du distributeur BP");
	//break;}
	//Step 3 : Renseignement d'un num�ro FFI et lancement d'une recherche pour consultation
	outil.attendreChargementElement(Cibles.SAISIE_FFI_CONSULT);
	outil.saisir(numFFI, Cibles.SAISIE_FFI_CONSULT);
	outil.attendreChargementElement(Cibles.BOUTON_ENVOI_RECHERCHE);
	outil.cliquer(Cibles.BOUTON_ENVOI_RECHERCHE);
	CT02.validerObjectif(outil.getDriver(), "Renseignement du num�ro FFI et envoi", true);
	CT02.validerObjectif(outil.getDriver(), CT02.getNomCasEssai() + CT02.getTime(),true);
	return CT02;
}

public CasEssaiIzigateBean CT03Murissement(CasEssaiIzigateBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT03
	CasEssaiIzigateBean CT03 = new CasEssaiIzigateBean();
	//Information issues du sc�nario.
	//Gestion des steps
	CT03.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT03.getNomCasEssai() + CT03.getTime()));
	CT03.ajouterStep("S�lectionner l'onglet correspondant au distributeur voulu", "S�lection du distributeur", "Affichage de la page de consultation d'un dossier pour le distributeur voulu");
	CT03.ajouterStep("S�lectionner l'option de m�rissement d'un dossier", "S�lection du m�rissement", "Affichage de l'�cran de m�rissement");
	CT03.ajouterStep("Renseigner le num�ro FFI et lancer la recherche", "Renseignement du num�ro FFI et envoi", "Recheche lanc� et affichage des donn�es du dossier en consultation");
	CT03.ajouterStep("V�rifier le contenu des donn�es du dossier en consultation", "V�rification des donn�es", "Donn�es conformes � l'attendu");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// M�RISSEMENT D'UN DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//modificateur.emprunteurCasden = true;
	//Step 1 : S�lection du distributeur
	switch (distributeur){
	case Constantes.CAS_CE :
	outil.attendreEtCliquer(Cibles.BOUTON_ONGLET_CE);
	CT03.validerObjectif(outil.getDriver(), "S�lection du distributeur", true);
	outil.attendrePresenceTexte("Consultation base CE");
	CT03.validerObjectif(outil.getDriver(), "Page de consultation", true);
	break;
	case Constantes.CAS_BP :
	outil.attendreEtCliquer(Cibles.BOUTON_ONGLET_BPOP);
	outil.attendrePresenceTexte("Consultation base BP");
	CT03.validerObjectif(outil.getDriver(), "Page de consultation", true);
	break;}
	//Step 2 : Acc�der � la page de recherche d'un dossier pour m�rissement
	
	//Step 3 : Renseignement d'un num�ro FFI et lancement d'une recherche pour consultation
	outil.attendreChargementElement(Cibles.SAISIE_FFI_CONSULT);
	outil.saisir(numFFI, Cibles.SAISIE_FFI_CONSULT);
	outil.attendreChargementElement(Cibles.BOUTON_ENVOI_RECHERCHE);
	outil.cliquer(Cibles.BOUTON_ENVOI_RECHERCHE);
	CT03.validerObjectif(outil.getDriver(), "Renseignement du num�ro FFI et envoi", true);

	CT03.validerObjectif(outil.getDriver(), CT03.getNomCasEssai() + CT03.getTime(),true);
	return CT03;
}*/
}