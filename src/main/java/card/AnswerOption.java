package main.java.card;

public enum AnswerOption {
	A,
	B,
	C,
	D,
	MAJORITY_RULES;
	
	@Override
	public String toString() {
		switch(this) {
			case A: return "a";
			case B: return "b";
			case C: return "c";
			case D: return "d";
			case MAJORITY_RULES: return "MAJORITY RULES";
			default: throw new IllegalArgumentException();
				
		}
	}
}
