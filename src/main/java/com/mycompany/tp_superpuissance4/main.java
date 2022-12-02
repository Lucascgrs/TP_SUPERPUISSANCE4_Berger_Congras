/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_superpuissance4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 * 6 lignes et 7 colonnes
 */
public class main {
    
    public static void main(String[] args){
        
        Joueur j1 = new Joueur("Lucas");
        Joueur j2 = new Joueur("Lea");
        
        Partie Partie = new Partie(j1, j2);

        Partie.initialiserpartie(30);
        String c = Partie.GrilleJeu.color_texte("Rouge");
        Partie.GrilleJeu.affichergrillesurconsole();
        Partie.debuterpartie();
        
    }
 
}
