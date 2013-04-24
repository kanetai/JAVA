package algorithm;

import java.util.Arrays;

public final class Poker {
	private Poker(){}
	//TODO: verify
	enum Suit{ Hearts, Diamonds, Clubs, Spades; }
	enum Hands{ High_Card, One_Pair, Two_Pairs, Three_of_a_Kind,
		  Straight, Flush, Full_House, Four_of_a_Kind, Straight_Flush, Royal_Straight_Flush }
	public static class Card {
		public final int rank;
		public final Suit suit;
		Card(int rank, Suit suit){ this.rank = rank; this.suit = suit; }
	}
	public static final int CYCLE = 1, R_SIZE = 13, S_SIZE = 4;
	public static Hands pokerHand(Card[] hand) {
		int[] freqRank = new int[R_SIZE], freqSuit = new int[S_SIZE];
		Arrays.fill(freqRank, 0); Arrays.fill(freqSuit, 0);
		for(Card c: hand){ ++freqRank[c.rank - 1]; ++freqSuit[c.suit.ordinal()]; }
		boolean straight = false; boolean royal = false;
		LOOP: for(int i = 0; i < R_SIZE; ++i){
			for(int j = 0; j < S_SIZE; ++j) if(freqRank[(i+j)%R_SIZE] != 1) continue LOOP;
			if(i+4 > 12+CYCLE) break; //13-1=12
			if(i == 9) royal = true;  //10-1=9
			straight = true; break;
		}
		Arrays.sort(freqRank); Arrays.sort(freqSuit);
		boolean flush = freqSuit[S_SIZE-1] == 5;
		if(straight)
			return (flush ? (royal ? Hands.Royal_Straight_Flush : Hands.Straight_Flush) : Hands.Straight);
		if(flush) return Hands.Flush;
		switch(freqRank[R_SIZE-1]){
		case 4: return Hands.Four_of_a_Kind;
		case 3: return freqRank[R_SIZE-2] == 2 ? Hands.Full_House : Hands.Three_of_a_Kind;
		case 2: return freqRank[R_SIZE-2] == 2 ? Hands.Two_Pairs : Hands.One_Pair;
		}
		return Hands.High_Card;
	}
}
