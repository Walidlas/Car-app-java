package ma.emsi.dao;

import ma.emsi.entities.Voiture;

import java.util.List;

public interface VoitureDao {
    void insert(Voiture voiture);
    void update(Voiture voiture);
    void deleteByMatricule(String matricule);
    Voiture findByMatricule(String matricule);
    List<Voiture> findAll();

}
