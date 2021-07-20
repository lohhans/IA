package IA.Q2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.dictionary.Dictionary;

public class App {
	public static void main(String[] args) throws FileNotFoundException, JWNLException, CloneNotSupportedException {

		Checker checker = new Checker();
		
		// Inicio do Helper Class
		String USAGE = "Usage: Examples [properties file]";
		Set<String> HELP_KEYS = Collections
				.unmodifiableSet(new HashSet<>(Arrays.asList("--help", "-help", "/help", "--?", "-?", "?", "/?")));
		Dictionary dictionary = null;

		if (args.length != 1) {
			dictionary = Dictionary.getDefaultResourceInstance();
		}

		else {
			if (HELP_KEYS.contains(args[0])) {
				System.out.println(USAGE);
			} else {
				FileInputStream inputStream = new FileInputStream(args[0]);
				dictionary = Dictionary.getInstance(inputStream);
			}
		}
		// Fim do Helper Class

		
		// Obter dados do usuario para traduzir
		if (dictionary != null) {

			String opcao = "";
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("App tradutor de textos em inglês para Lógica de Predicados");
			System.out.print("Digite uma frase aqui para formalizar (traduzir): \nEntrada -> ");
			
			String phrase = scanner.nextLine();
			String[] inputList = phrase.split(" ");
			ArrayList<String> sentence = new ArrayList<String>();

			for (int i = 0; i < inputList.length; i++) {
				sentence.add(inputList[i]);
			}

			Parser pars = new Parser(dictionary, sentence);
			ArrayList<IndexWord> sentence_analized = pars.run();
			
			System.out.print("Saida -> ");
			checker.formalize(sentence, sentence_analized);

		}

	}
}
