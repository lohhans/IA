package IA.Q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.dictionary.Dictionary;

public class Parser {
	public Parser(Dictionary dictionary, ArrayList<String> sentence) throws JWNLException {
		this.dictionary = dictionary;
		this.sentence = sentence;
	}

	private final Dictionary dictionary;

	private ArrayList<String> sentence = new ArrayList<String>();
	private ArrayList<IndexWord> sentence_analized = new ArrayList<IndexWord>();
	private IndexWord token;
	private static final String USAGE = "Usage: Examples [properties file]";
	private static final Set<String> HELP_KEYS = Collections
			.unmodifiableSet(new HashSet<>(Arrays.asList("--help", "-help", "/help", "--?", "-?", "?", "/?")));

	public ArrayList<IndexWord> run() throws JWNLException, CloneNotSupportedException {
		int verbs = 0;
		int subs = 0;
		int adjs = 0;
		int adves = 0;

		for (int i = 0; i < this.sentence.size(); i++) {
			try {
				token = dictionary.lookupIndexWord(POS.VERB, this.sentence.get(i));
				if (token.getPOS().toString().contains("verb")) {
					verbs += 1;
				}
				demonstrateListOperation(token, this.sentence.get(i));
			} catch (NullPointerException e) {
			}

			try {
				token = dictionary.lookupIndexWord(POS.NOUN, this.sentence.get(i));
				if (token.getPOS().toString().contains("noun")) {
					verbs += 1;
				}
				demonstrateListOperation(token, this.sentence.get(i));
			} catch (NullPointerException f) {

			}
			try {
				token = dictionary.lookupIndexWord(POS.ADJECTIVE, this.sentence.get(i));
				if (token.getPOS().toString().contains("adjective")) {
					adjs += 1;
				}
				demonstrateListOperation(token, this.sentence.get(i));
			} catch (NullPointerException a) {

			}

			try {
				token = dictionary.lookupIndexWord(POS.ADVERB, this.sentence.get(i));
				if (token.getPOS().toString().contains("adverb")) {
					adves += 1;
				}
				demonstrateListOperation(token, this.sentence.get(i));
			} catch (NullPointerException p) {

			}
		}
		return sentence_analized;

	}

	private void demonstrateListOperation(IndexWord word, String token) throws JWNLException {

		if (word.getPOS().toString().contains("noun") || word.getPOS().toString().contains("verb")
				|| (word.getPOS().toString().contains("adjective")) || (word.getPOS().toString().contains("adverb"))) {

			if (word.getPOS().toString().contains("noun")) {
				sentence_analized.add(word);
				System.out.println("# Substantivo: " + token);

			} else if (word.getPOS().toString().contains("adjective")) {
				sentence_analized.add(word);
				System.out.println("# Adjetivo: " + token);
			} else if (word.getPOS().toString().contains("adverb")) {
				sentence_analized.add(word);
				System.out.println("# Adverbio: " + token);

			}

			else {
				sentence_analized.add(word);
				System.out.println("# Verbo: " + token);
			}

			PointerTargetNodeList hypernyms = PointerUtils.getDirectHypernyms(word.getSenses().get(0));
			String aux = hypernyms.toString();
			String[] auxList = aux.split(" ");
			ArrayList<String> synonyms = new ArrayList<String>();
			Boolean flag = false;
			for (int j = 0; j < auxList.length; j++) {
				if (auxList[j].equals("Words:")) {
					flag = true;
				}
				if (auxList[j].equals("--")) {
					flag = false;
				}
				if (flag == true && auxList[j].equals("Words:") == false) {
					synonyms.add(auxList[j].toString().replaceAll(",", ""));
				}
			}
			System.out.println("Sinônimos: " + synonyms.toString());
		}
	}

}
