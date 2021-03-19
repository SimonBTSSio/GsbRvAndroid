package fr.gsb.rv.technique;

import fr.gsb.rv.entites.Visiteur;

public class Session {
    private static Session session = null;
    private Visiteur visiteur;

    private Session(Visiteur visiteur){
        super();
        this.visiteur = visiteur;
    }
    public static void ouvrir(Visiteur visiteur){
        if(session == null){
            session = new Session(visiteur);
        }
    }

    public Visiteur getVisiteur() {
        return visiteur;
    }

    public static Session getSession() {
        return session;
    }

    public static void fermer(){
        session = null;
    }

}
