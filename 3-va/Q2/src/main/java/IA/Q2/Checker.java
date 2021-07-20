package IA.Q2;

import java.util.ArrayList;

import net.sf.extjwnl.data.IndexWord;

public class Checker {

	public String check_or_conjunc_synonym(String word) {
		ArrayList<String> frequent_conjs = new ArrayList<String>();
		frequent_conjs.add("or");
		if (frequent_conjs.contains(word.toLowerCase())) {
			return "v";
		}
		return "";
	}

	public String check_and_conjunc_synonym(String word) {
		ArrayList<String> frequent_conjs = new ArrayList<String>();
		frequent_conjs.add("and");
		if (frequent_conjs.contains(word.toLowerCase())) {
			return "∧";
		}
		return "";

	}

	public String check_if_then_conjunc_synonym(String word) {
		ArrayList<String> frequent_conjs = new ArrayList<String>();
		frequent_conjs.add("whether");
		frequent_conjs.add("if");
		frequent_conjs.add("then");
		frequent_conjs.add("is");
		frequent_conjs.contains(word.toLowerCase());
		if (frequent_conjs.contains(word.toLowerCase())) {
			return  " → ";
		}
		return "";
		
	}

	public String check_everyone_pronoun_synonym(String word) {
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
	

	public String check_exist(String word) {
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
	
	public String check_denil(String word) {
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

	public boolean ignore(String word) {
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
	
	public boolean check_negation(String word) {
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
	public int countString(ArrayList<String> sentence, String l)
	{
	    int count = 0;

	    for(int i=0; i < sentence.size(); i++)
	    {    if(sentence.get(i).equals(l))
	            count++;
	    }

	    return count;
	}
	public String translate(ArrayList<String> sentence, ArrayList<IndexWord> parsing_list) {

		ArrayList<IndexWord> choose_sentence = new ArrayList<IndexWord>();
		String anterior_lema = "";
		String atual_lema = "";
		String posterior_lema = "";
		String anterior_type = "";
		String atual_type = "";
		String posterior_type = "";
		String verb_aux = "";
		String check_everyone_pronoun = "";
		int index_sentence_of = 0;
		String final_sentence = "";
		String st = "";
		String finded_for_every = "";
		String check_if_then = "";
		int aux = 0;
//		choose_sentence.add(parsing_list.get(0));

		System.out.print("\n\n");

		for(int i=0; i<sentence.size(); i++){
//			
			check_everyone_pronoun = check_everyone_pronoun_synonym(sentence.get(i));
			if(check_everyone_pronoun != "") {
				final_sentence += check_everyone_pronoun + ' ';
				finded_for_every = sentence.get(i);
			}
			if (sentence.get(i).toLowerCase().equals("every")) {
				final_sentence += sentence.get(i + 1) + "(p)";
			}
			if(ignore(sentence.get(i))) {
				continue;
			}
			
			String exists = check_exist(sentence.get(i));
			if(exists !="") {
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
//			if(ignore(atual_lema)) {
//				continue;
//			}
			try {
				if (atual_type.contains("verb")) {


					for (String element : sentence) {
						if (element.contains(atual_lema)) {
							atual_sentence = element;
							if (!element.equals(sentence.get(sentence.size() - 1))) {
								index_sentence_of = sentence.indexOf(atual_sentence);
//								System.out.print("\n --- " + index_sentence_of + " --- \n");
							}
						}

					}
					if (atual_sentence.endsWith(",")) {
					}

					else if ((check_and_conjunc_synonym(sentence.get(index_sentence_of + 1)).equals("∧"))
							|| (check_or_conjunc_synonym(sentence.get(index_sentence_of + 1)).equals("v"))) {
//						System.out.print("\n sentença: " + sentence.get(index_sentence_of) + "\n");
						
						
						if(!final_sentence.contains(verb_aux + "(p," + sentence.get(index_sentence_of) + ") " + check_and_conjunc_synonym(sentence.get(index_sentence_of + 1)) + check_or_conjunc_synonym(sentence.get(index_sentence_of + 1)) + ' ')) {
							st = verb_aux + "(p," + sentence.get(index_sentence_of) + ") " + check_and_conjunc_synonym(sentence.get(index_sentence_of + 1)) + check_or_conjunc_synonym(sentence.get(index_sentence_of + 1)) + ' ';
							final_sentence += st;
						
						}
//						System.out.print(st);
						

					} else if (i == parsing_list.size() - 2) {

					} else if (anterior_lema.equals(verb_aux)) {

					} else {
						if(verb_aux.isEmpty()) {
							if(final_sentence.contains("∀p")) {
								if(posterior_type.contains("verb")) {
									final_sentence += atual_lema + "(p)";
								} else {
									if(ignore(sentence.get(index_sentence_of +1))) {
										if(!sentence.get(0).toLowerCase().equals("every")) {
											final_sentence += atual_lema + "(p," + sentence.get(index_sentence_of +2) + ")";
									
										}
									} else {
										

										final_sentence += atual_lema + "(p," + sentence.get(index_sentence_of +1) + ")";
									}
								}
							} 
						} 
						
						else {
							
							if(!final_sentence.contains(" → ")) {
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
				if(!final_sentence.contains(" → ")) {
					check_if_then = check_if_then_conjunc_synonym(sentence.get(i));
					if(check_if_then!= "") {
						final_sentence += check_if_then;
						
					}
				}
				if(check_denil(sentence.get(i)).equals("¬")) {
					final_sentence += "¬";
				}
				
			}catch (Exception e) {
				
			}
			
			
			
			if (i < sentence.size() - 1) {
//				System.out.print(sentence);
				if (sentence.get(i).endsWith(",")) {

					String straux = "";
					straux = sentence.get(i).toString();
					straux = straux.substring(0, straux.length() - 1);
//					System.out.print(verb_aux + "(" + straux + ")" + " ∧ ");
					final_sentence += verb_aux + "(" + straux + ")" + " ∧ ";
				} else if ((check_and_conjunc_synonym(sentence.get(i + 1)).equals("∧"))) {
//					System.out.print("\n sentença: " + sentence.get(i) + "\n");
					if(!final_sentence.contains(verb_aux + "(p," + sentence.get(i) + ") " + check_and_conjunc_synonym(sentence.get(i + 1)) + check_or_conjunc_synonym(sentence.get(i + 1)) + ' ')) {
						final_sentence += verb_aux + "(p," + sentence.get(i) + ") " + check_and_conjunc_synonym(sentence.get(i + 1)) + check_or_conjunc_synonym(sentence.get(i + 1)) + ' ';

					}
					
					

				} else if(check_or_conjunc_synonym(sentence.get(i + 1)).equals("v")) {
					if(!final_sentence.contains(verb_aux + "(p,"+ sentence.get(i) + ") " + check_and_conjunc_synonym(sentence.get(i + 1)) + check_or_conjunc_synonym(sentence.get(i + 1)) + ' ')) {
						if (ignore(sentence.get(i + 2)) ) {
							if(!final_sentence.contains(" → ")) {
								final_sentence += " → ";
							}
							final_sentence += verb_aux + "(p," + sentence.get(i) + ") " + check_or_conjunc_synonym(sentence.get(i + 1)) + ' ' + verb_aux  + "(p," + sentence.get(i + 3) + ") ";
						}
						if (sentence.get(i + 2).equals(verb_aux)) {
							final_sentence += verb_aux + "(p," + sentence.get(i) + ") " + check_or_conjunc_synonym(sentence.get(i + 1)) + ' ' + verb_aux  + "(p,"+ sentence.get(i + 3) + ") ";
						}
					}
				}
				else {
//					final_sentence += verb_aux + '(' + sentence.get(i) + ") " + check_and_conjunc_synonym(sentence.get(i + 1)) + check_or_conjunc_synonym(sentence.get(i + 1)) + ' ';
				}
				
			}
			
			
			if (i == (sentence.size() - 2)) {
				
				final_sentence += verb_aux + "(p," + sentence.get(i+1) + ") ";
				
			}
			
			
			else if(atual_lema.contains(sentence.get(sentence.size()-2))) {
				if(aux < countString(sentence, sentence.get(sentence.size()-2))) {
					aux+=1;
					
				}
				else {
					if (!final_sentence.contains(verb_aux + "(p," + sentence.get(sentence.size()-1) + ") "))
						final_sentence += verb_aux + "(p," + sentence.get(sentence.size()-1) + ") ";
					
				}

			}
			

		}
		
//		 for (String neg : negacao) {
////           System.out.println("neg: " + palavra);
//
//           if (palavra.equalsIgnoreCase(neg)) {
//               saida.add("¬");
//
//               saida.add(frase[x] + "(" + frase[x - 2] + ", x)");
//
//           }
//       }

		
		
		System.out.print(final_sentence);
		return "s";

	}
}
