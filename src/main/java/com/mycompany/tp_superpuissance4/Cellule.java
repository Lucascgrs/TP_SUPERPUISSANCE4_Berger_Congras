/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_superpuissance4;

/**
 *
 * @author lucas
 */
public class Cellule {
    
    Jeton JetonCourant;
    boolean TrouNoir;
    boolean Desintegrateur;
    
    public Cellule(){
        
        
        
    }
    
    public boolean affecterjeton(Jeton jeton){
        if(JetonCourant == null){
            JetonCourant = jeton;
            return true;
        }else{
            return false;
        }
    }
    
    public Jeton recupererjeton(){
        return JetonCourant;
    }
    
    public boolean supprimerjeton(){
        if(JetonCourant != null){
            JetonCourant = null;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean placertrounoir(){
        if(!presencetrounoir()){
            TrouNoir = true;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean placerdesintegrateur(){
        if (!presencedesintegrateur()){
            Desintegrateur = true;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean presencetrounoir(){
        return TrouNoir;
    }
    
    public boolean presencedesintegrateur(){
        return Desintegrateur;
    }
    
    public String lirecouleurdujeton(){
        if(JetonCourant != null){
            return JetonCourant.lirecouleur();
        }else{
            return "";
        }
    }
    
    public boolean recupererdesintegrateur(){
        if (Desintegrateur){
            Desintegrateur = false;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean activertrounoir(){
        if (TrouNoir){
            if(supprimerjeton()){
                TrouNoir = false;
                return true;
            }
        }
        return false;
    }
    
    public boolean afficherdesintegrateur(){
        return (Desintegrateur & !TrouNoir);
    }
    
}