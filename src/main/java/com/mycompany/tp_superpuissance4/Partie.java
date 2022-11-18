/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_superpuissance4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lucas
 */
public class Partie {
    
    Joueur[] ListeJoueur = new Joueur[2];
    Joueur JoueurCourant;
    Grille GrilleJeu;
    ArrayList<String> colorlist = new ArrayList<String>(){{
        add("Rouge");
        add("Jaune");
        add("Bleu");
        add("Vert");
        add("noir");
        add("Blanc");
        add("Orange");
    }};
    
    public Partie(Joueur j1, Joueur j2){
        
        ListeJoueur[0] = j1;
        ListeJoueur[1] = j2;
        
    }
    
    public void initialiserpartie(int nombrejetons){
        
        if(nombrejetons % 2 != 0){
            nombrejetons++;
        }
        
        GrilleJeu = new Grille();
        JoueurCourant = ListeJoueur[0];
        Jeton jeton;
        String[] couleurs = attribuercouleursauxjoueurs();
        
        for(int k = 0; k < nombrejetons; k++){
            if (k%2 == 0){
                jeton = new Jeton(couleurs[0]);
                ListeJoueur[0].ajouterjeton(jeton);
            }else{
                jeton = new Jeton(couleurs[1]);
                ListeJoueur[1].ajouterjeton(jeton);
            }
        }
        
        //placer les trous noir et les téléporteurs
        
    }
    
    public String[] attribuercouleursauxjoueurs(){
        
        int couleur;
        String[] couleurs = new String[2];
        Random generateurAleat = new Random();
        
        for(int k = 0; k < 2; k++){
            
            couleur = generateurAleat.nextInt(colorlist.size());
            couleurs[k] = colorlist.get(k);
            ListeJoueur[k].affectercouleur(colorlist.get(couleur));
            System.out.println("Le joueur : " + ListeJoueur[k].Nom + " a la couleur " + couleur);
            colorlist.remove(couleur);
        }
        return couleurs;
    }
    
    public void debuterpartie(){
        
        int colonne;
        int nbrcoup = 0;
        int choice;
        
        while(true){

            if(nbrcoup % 2 == 0){
                JoueurCourant = ListeJoueur[0];
            }else{
                JoueurCourant = ListeJoueur[1];
            }
            
            choice = choice();
            switch(choice){
                case 0:
                    
                    break;
                case 1:
                    
                    break;
                case 2:
                    
                    break;
            }
            if(JoueurCourant.nombrejetonsrestants > 0){ //dans le cas ou l'on veut placer un pion
                colonne = asknbr("Colonne du jeton : ");
                if (!GrilleJeu.colonneremplie(colonne)){
                    GrilleJeu.ajouterjetondanscolonne(JoueurCourant.poserjeton(), colonne);
                }
            }
            
            GrilleJeu.affichergrillesurconsole();
            nbrcoup++;
            
            for(int k = 0; k < 2; k++){ //on vérifie si un joueur a gagné
                if((GrilleJeu.etregagnantpourjoueur(ListeJoueur[k], true))){
                    System.out.println("Le joueur : " + ListeJoueur[k].Nom + " a gagné !");
                    break;
                }
            }
            
            if(GrilleJeu.etreremplie()){
                System.out.println("La grille est remplie...");
                break;
            }
            
        }
        
        System.out.println("--Partie terminée--");
        
    }
    
    public int asknbr(String qst){
        Scanner sc = new Scanner(System.in);
        System.out.println(qst);
        int rep = sc.nextInt();
        return rep;
    }
    
    public int choice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        int rep = -1;
        while(rep < 0 | rep > 2){
            rep= sc.nextInt();
        }
        return rep;
    }
    
}
