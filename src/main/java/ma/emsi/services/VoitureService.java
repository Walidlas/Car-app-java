package ma.emsi.services;

import ma.emsi.dao.VoitureDao;
import ma.emsi.dao.impl.VoitureDaoImpl;
import ma.emsi.entities.Voiture;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class VoitureService {
    private VoitureDao voitureDao = new VoitureDaoImpl();
    public List<Voiture> findAll() {
        return voitureDao.findAll();
    }
    public void save(Voiture voiture) {
        voitureDao.insert(voiture);
    }
    public void update(Voiture voiture) {
        voitureDao.update(voiture);
    }
    public void findByMat(String matricule) { voitureDao.findByMatricule(matricule); }
    public Voiture findByMatt(String matricule) { return voitureDao.findByMatricule(matricule); }

    public List<Voiture> extractTXT(String path)throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        List<Voiture> liste = new ArrayList<>();
        String readLine = bufferedReader.readLine();
        while(readLine != null){
            String [] voitureData = readLine.split("/");
            Voiture voiture = new Voiture();
            voiture.setMatricule(voitureData[0].trim());
            voiture.setMarque(voitureData[1].trim());
            voiture.setCouleur(voitureData[2].trim());
            voiture.setPrix(Double.valueOf(voitureData[3].trim()));
            voiture.setKilometrage(Double.valueOf(voitureData[4].trim()));
            voiture.setVitesse(Double.valueOf(voitureData[5].trim()));
            liste.add(voiture);
            Voiture d = voitureDao.findByMatricule(voiture.getMatricule());
            if(d != null){
                System.out.println("exists");
                voitureDao.update(voiture);
            }
            else{
                System.out.println("nn hh");
                voitureDao.insert(voiture);
            }
            readLine = bufferedReader.readLine();
        }
        return liste;
    }
    public void writeTXT(String path) throws Exception {
        List<Voiture> voitures = voitureDao.findAll();
        try (PrintWriter writer = new PrintWriter(new File(path))) {
            for (Voiture voiture : voitures) {
                writer.println(voiture.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void remove(String matricule) {
        voitureDao.deleteByMatricule(matricule);
    }
    public void extractXLSX(String path) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        List<Voiture> voitures = new ArrayList<>();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Voiture voiture = new Voiture();
            voiture.setMatricule(cellIterator.next().getStringCellValue());
            try{
                voiture.setMarque(cellIterator.next().getStringCellValue());
            }
            catch (Exception exception){
                break;
            }
            voiture.setCouleur(cellIterator.next().getStringCellValue());
            voiture.setPrix(cellIterator.next().getNumericCellValue());
            voiture.setKilometrage(cellIterator.next().getNumericCellValue());
            voiture.setVitesse(cellIterator.next().getNumericCellValue());
            voitures.add(voiture);
            Voiture d = voitureDao.findByMatricule(voiture.getMatricule());
            if(d != null){
                System.out.println("exists");
                voitureDao.update(voiture);
            }
            else{
                System.out.println("nn hh");
                voitureDao.insert(voiture);
            }
        }
    }

    public void writeXLSX(String path) throws Exception {
        List<Voiture> voitures = voitureDao.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Dishes Info");

        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Matricule;");
        headerRow.createCell(1).setCellValue("Marque");
        headerRow.createCell(2).setCellValue("Couleur");
        headerRow.createCell(3).setCellValue("Prix");
        headerRow.createCell(4).setCellValue("Kilometrage");
        headerRow.createCell(5).setCellValue("Vitesse");

        int rowNum = 1;
        for (Voiture voiture : voitures) {
            XSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(voiture.getMatricule());
            row.createCell(1).setCellValue(voiture.getMarque());
            row.createCell(2).setCellValue(voiture.getCouleur());
            row.createCell(3).setCellValue(voiture.getPrix());
            row.createCell(4).setCellValue(voiture.getKilometrage());
            row.createCell(5).setCellValue(voiture.getVitesse());
        }

        FileOutputStream outputStream = new FileOutputStream(new File(path));
        workbook.write(outputStream);
        outputStream.close();
    }

    static XSSFRow row;
}
