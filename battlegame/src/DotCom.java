import java.util.*;

public class DotCom {
    private ArrayList<String> locationCells;
    private String name; //переменные экземпляра класса ДотКом

    public void setLocationCells(ArrayList<String> loc){ //сеттер обновляет местоположение сайта
        locationCells = loc;
    }

    public void setName(String n) {
        name = n;
    }

    public String checkYourself(String userInput){
        String result = "Мимо";
        int index = locationCells.indexOf(userInput); //Если ход пользователя совпал с одним из элементов Arraylist то метод IndexOf вернет его местоположение Если нет то -1
        if (index >= 0) {
            locationCells.remove(index);

            if (locationCells.isEmpty()) {
                result = "Потопил";
                System.out.println("Ой! Вы потопили"+ name + "  :( ");
            } else {
                result = "Попал";
            }
        }
        return result;
    }
}
