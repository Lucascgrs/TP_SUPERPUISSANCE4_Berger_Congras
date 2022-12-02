/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_superpuissance4;

import java.util.HashMap;

/**
 *
 * @author lucas
 */
public class Grille {
    
    int Ligne = 6;
    int Colonne = 7;
    Cellule[][] CellulesJeu = new Cellule[Ligne][Colonne];
    final String RESET = "\033[0m";  // Text Reset
    
    public Grille(){
        
        for(int k = 0; k < Ligne; k++){
            for (int i = 0; i < Colonne; i++){
                CellulesJeu[k][i] = new Cellule();
            }
        }
    }
    
    public int ajouterjetondanscolonne(Jeton jeton, int colonne){
        if(!colonneremplie(colonne)){
            CellulesJeu[0][colonne].affecterjeton(jeton);
            int ligne = tassergrilleparlebas(colonne);
            return ligne;
        }
        return -1;
    }
    
    public boolean etreremplie(){
        for (int k = 0; k < Colonne; k++){
            if(!colonneremplie(k)){
                return false;
            }
        }
        return true;
    }
    
    public void vidergrille(Joueur joueur1, Joueur joueur2){
        Jeton jeton;
        for(int k = 0; k < Ligne; k++){
            for (int i = 0; i < Colonne; i++){
                if(celluleoccupee(k, i)){
                    jeton = CellulesJeu[k][i].recupererjeton();

                    if(jeton.lirecouleur().equals(joueur1.Couleur)){
                        joueur1.ajouterjeton(jeton);
                    }
                    
                    if(jeton.lirecouleur().equals(joueur2.Couleur)){
                        joueur2.ajouterjeton(jeton);
                    }
                    
                    CellulesJeu[k][i].supprimerjeton();
                    
                }
            }
        }
    }
    
    public void affichergrillesurconsole(){
        String txt;
        for (int k = 0; k < Ligne; k++){
            for (int i = 0; i < Colonne; i++){
                
                txt = "[" + color_texte(CellulesJeu[k][i].lirecouleurdujeton()) + CellulesJeu[k][i].lirecouleurdujeton() + RESET + " TN:" + CellulesJeu[k][i].TrouNoir;
                if(!CellulesJeu[k][i].presencetrounoir()& CellulesJeu[k][i].presencedesintegrateur()){
                    txt += " D";
                }
                txt += "]";
                
                System.out.print(txt);
                
                if(i == Colonne - 1){
                    System.out.print("\n");
                }
            }
        }
    }
    
    public boolean celluleoccupee(int ligne, int colonne){
        return (CellulesJeu[ligne][colonne].recupererjeton() != null);
    }
    
    public String lirecouleurdujeton(int ligne, int colonne){
        return CellulesJeu[ligne][colonne].lirecouleurdujeton();
    }
    
