package IA.Q2;

import java.util.ArrayList;

import net.sf.extjwnl.data.IndexWord;

public class Checker {

	public String formalize(ArrayList<String> sentence, ArrayList<IndexWord> parsing_list) {

		ArrayList<IndexWord> choose_sentence = new ArrayList<IndexWord>();

		// Lemas
		String anterior_lema = "";
		String atual_lema = "";
		String posterior_lema = "";

		// Tipos
		String anterior_type = "";
		String atual_type = "";
		String posterior_type = "";

		// Verbo auxiliar
		String verb_aux = "";

		// Strings auxiliares
		String check_everyone_pronoun = "";
		String final_sentence = "";
		String st = "";
		String finded_for_every = "";
		String check_if_then = "";

		// Indices
		int index_sentence_of = 0;
		int aux = 0;

		for (int i = 0; i < sentence.size(); i++) {
			check_everyone_pronoun = Auxiliar.check_everyone_pronoun_synonym(sentence.get(i));

			if (check_everyone_pronoun != "") {
				final_sentence += check_everyone_pronoun + ' ';
				finded_for_every = sentence.get(i);
			}

			if (sentence.get(i).toLowerCase().equals("every")) {
				final_sentence += sentence.get(i + 1) + "(p)";
			}

			if (Auxiliar.ignore(sentence.get(i))) {
				continue;
			}

			String exists = Auxiliar.check_exist(sentence.get(i));

			if (exists != "") {
				final_sentence += exists + " " + sentence.get(i + 1) + "(p)";
			}
		}

		for (int i = 0; i < (parsing_list.size() - 1); i++) {
			if (i != 0) {
				anterior_type = parsing_list.get(i - 1).getPOS().toString();
				anterior_lema = parsing_list.get(i - 1).getLemma().toString();
			}

			if (i != parsing_list.size()) {
				posterior_lema = parsing_list.get(i + 1).getLemma().toString();
				posterior_type = parsing_list.get(i + 1).getPOS().toString();
			}

			atual_type = parsing_list.get(i).getPOS().toString();
			atual_lema = parsing_list.get(i).getLemma().toString();
			String atual_sentence = "";

			try {
				if (atual_type.contains("verb")) {
					for (String element : sentence) {
						if (element.contains(atual_lema)) {
							atual_sentence = element;
							if (!element.equals(sentence.get(sentence.size() - 1))) {
								index_sentence_of = sentence.indexOf(atual_sentence);
							}
						}
					}
					if (atual_sentence.endsWith(",")) {
					} else if ((Auxiliar.check_and_conjunc_synonym(sentence.get(index_sentence_of + 1)).equals("∧"))
							|| (Auxiliar.check_or_conjunc_synonym(sentence.get(index_sentence_of + 1)).equals("v"))) {

						if (!final_sentence.contains(verb_aux + "(p," + sentence.get(index_sentence_of) + ") "
								+ Auxiliar.check_and_conjunc_synonym(sentence.get(index_sentence_of + 1))
								+ Auxiliar.check_or_conjunc_synonym(sentence.get(index_sentence_of + 1)) + ' ')) {
							st = verb_aux + "(p," + sentence.get(index_sentence_of) + ") "
									+ Auxiliar.check_and_conjunc_synonym(sentence.get(index_sentence_of + 1))
									+ Auxiliar.check_or_conjunc_synonym(sentence.get(index_sentence_of + 1)) + ' ';
							final_sentence += st;
						}
					} else if (i == parsing_list.size() - 2) {

					} else if (anterior_lema.equals(verb_aux)) {

					} else {
						if (verb_aux.isEmpty()) {
							if (final_sentence.contains("∀p")) {
								if (posterior_type.contains("verb")) {
									final_sentence += atual_lema + "(p)";
								} else {
									if (Auxiliar.ignore(sentence.get(index_sentence_of + 1))) {
										if (!sentence.get(0).toLowerCase().equals("every")) {
											final_sentence += atual_lema + "(p," + sentence.get(index_sentence_of + 2)
													+ ")";
										}
									} else {
										final_sentence += atual_lema + "(p," + sentence.get(index_sentence_of + 1)
												+ ")";
									}
								}
							}
						} else {
							if (!final_sentence.contains(" → ")) {
								final_sentence += " → ";
							}
						}
						verb_aux = atual_lema;
					}
				}
			} catch (Exception e) {
				System.out.print("Não foi possível traduzir");
			}

			try {
				if (!final_sentence.contains(" → ")) {
					check_if_then = Auxiliar.check_if_then_conjunc_synonym(sentence.get(i));
					if (check_if_then != "") {
						final_sentence += check_if_then;
					}
				}
				if (Auxiliar.check_denil(sentence.get(i)).equals("¬")) {
					final_sentence += "¬";
				}
			} catch (Exception e) {

			}

			if (i < sentence.size() - 1) {
				if (sentence.get(i).endsWith(",")) {
					String straux = "";
					straux = sentence.get(i).toString();
					straux = straux.substring(0, straux.length() - 1);
					final_sentence += verb_aux + "(" + straux + ")" + " ∧ ";
				} else if ((Auxiliar.check_and_conjunc_synonym(sentence.get(i + 1)).equals("∧"))) {
					if (!final_sentence.contains(
							verb_aux + "(p," + sentence.get(i) + ") " + Auxiliar.check_and_conjunc_synonym(sentence.get(i + 1))
									+ Auxiliar.check_or_conjunc_synonym(sentence.get(i + 1)) + ' ')) {
						final_sentence += verb_aux + "(p," + sentence.get(i) + ") "
								+ Auxiliar.check_and_conjunc_synonym(sentence.get(i + 1))
								+ Auxiliar.check_or_conjunc_synonym(sentence.get(i + 1)) + ' ';
					}

				} else if (Auxiliar.check_or_conjunc_synonym(sentence.get(i + 1)).equals("v")) {
					if (!final_sentence.contains(
							verb_aux + "(p," + sentence.get(i) + ") " + Auxiliar.check_and_conjunc_synonym(sentence.get(i + 1))
									+ Auxiliar.check_or_conjunc_synonym(sentence.get(i + 1)) + ' ')) {
						if (Auxiliar.ignore(sentence.get(i + 2))) {
							if (!final_sentence.contains(" → ")) {
								final_sentence += " → ";
							}
							final_sentence += verb_aux + "(p," + sentence.get(i) + ") "
									+ Auxiliar.check_or_conjunc_synonym(sentence.get(i + 1)) + ' ' + verb_aux + "(p,"
									+ sentence.get(i + 3) + ") ";
						}
						if (sentence.get(i + 2).equals(verb_aux)) {
							final_sentence += verb_aux + "(p," + sentence.get(i) + ") "
									+ Auxiliar.check_or_conjunc_synonym(sentence.get(i + 1)) + ' ' + verb_aux + "(p,"
									+ sentence.get(i + 3) + ") ";
						}
					}
				} else {
					// Caso contrario sai e nao faz nd
					// final_sentence += verb_aux + '(' + sentence.get(i) + ") " +
					// Auxiliar.check_and_conjunc_synonym(sentence.get(i + 1)) +
					// Auxiliar.check_or_conjunc_synonym(sentence.get(i + 1)) + ' ';
				}
			}

			if (i == (sentence.size() - 2)) {
				final_sentence += verb_aux + "(p," + sentence.get(i + 1) + ") ";
			}

			else if (atual_lema.contains(sentence.get(sentence.size() - 2))) {
				if (aux < Auxiliar.countString(sentence, sentence.get(sentence.size() - 2))) {
					aux += 1;
				} else {
					if (!final_sentence.contains(verb_aux + "(p," + sentence.get(sentence.size() - 1) + ") "))
						final_sentence += verb_aux + "(p," + sentence.get(sentence.size() - 1) + ") ";
				}
			}
		}
		System.out.print(final_sentence);
		return "s";
	}
}
