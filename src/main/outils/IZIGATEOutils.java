package main.outils;

public class IZIGATEOutils {
	
	/**
	 * Permet de r�cup�rer les n derniers caract�res d'une cha�ne
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
