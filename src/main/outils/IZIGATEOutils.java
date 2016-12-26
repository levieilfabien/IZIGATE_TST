package main.outils;

public class IZIGATEOutils {
	
	/**
	 * Permet de récupérer les n derniers caractères d'une chaîne
	 * @param chaine
	 * @param nombre
	 * @return
	 */
	public static final String derniersCaracteres(String chaine, int nombre)
	{
	    if (chaine.length() <= nombre)
	       return(chaine);
	    else
	        return(chaine.substring(chaine.length() - nombre));
}
	}
