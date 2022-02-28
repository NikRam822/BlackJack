package com.company.module;

import com.company.blackjack.BlackJack;
import com.company.blackjack.GameVisualizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Сущности: Рука с Картами.
 */
public class CardHand {
    boolean needCardIsExist = false;
    /**
     * Массив карт в руке.
     */
    ArrayList<Card> cardsInHand;

    /**
     * Конструктор для присваевания полей сущности Рука с Картами.
     * @param cardsInHand карты в руке
     */
    public CardHand(ArrayList<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    /**
     * Переопределенный метод для взятия элемента.
     * @param index индекс элемента массива
     * @return элемент массива
     */
    public Card get(int index) {
        return this.cardsInHand.get(index);
    }

    /**
     * Переопределенный метод для взятия размера массива.
     * @return размер массива
     */
    public int size() {
        return this.cardsInHand.size();
    }

    /**
     * Переопределнный метод для добавления карты в руку.
     * @param deck колода
     */
    private void addCard(Stack<Card> deck) {
        Deck newCard = new Deck();
        this.cardsInHand.add(newCard.getCard(deck));
    }

    /**
     * Метод для взятия суммы карт в руке.
     * @return сумма карт в руке
     */
    public int getSum() {
        int countA = 0;
        int sum = 0;
        for (Card value : this.cardsInHand) {
            String card = value.valueCards;
            if (card.equals("A")) { countA++; }
            sum = sum + toValueCard(card);
        }
        if (sum > 21) { sum = sum - 10 * countA; }
        return sum;
    }

    /**
     * Метод для перевода карт значения String в тип int.
     * @param card значение карты
     * @return значение карты типа int
     */
    private int toValueCard(String card) {
        return switch (card) {
            case "K", "Q", "J" -> 10;
            case "A" -> 11;
            default -> Integer.parseInt(card);
        };
    }

    /**
     * Метод для добавления карты в руку.
     * @param cardHand рука с картами
     * @param deck колода
     */
    private static void takeNewCard(CardHand cardHand, Stack<Card> deck) {
        BlackJack blackJack = new BlackJack();
        try {
            cardHand.addCard(deck);
        } catch (Exception exception) {
            System.out.println("Колода закончилась!");
            blackJack.play();
        }
    }

    /**
     * Метод для обработки решения Игрока.
     * @param numberGame порядковый номер игры
     * @param handBanker рука Банкира
     * @param handPlayer рука Игрока
     * @param deckStart  колода
     */
    public void distributionNewCardPlayer(int numberGame, CardHand handBanker, CardHand handPlayer, Stack<Card> deckStart) {
        GameVisualizer.getDistributionNumber(numberGame);
        GameVisualizer.getDistributionCloseHands(handBanker, handPlayer);
        GameVisualizer.takeNewCard();
        boolean decisionPlayer = doDecisionPlayer();
        while (decisionPlayer) {
            CardHand.takeNewCard(handPlayer, deckStart);
            GameVisualizer.getDistributionCloseHands(handBanker, handPlayer);
            GameVisualizer.takeNewCard();
            decisionPlayer = doDecisionPlayer();
        }
    }

    /**
     * Метод для обработки решения Банкира.
     * @param handBanker рука Банкира
     * @param handPlayer рука Игрока
     * @param deckStart  колода
     */
    public void distributionNewCardBanker(CardHand handBanker, CardHand handPlayer, Stack<Card> deckStart) {
        boolean decisionBanker = doDecisionBanker(handBanker.getSum(), handPlayer.getSum());
        while (decisionBanker) {
            //TODO: добавить метод жульничества. Метод будет класть нужные ему карты наверх;
            cheat(handBanker,deckStart);

            CardHand.takeNewCard(handBanker, deckStart);
            GameVisualizer.getDistributionCloseHands(handBanker, handPlayer);
            decisionBanker = doDecisionBanker(handBanker.getSum(), handPlayer.getSum());
        }
        GameVisualizer.getDistributionHands(handBanker, handPlayer);

    }

    private void cheat(CardHand handBanker, Stack<Card> deckStart) {
        int sumBanker = handBanker.getSum();//Сумма очков карт банкира.
        int sumNeed = 21 - sumBanker;//Недостающая сумма очков до 21;
        ArrayList<Card> cheatCards = new ArrayList<>();//Массив в который бдем складывать нужные для победы карты.
        while (sumNeed > 1) {//Пока сумма недостающих до 21 очков,больше 1,мы ищем в колоде нужные карты,сумма очков которых,даст нам 21,и кладем их в массив.
           sumNeed =  rearrangementOfCardsInTheDeckIfNeedCardIsExist(deckStart,handBanker,sumNeed,cheatCards);//метод rearrangementOfCardsInTheDeckIfNeedCardIsExist, реализует перестановку нужных для достижения суммы 21 карт и возвращает недостающую сумму с учетом значения переставленной карты.
            if (!needCardIsExist) { sumNeed =rearrangementOfCardsInTheDeck(deckStart,handBanker,sumNeed,cheatCards);break; } }//Аналогичный метод применяемый  в случае, если нужной карты для достижения 21, в колоде нет.В этом случае метод rearrangementOfCardsInTheDeck положит в массив cheatCards карту,с наиболее подходящим занчением,для достижения 21.
        for (Card cheatCard : cheatCards) { deckStart.push(cheatCard); }//Добавление нужных карт из массива cheatCards на верх колоды.
    }


private int rearrangementOfCardsInTheDeck(Stack<Card> deckStart,CardHand handBanker,int sumNeed,ArrayList<Card> cheatCards){
    for (int i = 0; i < deckStart.size(); i++) {
        if (handBanker.getSum() == 21) {
            break;
        }
        if (toValueCard(deckStart.get(i).valueCards) < sumNeed) {
            cheatCards.add(deckStart.get(i));
            sumNeed = sumNeed - toValueCard(deckStart.get(i).valueCards);
            deckStart.remove(i);
            break;
        }
    }
    return sumNeed;
}


    private int rearrangementOfCardsInTheDeckIfNeedCardIsExist(Stack<Card> deckStart,CardHand handBanker,int sumNeed,ArrayList<Card> cheatCards){
        for (int i = 0; i < deckStart.size(); i++) {
            if (handBanker.getSum() == 21) {
                break;
            }
            if (toValueCard(deckStart.get(i).valueCards) == sumNeed) {
                needCardIsExist = true;
                cheatCards.add(deckStart.get(i));
                sumNeed = sumNeed - toValueCard(deckStart.get(i).valueCards);
                deckStart.remove(i);
                break;
            }
        }
        return sumNeed;
    }
    /**
     * Метод принятия решения Игрока.
     * @return решение о взятие карты
     */
    private boolean doDecisionPlayer() {
        Scanner in = new Scanner(System.in);
        String takeCard = in.nextLine();
        if (takeCard.equals("/yes")) {
            return true;
        } else {
            if (!takeCard.equals("/no")) {
                System.out.println("Не понял команду!");
                doDecisionPlayer();
            }
            return false;
        }
    }

    /**
     * Метод принятия решения Банкира.
     * @param sumBanker сумма Банкира
     * @param sumPlayer сумма Игрока
     * @return решение о взятие карты
     */
    private boolean doDecisionBanker(int sumBanker, int sumPlayer) {
       // if (sumPlayer > 21) { return false; }
        if(sumBanker==20){return false;}
        if(sumBanker>21 ){return false;}
        //return (/*(sumBanker < sumPlayer) &*/ sumBanker < 22);
        return (sumBanker!=21);
    }
}