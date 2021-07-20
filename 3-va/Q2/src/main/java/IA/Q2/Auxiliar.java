package IA.Q2;

import java.util.ArrayList;

// Metodos auxiliares
public class Auxiliar {
	
	static public String check_or_conjunc_synonym(String word) {
		ArrayList<String> frequent_conjs = new ArrayList<String>();
		frequent_conjs.add("or");
		if (frequent_conjs.contains(word.toLowerCase())) {
			return "v";
		}

		return "";
	}

	static public String check_and_conjunc_synonym(String word) {
		ArrayList<String> frequent_conjs = new ArrayList<String>();
		frequent_conjs.add("and");
		if (frequent_conjs.contains(word.toLowerCase())) {
			return "∧";
		}

		return "";

	}

	static public String check_if_then_conjunc_synonym(String word) {
		ArrayList<String> frequent_conjs = new ArrayList<String>();
		frequent_conjs.add("whether");
		frequent_conjs.add("if");
		frequent_conjs.add("then");
		frequent_conjs.add("is");
		frequent_conjs.contains(word.toLowerCase());
		
		if (frequent_conjs.contains(word.toLowerCase())) {
			return " → ";
		}

		return "";

	}

	static public String check_everyone_pronoun_synonym(String word) {
		ArrayList<String> frequent_conjs = new ArrayList<String>();

		frequent_conjs.add("everyone");
		frequent_conjs.add("every");
		frequent_conjs.add("everybody");
		frequent_conjs.add("everyday");
		frequent_conjs.add("everyone");
		frequent_conjs.add("everything");
		frequent_conjs.add("everywhere");
		frequent_conjs.add("everywoman");
		frequent_conjs.add("everyman");
		frequent_conjs.add("anyone");
		
		if (frequent_conjs.contains(word.toLowerCase())) {
			return "∀p";
		}

		return "";
	}

	static public String check_exist(String word) {
		ArrayList<String> frequent_conjs = new ArrayList<String>();

		frequent_conjs.add("exist");
		frequent_conjs.add("there are");
		frequent_conjs.add("there is");
		frequent_conjs.add("exists");

		if (frequent_conjs.contains(word.toLowerCase())) {
			return "∃p";
		}

		return "";
	}

	static public String check_denil(String word) {
		ArrayList<String> frequent_conjs = new ArrayList<String>();

		frequent_conjs.add("dont");
		frequent_conjs.add("don't");
		frequent_conjs.add("does not");
		frequent_conjs.add("doesn't");
		frequent_conjs.add("not");
		frequent_conjs.add("no");
		frequent_conjs.add("nothing");
		frequent_conjs.add("noway");

		if (frequent_conjs.contains(word.toLowerCase())) {
			return "¬";
		}

		return "";
	}

	static public boolean ignore(String word) {
		ArrayList<String> frequent_conjs = new ArrayList<String>();

		frequent_conjs.add("who");
		frequent_conjs.add("what");
		frequent_conjs.add("when");
		frequent_conjs.add("how");
		frequent_conjs.add("whose");
		frequent_conjs.add("where");
		frequent_conjs.add("a");
		frequent_conjs.add("an");
		frequent_conjs.add("the");
		frequent_conjs.add("any");

		if (frequent_conjs.contains(word.toLowerCase())) {
			return true;
		}

		return false;
	}

	static public boolean check_negation(String word) {
		ArrayList<String> frequent_conjs = new ArrayList<String>();

		frequent_conjs.add("not");
		frequent_conjs.add("no");
		frequent_conjs.add("doesn't");
		frequent_conjs.add("does not");

		if (frequent_conjs.contains(word.toLowerCase())) {
			return true;
		}

		return false;
	}

	static public int countString(ArrayList<String> sentence, String l) {
		int count = 0;

		for (int i = 0; i < sentence.size(); i++) {
			if (sentence.get(i).equals(l))
				count++;
		}

		return count;
	}

}
