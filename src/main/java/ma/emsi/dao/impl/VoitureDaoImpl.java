package ma.emsi.dao.impl;

import ma.emsi.dao.VoitureDao;
import ma.emsi.entities.Voiture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VoitureDaoImpl implements VoitureDao {
    private Connection connection= DB.getConnection();
    public void insert(Voiture voiture) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Voiture (matricule, marque, couleur, prix, kilometrage, vitesse) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, voiture.getMatricule());
            preparedStatement.setString(2, voiture.getMarque());
            preparedStatement.setString(3, voiture.getCouleur());
            preparedStatement.setDouble(4, voiture.getPrix());
            preparedStatement.setDouble(5, voiture.getKilometrage());
            preparedStatement.setDouble(6, voiture.getVitesse());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(Voiture voiture) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Voiture SET matricule = ?, marque = ?, couleur = ?, prix = ?, kilometrage = ?, vitesse = ? WHERE matricule = ?")) {
            preparedStatement.setString(1, voiture.getMatricule());
            preparedStatement.setString(2, voiture.getMarque());
            preparedStatement.setString(3, voiture.getCouleur());
            preparedStatement.setDouble(4, voiture.getPrix());
            preparedStatement.setDouble(5, voiture.getKilometrage());
            preparedStatement.setDouble(6, voiture.getVitesse());
            preparedStatement.setString(7, voiture.getMatricule());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByMatricule(String matricule) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Voiture WHERE matricule = ?")) {
            preparedStatement.setString(1, matricule);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Voiture findByMatricule(String matricule) {
        Voiture voiture = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Voiture WHERE matricule = ?")) {
            preparedStatement.setString(1, matricule);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                voiture = new Voiture();
                voiture.setMatricule(resultSet.getString("matricule"));
                voiture.setMarque(resultSet.getString("marque"));
                voiture.setCouleur(resultSet.getString("couleur"));
                voiture.setPrix(resultSet.getDouble("prix"));
                voiture.setKilometrage(resultSet.getDouble("kilometrage"));
                voiture.setVitesse(resultSet.getDouble("vitesse"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voiture;
    }

    @Override
    public List<Voiture> findAll() {
        List<Voiture> voitures = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Voiture");
            while (resultSet.next()) {
                Voiture voiture = new Voiture();
                voiture.setMatricule(resultSet.getString("matricule"));
                voiture.setMarque(resultSet.getString("marque"));
                voiture.setCouleur(resultSet.getString("couleur"));
                voiture.setPrix(resultSet.getDouble("prix"));
                voiture.setKilometrage(resultSet.getDouble("kilometrage"));
                voiture.setVitesse(resultSet.getDouble("vitesse"));
                voitures.add(voiture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voitures;
    }


}
