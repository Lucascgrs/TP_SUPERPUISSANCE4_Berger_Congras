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
    ImageIcon image_vide = new javax.swing.ImageIcon(getClass().getResource("/Images/celluleVide.png"));
    
    public CelluleGraphique(Cellule cellule){
        CelluleAssociee = cellule;
    }
    
    @Override
    public void paintComponent(Graphics G){
        super.paintComponent(G);
        setIcon(image_vide);
    }
}
