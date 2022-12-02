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
        add("Noir");
        add("Blanc");
        add("Orange");
    }};
    
    public Partie(Joueur j1, Joueur j2){
        
        ListeJoueur[0] = j1;
        ListeJoueur[1] = j2;
        
    }
    
    public void initialiserpartie(int nombrejetons){
        
        int Ligne = 6;
        int Colonne = 7;
        int TrousNoir = 5;
        int Desintegrateurs = 3;
        int DesintegrateursTrouNoir = 2;
        ArrayList<Cellule> ListeTrousNoir = new ArrayList<>();
        
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
        
        Random generateurAleat = new Random();
        int x, y;
        while(TrousNoir > 0){
            x = generateurAleat.nextInt(Ligne);
            y = generateurAleat.nextInt(Colonne);
            if(GrilleJeu.CellulesJeu[x][y].placertrounoir()){
                ListeTrousNoir.add(GrilleJeu.CellulesJeu[x][y]);
                TrousNoir--;
            }
        }
        
        while(DesintegrateursTrouNoir > 0){
            ListeTrousNoir.get(generateurAleat.nextInt(ListeTrousNoir.size())).placerdesintegrateur();
            DesintegrateursTrouNoir--;
        }
        
        while(Desintegrateurs > 0){
            x = generateurAleat.nextInt(Ligne);
            y = generateurAleat.nextInt(Colonne);
            if(!GrilleJeu.CellulesJeu[x][y].presencetrounoir()){
                if(GrilleJeu.CellulesJeu[x][y].placerdesintegrateur()){
                    Desintegrateurs--;
                }
            }
        }
        
    }
    
    public String[] attribuercouleursauxjoueurs(){
        
        int couleur;
        String[] couleurs = new String[2];
        Random generateurAleat = new Random();
        
        for(int k = 0; k < 2; k++){
            
            couleur = generateurAleat.nextInt(colorlist.size());
            couleurs[k] = colorlist.get(couleur);
            ListeJoueur[k].affectercouleur(colorlist.get(couleur));
            System.out.println("Le joueur : " + ListeJoueur[k].Nom + " a la couleur " + colorlist.get(couleur));
            colorlist.remove(couleur);
        }
        return couleurs;
    }
    
    public void debuterpartie(){
        
        int nbrcoup = 0;
        int choice;
        Joueur winner;
        ArrayList<Joueur> winners = new ArrayList<Joueur>();
        
        while(true){

            if(nbrcoup % 2 == 0){
                JoueurCourant = ListeJoueur[0];
            }else{
                JoueurCourant = ListeJoueur[1];
            }
            
            System.out.println("C'est à " + JoueurCourant.Nom + " de jouer");
            
            choice = choice();
            switch(choice){ //Le joueur courant joue
                case 0:
                    jouerjeton();
                    break;
                case 1:
                    recupererjeton();
                    break;
                case 2:
                    jouerdesintegrateur();
                    break;
            }
            
            GrilleJeu.affichergrillesurconsole();
            nbrcoup++;
            
            winners = whoswin();
            int size = winners.size();
            if(size > 0){
                if(size == 2 | (size == 1 & winners.get(0) != JoueurCourant)){
                    if(ListeJoueur[0] == JoueurCourant){
                        winner = ListeJoueur[1];
                    }else{
                        winner = ListeJoueur[0];
                    }
                }
                if(size == 1 & winners.get(0) == JoueurCourant){
                    winner = JoueurCourant;
                }
                break;
                
            }else{
                winner = null;
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
        int rep = -1;
        while((rep < 0 | rep > 2) | (rep == 2 & JoueurCourant.nombredesintegrateurs < 1) | (rep == 1 & !GrilleJeu.contain(JoueurCourant))){
            System.out.println("0 -> Poser jeton\n1 -> Récupérer Jeton\n2 -> Jouer Désintégrateur");
            rep= sc.nextInt();
            
            if(rep == 2 & JoueurCourant.nombredesintegrateurs < 1){
                System.out.println("Vous ne possédez pas de désintégrateurs");
            }
            
            if(rep == 1 & !GrilleJeu.contain(JoueurCourant)){
                System.out.println("Vous n'avez pas de jeton à récupérer");
            }
            
        }
        return rep;
    }
    
    public ArrayList<Joueur> whoswin(){
        ArrayList<Joueur> winners = new ArrayList<Joueur>();
        for(int k = 0; k < 2; k++){
            if((GrilleJeu.etregagnantpourjoueur(ListeJoueur[k], false))){
                winners.add(ListeJoueur[k]);
                //System.out.println("Le joueur : " + ListeJoueur[k].Nom + " a gagné !");
            }
        }
        return winners;
    }
    
    public void jouerjeton(){
        int colonne = -1;
        int lignejeton;
        
        if(JoueurCourant.nombrejetonsrestants > 0){ //dans le cas ou l'on veut et peut placer un pion
            
            while(colonne < 0 | colonne > 6){
                colonne = asknbr("Colonne du jeton : ");
            }
            
            if (!GrilleJeu.colonneremplie(colonne)){
                
                lignejeton = GrilleJeu.ajouterjetondanscolonne(JoueurCourant.poserjeton(), colonne);
                if(lignejeton != -1){
                    if(GrilleJeu.CellulesJeu[lignejeton][colonne].presencetrounoir()){
                        GrilleJeu.CellulesJeu[lignejeton][colonne].activertrounoir();
                    }

                    if(GrilleJeu.CellulesJeu[lignejeton][colonne].presencedesintegrateur()){
                        GrilleJeu.CellulesJeu[lignejeton][colonne].recupererdesintegrateur();
                    }
                }
            }
        }
    }
    
    public void recupererjeton(){
        int x = -1;
        int y = -1;
        
        do{
            x = asknbr("Ligne : ");
            y = asknbr("Colonne : ");}
        while(GrilleJeu.recupererjeton(x, y) == null);
        
        Jeton jeton = GrilleJeu.recupererjeton(x, y);
        JoueurCourant.ajouterjeton(jeton);
    }
    
    public void jouerdesintegrateur(){
        int x = -1;
        int y = -1;
        if(JoueurCourant.nombredesintegrateurs > 0){
            do{
                System.out.println("Les coordonnées du point que vous voulez désintégerer : ");
                x = asknbr("Ligne : ");
                y = asknbr("Colonne : ");}
            while(GrilleJeu.recupererjeton(x, y) == null);
            if(!GrilleJeu.CellulesJeu[x][y].lirecouleurdujeton().equals(JoueurCourant.Couleur) & GrilleJeu.celluleoccupee(x, y)){
                GrilleJeu.supprimerjeton(x, y);
            }
        }
    }
    
}
