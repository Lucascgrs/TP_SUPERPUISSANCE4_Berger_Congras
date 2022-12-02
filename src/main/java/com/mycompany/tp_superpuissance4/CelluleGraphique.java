/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_superpuissance4;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author lucas
 */
public class CelluleGraphique extends JButton{
    Cellule CelluleAssociee;
    ImageIcon image_vide = new javax.swing.ImageIcon(getClass().getResource("Images/celluleVide.png"));
    ImageIcon image_trounoir = new javax.swing.ImageIcon(getClass().getResource("Images/trouNoir.png"));
    ImageIcon image_desintegrateur = new javax.swing.ImageIcon(getClass().getResource("Images/desintegrateur.png"));
    ImageIcon image_jetonjaune = new javax.swing.ImageIcon(getClass().getResource("Images/jetonJaune.png"));
    ImageIcon image_jetonrouge = new javax.swing.ImageIcon(getClass().getResource("Images/jetonRouge.png"));
    ImageIcon image_vert = new javax.swing.ImageIcon(getClass().getResource("Images/jetonvert.png"));
    ImageIcon image_jetonbleu = new javax.swing.ImageIcon(getClass().getResource("Images/jetonbleu.png"));
    ImageIcon image_jetonorange = new javax.swing.ImageIcon(getClass().getResource("Images/jetonorange.png"));
    ImageIcon image_blanc = new javax.swing.ImageIcon(getClass().getResource("Images/jetonblanc.png"));
    ImageIcon image_jetonnoir = new javax.swing.ImageIcon(getClass().getResource("Images/jetonnoir.png"));
    
    public CelluleGraphique(Cellule cellule){
        CelluleAssociee = cellule;
    }
    
    @Override
    public void paintComponent(Graphics G){
        super.paintComponent(G);
        
        if(CelluleAssociee.presencetrounoir()){
            setIcon(image_trounoir);
        } else if (CelluleAssociee.presencedesintegrateur()){
            setIcon(image_desintegrateur);
        }else{
            String Couleurjeton = CelluleAssociee.lirecouleurdujeton();
            switch(Couleurjeton){
                case "Rouge":
                    setIcon(image_jetonrouge);
                    break;
                case "Jaune":
                    setIcon(image_jetonjaune);
                    break;
                case "Bleu":
                    setIcon(image_jetonbleu);
                    break;
                case "Vert":
                    setIcon(image_vert);
                    break;
                case "Noir":
                    setIcon(image_jetonnoir);
                    break;
                case "Blanc":
                    setIcon(image_blanc);
                    break;
                case "Orange":
                    setIcon(image_jetonorange);
                    break;
                case "":
                    setIcon(image_vide);
                    break;
            }
        }
    }

}