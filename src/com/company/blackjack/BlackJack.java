package com.company.blackjack;

import com.company.commands.BlackJackGame;
import com.company.commands.GameExit;
import com.company.main.IGame;
import com.company.module.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Класс для реализации игры BlackJack.
 */
public class BlackJack implements IGame,IBlackJack {

    @Override
    public String isGameOver(int Banker, int Player) {
        if ((Banker < 22) & (Banker > Player) | (Player > 21) & (Banker < 22)) {
            System.out.println("Банкир выиграл!");
            return String.valueOf(com.company.module.Exodus.Banker);
        } else if ((Player < 22) & (Banker < Player) | (Banker > 21) & (Player < 22)) {
            System.out.println("Игрок выиграл!");
            return String.valueOf(Exodus.Player);
        } else {
            System.out.println("Ого! Ничья!");
            return String.valueOf(Exodus.Draw);
        }
    }

    @Override
    public String isBlackJack(int sumBanker, int sumPlayer) {
        if ((sumBanker == 21) & (sumPlayer == 21)) {
            System.out.println("Вау! BlackJack у Банкира и у Игрока!");
            return String.valueOf(Exodus.BankerPlayer);
        }
        if (sumBanker == 21) {
            System.out.println("Вау! BlackJack у Банкира!");
            return String.valueOf(Exodus.Banker);
        } else if (sumPlayer == 21) {
            System.out.println("Вау! BlackJack у Игрока!");
            return String.valueOf(Exodus.Player);
        } else { return String.valueOf(Exodus.None); }
    }

    @Override
    public int countOfBank(ArrayList<Integer> bets, int bank, String exodus, String exodusSpecial) {
        int win = 0;
        bank = bank - (bets.get(0) + bets.get(1) + bets.get(2));
        switch (exodus) {
            case "Player" -> win = win + (bets.get(0) * 2);
            case "Draw" -> win = win + bets.get(0);
        }
        switch (exodusSpecial) {
            case "BankerPlayer" -> win = win + (bets.get(1) * 10) + (bets.get(2) * 10);
            case "Banker" -> win = win + (bets.get(1) * 10);
            case "Player" -> win = win + (bets.get(2) * 10);
        }
        bank += win;
        return bank;
    }

    @Override
    public void play() {
        Deck deck = new Deck();
        GameVisualizer.getInstractionGreeting();
        int bank = 10000;
        BlackJackGame.getCommand(bank);
        Stack<Card> deckStart = deck.creatingDeck();
        creatingHandsAndPlayGame(deckStart, bank);
        System.out.println("Спасибо за игру!");
        GameExit gameExit = new GameExit();
        gameExit.execute();
    }

    @Override
    public void creatingHandsAndPlayGame(Stack<Card> deckStart, int bank ){
        int numberGame = 0;
        while (!deckStart.isEmpty()) {
            CardHand handBanker = distributionOfCardsBanker(deckStart);
            CardHand handPlayer = distributionOfCardsPlayer(deckStart);
            GameVisualizer.getBet();
            Scanner in = new Scanner(System.in);
            String bet = in.nextLine();
            if (bet.equals("/exit")){
                deckStart.clear();
                break;
            }
            ArrayList<Integer> bets = Bet.TakeBet(bet, bank);
            numberGame++;
            handPlayer.distributionNewCardPlayer(numberGame, handBanker, handPlayer, deckStart);
            handBanker.distributionNewCardBanker(handBanker, handPlayer, deckStart);
            String exodus =  isGameOver(handBanker.getSum(), handPlayer.getSum());
            String exodusIsBlackJack = isBlackJack(handBanker.getSum(), handPlayer.getSum());
            int win = countOfBank(bets, bank, exodus, exodusIsBlackJack) - bank;
            if (win < 0) { win = 0; }
            System.out.println("Выигрыш: " + win + " руб.\nБанк: " + countOfBank(bets, bank, exodus, exodusIsBlackJack) + " руб.");
            bank = countOfBank(bets, bank, exodus, exodusIsBlackJack);
        }
    }

    @Override
    public CardHand distributionOfCardsPlayer(Stack<Card> deck) {
        Deck newCard = new Deck();
        Card cardPlayer1 = newCard.getCard(deck);
        Card cardPlayer2 = newCard.getCard(deck);
        CardHand handPlayer = new CardHand(new ArrayList<>(Arrays.asList(cardPlayer1, cardPlayer2)));
        return handPlayer;
    }

    @Override
    public CardHand distributionOfCardsBanker(Stack<Card> deck) {
        Deck newCard = new Deck();
        Card cardBanker1 = newCard.getCard(deck);
        Card cardBanker2 = newCard.getCard(deck);
        CardHand handBanker = new CardHand(new ArrayList<>(Arrays.asList(cardBanker1, cardBanker2)));
        return handBanker;
    }

    @Override
    public boolean doDecisionPlayer() {
        Scanner in = new Scanner(System.in);
        String takeCard = in.nextLine();
        if (takeCard.equals("/yes")) {
            return true;
        } else {
            if (takeCard.equals("/no")) {
                return false;
            } else {
                System.out.println("Не понял команду");
               doDecisionPlayer();
                return false;
            }
        }
    }

    @Override
    public boolean doDecisionBanker(int sumBanker, int sumPlayer) {
        if (sumPlayer > 21) { return false; }
        return ((sumBanker < sumPlayer) & sumBanker < 22);
    }
}