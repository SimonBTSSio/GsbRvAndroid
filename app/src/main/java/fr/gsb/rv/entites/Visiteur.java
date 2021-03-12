package fr.gsb.rv.entites;

public class Visiteur {
    private String matricule;
    private String mdp;
    private String nom;
    private String prenom;

    public Visiteur(String matricule, String mdp, String nom, String prenom) {
        this.matricule = matricule;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
