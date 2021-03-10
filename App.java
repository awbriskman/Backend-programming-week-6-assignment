import java.util.*;

class Card{
    String name;
    int value;

    public String getName() {return this.name;}
    public int getValue() {return this.value;}
    public void setName(String name) {this.name = name;}
    public void setValue(int value){this.value = value;}

    public Card (String name, int value){
        this.name = name;
        this.value = value;
    }

    public void describe(){
        System.out.println(this.name);
    }

}

class Deck{
    public List<Card> cards;

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card draw(){
        return cards.remove(0);
    }

    public Deck(){
        cards = newDeck();
    }

    private List<Card> newDeck(){
        String suit;
        String face;
        List<Card> deck = new ArrayList<Card>();
        for (int j = 0; j < 4; j++){
            switch(j){
            case 0: suit = "Clubs"; break;
            case 1: suit = "Hearts"; break;
            case 2: suit = "Spades"; break;
            case 3: suit = "Diamonds"; break;
            default: suit = "Stars"; break;
            }
            for (int i = 2; i < 15; i++){
                switch(i){
                    case 2: face = "Two"; break;
                    case 3: face = "Three"; break;
                    case 4: face = "Four"; break;
                    case 5: face = "Five"; break;
                    case 6: face = "Six"; break;
                    case 7: face = "Seven"; break;
                    case 8: face = "Eight"; break;
                    case 9: face = "Nine"; break;
                    case 10: face = "Ten"; break;
                    case 11: face = "Jack"; break;
                    case 12: face = "Queen"; break;
                    case 13: face = "King"; break;
                    case 14: face = "Ace"; break;
                    default: face = "One"; break;
                }
                Card newCard = new Card(face + " of " + suit, i);
                deck.add(newCard);
            }
        }
        return deck;
    }

}
class Player{
    int score;
    String name;
    List<Card> hand;

    public String getName() {return this.name;}
    public int getScore() {return this.score;}
    public void setName(String name) {this.name = name;}
    private void setScore(int score){this.score = score;}

    public Player(String name){
        this.name = name;
        hand = new ArrayList<Card>();
        score = 0;
    }

    public void describe(){
        for (Card card : this.hand){
            card.describe();
        }
    }

    public Card flip(){
        return this.hand.remove(0);
    }

    public void draw(Deck deck){
        this.hand.add(deck.draw());
    }

    public void incrementScore(){
        this.score++;
    }
}


public class App {
    public static void main(String[] args){
        Player one = new Player("Alice");
        Player two = new Player("Bob");
        Deck deck = new Deck();
        deck.shuffle();
        for (int i = 0; i < 52; i++){
            if (i % 2 == 0){
                one.draw(deck);
            } else{
                two.draw(deck);
            }
        }
        for (int j = 0; j < 26; j++){
            Card card1 = one.flip();
            Card card2 = two.flip();
            if(card1.getValue() > card2.getValue()){
                one.incrementScore();
            }
            if(card2.getValue() > card1.getValue()){
                two.incrementScore();
            }
        }
        if (one.getScore() > two.getScore()){
            System.out.println(one.getName() + " Wins");
        } else if (one.getScore() < two.getScore()){
            System.out.println(two.getName() + " Wins");
        } else {
            System.out.println("Draw");
        }

    }    
}
