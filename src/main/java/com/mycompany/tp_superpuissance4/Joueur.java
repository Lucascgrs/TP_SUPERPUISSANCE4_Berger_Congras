/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_superpuissance4;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Joueur {
    
    String Nom;
    String Couleur;
    ArrayList<Jeton> ListeJetons = new ArrayList<Jeton>();
    int nombredesintegrateurs;
    int nombrejetonsrestants;
    
    public Joueur(String nom){
        
        Nom = nom;
        
    }
    
    public void affectercouleur(String couleur){
        Couleur = couleur;
    }
    
    public void ajouterjeton(Jeton jeton){
        ListeJetons.add(jeton);
        nombrejetonsrestants++;
    }
    
    public void obtenirdesintegrateur(){
        nombredesintegrateurs ++;
    }
    
    public boolean utiliserdesintegrateur(){ //je sais pas ce qu'il faut faire pour utiliser le désintegrateur
        if(nombredesintegrateurs > 0){
            nombredesintegrateurs --;
            return true;
        }else{
            return false;
        }
    }
    
    public Jeton poserjeton(){
        if(nombrejetonsrestants > 0){
            nombrejetonsrestants--;
            Jeton jeton = ListeJetons.get(0);
            ListeJetons.remove(0);
            return jeton;
        }
        return null;
    }
    
}
