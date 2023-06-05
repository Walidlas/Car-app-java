package ma.emsi;

import ma.emsi.entities.Voiture;
import ma.emsi.services.VoitureService;



public class Main {
    public static void main(String[] args) throws Exception {


        VoitureService voitureService = new VoitureService();
//        for(Dish dish : dishService.findAll())
//            System.out.println(dish);
//        dishService.writeJSON();
//        dishService.remove(5);
//        dishService.save(new Dish("Fried Rice", 400, "Chinese", "Rice stir-fried with egg, vegetables, and a choice of meat or tofu", LUNCH, false));
//        for(Dish dish : dishService.findAll())
//            System.out.println(dish);
//        dishService.update(new Dish(5, "Fried Lice", 400, "Chinese", "Rice stir-fried with egg, vegetables, and a choice of meat or tofu", LUNCH, false));
//        for(Dish dish : dishService.findAll())
//            System.out.println(dish);
//        for(Dish dish : dishService.findAll())
//            System.out.println(dish);
//        dishService.writeJSON();
//        System.out.println(dishService.extractXLSX());
//        System.out.println("hh");
//        dishService.extractXLSX("src/main/resources/inputDataExcel.xlsx");
//        dishService.writeJSON("src/main/resources/outputDataJson.txt");
        voitureService.writeTXT("src/main/resources/outputData.txt");
//        dishService.extractTXT("src/main/resources/inputData.txt");
    }
}