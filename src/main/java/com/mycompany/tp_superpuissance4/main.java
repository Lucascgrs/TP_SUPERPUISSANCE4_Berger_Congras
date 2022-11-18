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
        
        Partie.initialiserpartie(42);
        Partie.GrilleJeu.affichergrillesurconsole();
        Partie.debuterpartie();
        /*
        List<Integer> l = new ArrayList<Integer>();
        for (int k = 1; k <= 10; k++){
            l.add(k);
        }
        l.set(8, 0);
        System.out.println(l.toString());
        int tmp = 0;
        int tmp2 = 0;
        boolean cond = false;
        for(int i = 0; i < l.size(); i++){
            tmp = l.get(i);
            if(tmp != 0){
                cond = true;
            }
            l.set(i, tmp2);
            tmp2 = tmp;
            System.out.println(l.toString());
            if(l.get(i + 1) == 0 & cond){
                l.set(i + 1, tmp2);
                System.out.println(l.toString());
                break;
            }
        }*/
        
    }
 
}