    public boolean etregagnantpourjoueur(Joueur joueur, boolean details){
        int h, v, d1, d2;
        String couleur_joueur = joueur.Couleur;
        
        for(int k = 0; k < Ligne; k++){
            for (int i = 0; i < Colonne; i++){
                
                if(celluleoccupee(k, i)){
                    if (CellulesJeu[k][i].lirecouleurdujeton().equals(joueur.Couleur)){
                        
                        if(details){
                            System.out.println("\nVerif win : " + k + " " + i + " " + joueur.Couleur);
                        }
                        
                        h = 0; v = 0; d1 = 0; d2 = 0;
                        
                        for(int x = k; (x <= k + 3) & (x < Ligne); x++){
                            
                            if(CellulesJeu[x][i].lirecouleurdujeton().equals(couleur_joueur)){
                                v++;
                                if (details){
                                    System.out.println(x + " " + i + " v : " + CellulesJeu[x][i].lirecouleurdujeton());
                                }
                            }
                        }
                            
                        for(int y = i; (y <= i + 3) & (y < Colonne); y++){
                            
                            if(CellulesJeu[k][y].lirecouleurdujeton().equals(couleur_joueur)){
                                h++;
                                if (details){
                                    System.out.println(k + " " + y + " h : " + CellulesJeu[k][y].lirecouleurdujeton());
                                }
                            }
                        }
                            
                        for(int x = k, y = i; ((x <= k + 3) & (x < Ligne)) & ((y <= i + 3) & (y < Colonne)); x++ , y++){
                            
                            if(CellulesJeu[x][y].lirecouleurdujeton().equals(couleur_joueur)){
                                d1++;
                                if (details){
                                    System.out.println(x + " " + y + " d1 : " + CellulesJeu[x][y].lirecouleurdujeton());
                                }
                            }
                        }
                        
                        for(int x = k, y = i; ((x >= k - 3) & (x > 0)) & ((y <= i + 3) & (y < Colonne)); x-- , y++){
                            
                            if(CellulesJeu[x][y].lirecouleurdujeton().equals(couleur_joueur)){
                                d2++;
                                if (details){
                                    System.out.println(x + " " + y + " d2 : " + CellulesJeu[x][y].lirecouleurdujeton());
                                }
                            }
                        }
                        
                        if (details){
                            System.out.println("\nV : " + v + "\nH : " + h + "\nD_bas : " + d1 + "\nD_haut : " + d2);
                        }
                        
                        if(h >= 4 | v >= 4 | d1 >= 4 | d2 >= 4){
                            return true;
                        }
                    }
                
                }
            }
        }
        return false;
    }
    
    public int tassergrilleparlebas(int colonne){       //refaire cette fonction mange des jetons
        Jeton dessus;
        
        for(int n = 0; n < Ligne; n++){
            
            for(int k = Ligne - 1; k > 0; k--){
                
                if(CellulesJeu[k][colonne].recupererjeton() == null){ //on part du bas et on regarde si la cellule de la ligne k est vide
                    for(int i = k; i > 0; i--){
                        dessus = CellulesJeu[i - 1][colonne].recupererjeton();
                        if(dessus != null){
                            CellulesJeu[i][colonne].affecterjeton(dessus);
                            CellulesJeu[i - 1][colonne].supprimerjeton();
                        }else{
                            CellulesJeu[i][colonne].supprimerjeton();
                        }
                    }
                }
            }
        }
        for(int k = 0; k < Ligne; k++){
            if(CellulesJeu[k][colonne].recupererjeton() != null){
                return k;
            }
        }
        return -1;
    }
    
    public boolean colonneremplie(int colonne){
        return (CellulesJeu[0][colonne].recupererjeton() != null);
    }
    
    public boolean placerdesintegrateur(int ligne, int colonne){
        if(!CellulesJeu[ligne][colonne].presencedesintegrateur()){
            CellulesJeu[ligne][colonne].placerdesintegrateur();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean placertrounoir(int ligne, int colonne){
        if(!CellulesJeu[ligne][colonne].presencetrounoir()){
            CellulesJeu[ligne][colonne].placertrounoir();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean supprimerjeton(int ligne, int colonne){
        if (celluleoccupee(ligne, colonne)){
            CellulesJeu[ligne][colonne].supprimerjeton();
            tassergrilleparlebas(colonne);
            return true;
        }else{
            return false;
        }
    }
    
    public Jeton recupererjeton(int ligne, int colonne){
        Jeton jeton = CellulesJeu[ligne][colonne].recupererjeton();
        CellulesJeu[ligne][colonne].supprimerjeton();
        tassergrilleparlebas(colonne);
        return jeton;
    }
    
    public boolean contain(Joueur joueur){
        for(int k = 0; k < Ligne; k++){
            for (int i = 0; i < Colonne; i++){
                if(CellulesJeu[k][i].lirecouleurdujeton().equals(joueur.Couleur)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public String color_texte(String couleur){
        HashMap<String, String> couleurs = new HashMap<String, String>(){{
            put("Rouge", "\033[0;31m");
            put("Jaune", "\033[0;33m");
            put("Bleu", "\033[0;34m");
            put("Vert", "\033[0;32m");
            put("Noir", "\033[0;30m");
            put("Orange", "\033[48;2;255;165;0m");
        }};
       
        return couleurs.get(couleur);
    }
    
}