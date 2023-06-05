package ma.emsi.entities;

public class Voiture {
    private String matricule;
    private String marque;
    private String couleur;
    private Double prix;
    private Double kilometrage;
    private Double vitesse;

    public Voiture() {
    }

    public Voiture(String marque, String couleur, Double prix, Double kilometrage, Double vitesse) {
        this.marque = marque;
        this.couleur = couleur;
        this.prix = prix;
        this.kilometrage = kilometrage;
        this.vitesse = vitesse;
    }
    public Voiture(String matricule, String marque, String couleur, Double prix, Double kilometrage, Double vitesse) {
        this.matricule = matricule;
        this.marque = marque;
        this.couleur = couleur;
        this.prix = prix;
        this.kilometrage = kilometrage;
        this.vitesse = vitesse;
    }
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Double getVitesse() {
        return vitesse;
    }

    public void setVitesse(Double vitesse) {
        this.vitesse = vitesse;
    }

    @Override
    public String toString() {
        return matricule+"/"+marque+"/"+couleur+"/"+prix+"/"+kilometrage+"/"+vitesse;
    }
}