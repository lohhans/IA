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

		Checker form = new Checker();
		form.check_if_then_conjunc_synonym("whether");
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

		if (dictionary != null) {

			String opcao = "";
			Scanner scanner = new Scanner(System.in);
			do {
				System.out.println(
						"\n\n###  - TRADUZIR/FORMALIZAR UMA SENTENÇA EM LINGUAGEM NATURAL(LÍNGUA INGLESA) PARA LP ###");
				System.out.println("\n                  ========================");
				System.out.println("                  |     0 -  SAIR        |");
				System.out.println("                  |     1 - TRADUZIR     |");
				System.out.println("                  ========================\n");
				System.out.print("Opção -> ");
				opcao = scanner.nextLine();
				System.out.print("\n");

				switch (opcao) {

				case "0":
					break;

				case "1":

					System.out.print("Digite uma frase aqui: ");
					String phrase = scanner.nextLine();
					String[] inputList = phrase.split(" ");
					ArrayList<String> sentence = new ArrayList<String>();

					for (int i = 0; i < inputList.length; i++) {
						sentence.add(inputList[i]);
					}
					System.out.print(sentence);

					Parser pars = new Parser(dictionary, sentence);
					ArrayList<IndexWord> sentence_analized = pars.run();
					form.translate(sentence, sentence_analized);

					break;

				default:
					System.out.println("Opção Inválida!");
					break;
				}
			} while (opcao != "0");

		}

	}
}
