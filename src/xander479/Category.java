package xander479;

public enum Category {
	ONES {

		@Override
		public int getScore(Die[] dice) {
			int score = 0;
			for(Die die : dice) {
				if(die.getValue() == 1) score += 1;
			}
			return score;
		}
	},
	TWOS {

		@Override
		public int getScore(Die[] dice) {
			int score = 0;
			for(Die die : dice) {
				if(die.getValue() == 2) score += 2;
			}
			return score;
		}	
	},
	THREES {

		@Override
		public int getScore(Die[] dice) {
			int score = 0;
			for(Die die : dice) {
				if(die.getValue() == 3) score += 3;
			}
			return score;
		}
	},
	FOURS {

		@Override
		public int getScore(Die[] dice) {
			int score = 0;
			for(Die die : dice) {
				if(die.getValue() == 4) score += 4;
			}
			return score;
		}
	},
	FIVES {

		@Override
		public int getScore(Die[] dice) {
			int score = 0;
			for(Die die : dice) {
				if(die.getValue() == 5) score += 5;
			}
			return score;
		}
	},
	SIXES {

		@Override
		public int getScore(Die[] dice) {
			int score = 0;
			for(Die die : dice) {
				if(die.getValue() == 6) score += 6;
			}
			return score;
		}
	},
	CHOICE {

		@Override
		public int getScore(Die[] dice) {
			int score = 0;
			for(Die die : dice) score += die.getValue();
			return score;
		}
	},
	FULL_HOUSE {

		@Override
		public int getScore(Die[] dice) {
			int score = 0;
			
			/* The dice array should always be sorted so this checks that the first two dice
			 *  are equal, and that the last two are equal. Then makes sure that the centre die
			 *  is equal to the first or last die, but not both (that would be a yacht instead)
			 */
			if(dice[0].equals(dice[1]) && dice[3].equals(dice[4])) {
				if(dice[2].equals(dice[0]) ^ dice[2].equals(dice[4])) {
					for(Die die : dice) score += die.getValue();
				}
			}
			return score;
		}
	},
	FOUR_OF_A_KIND {

		@Override
		public int getScore(Die[] dice) {
			int score = 0;
			int matchesFirst = 0, matchesLast = 0;
			for(Die die : dice) {
				score += die.getValue();
				
				/* The dice array should always be sorted so this counts how many dice match the first
				 * in the array, and how many match the last. A 'four of a kind' should only ever have
				 * either the first die or the last be different to the rest.
				 */
				if(die.equals(dice[0])) matchesFirst++;
				if(die.equals(dice[4])) matchesLast++;
			}
			if(matchesFirst >= 4 || matchesLast >= 4) return score;
			return 0; 
		}
	},
	S_STRAIGHT {

		@Override
		public int getScore(Die[] dice) {
			if(dice[0].getValue() > 3) return 0;
			
			int previous = dice[0].getValue();
			int breaks = 0;
			for(int i = 1; i < dice.length; i++) {
				if(dice[i].getValue() != previous + 1) breaks++;
				previous = dice[i].getValue();
			}
			if(breaks > 1) return 0;
			return 15;
		}
	},
	L_STRAIGHT {

		@Override
		public int getScore(Die[] dice) {
			if(dice[0].getValue() > 2) return 0;
			
			int previous = dice[0].getValue();
			for(int i = 1; i < dice.length; i++) {
				if(dice[i].getValue() != previous + 1) return 0; 
			}
			return 30;
		}
	},
	YACHT {

		@Override
		public int getScore(Die[] dice) {
			for(Die die : dice) {
				if(!(die.equals(dice[0]))) return 0;
			}
			return 50;
		}
	};
	
	private boolean isUsed;
	public void choose() {
		this.isUsed = true;
	}
	
	public boolean isUsed() {
		return isUsed;
	}
	
	public static boolean isFull() {
		for(Category cat : values()) {
			if(!cat.isUsed()) return false;
		}
		return true;
	}
	
	public abstract int getScore(Die[] dice);
}
