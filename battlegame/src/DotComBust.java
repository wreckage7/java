

import java.util.*;

public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComList = new ArrayList<DotCom>(); //Массив только для объектов ДотКом
    private int numOfGuesses = 0;

    private void setUpGame() {
        // Создаем сайты
        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        one.setName("eToys.com");
        DotCom three = new DotCom();
        one.setName("Go2.com");
        dotComList.add(one);
        dotComList.add(two);
        dotComList.add(three);

        System.out.println("Ваша цель - потопить три сайта");

        for (DotCom dotComToSet : dotComList) { //Повторяем с каждым объектом в списке
            ArrayList<String> newLocation = helper.placeDotCom(3); //Запрашиваем у вспомогательного объекта адрес сайта

            dotComToSet.setLocationCells(newLocation);
            //Вызываем сеттер из объекта ДотКом чтобы передать ему положение которое только что получили от вспом. объекта
        }
    }
    private void startPlaying() {
        while(!dotComList.isEmpty()) { //пока список объектов не станет пустым
            String userGuess = helper.getUserInput ("Сделайте ход");
            checkUserGuess(userGuess); //вызываем наш метод
        }
        finishGame(); //вызываем метод Финиш гейм
    }
    private void checkUserGuess(String userGuess) {
        numOfGuesses++;

        String result = "Мимо";

        for (DotCom dotComToTest : dotComList) {
            result = dotComToTest.checkYourself(userGuess); //прросим ДотКом проверить ход пользователя, ищем попадание

            if (result.equals("Попал")) {
                break;
            }
if (result.equals("Потопил")){
                dotComList.remove(dotComToTest); //удаляем из сайтов
    break;
}

        }
        System.out.println(result);
    }
    private void finishGame() {
        System.out.println("Все сайты ушли ко дну!");
        if (numOfGuesses <= 18){
            System.out.println("Это заняло у вас" + numOfGuesses + "попыток");
            System.out.println("Вы успели выбраться");

        }else{
            System.out.println("Это заняло много времени");
            System.out.println("Рыбы водят хороводы вокруг вас");
        }
    }
    public static void main (String[] args){
        DotComBust game = new DotComBust(); //создаем игровой объект
        game.setUpGame(); //подготовить игру
        game.startPlaying(); //начинаем главный игровой цикл
    }

}
