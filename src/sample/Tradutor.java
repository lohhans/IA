package sample;

import java.util.Vector;

public class Tradutor {
    static String[] quantificadorUniversal = {"Nenhum", "Para todo", "Qualquer um"};
    static String[] quantificadorExistencial = {"Existe um", "Existe algum", "Há um", "Há uma"};
    static String[] implica = {"Então", "É", "Implica"};
    static String[] conjuncao = {"E", "Com um"};

    public static String tradutor(String frase) {

        Vector<String> saida = new Vector<>(999);
        StringBuilder saidaConcatenada = new StringBuilder();

        int x = 0;

        for (String palavra : frase.split(" ")) {

            x++;

            for (String qtdUni : quantificadorUniversal) {
                System.out.println("qtdUni: " + palavra);
                if (palavra.equalsIgnoreCase(qtdUni)) {
                    saida.add("∀x");
                    break;
                } else if (palavra.equalsIgnoreCase("Para")) {
                    if (frase.split(" ")[x].equalsIgnoreCase("todo")) {
                        saida.add("∀x");
                        break;
                    }
                } else if (palavra.equalsIgnoreCase("Qualquer")) {
                    if (frase.split(" ")[x].equalsIgnoreCase("um")) {
                        saida.add("∀x");
                        break;
                    }
                }

            }

            for (String qtdExi : quantificadorExistencial) {
                System.out.println("qtdExi: " + palavra);
                if (palavra.equalsIgnoreCase(qtdExi)) {
                    saida.add("∃x");
                    break;
                } else if (palavra.equalsIgnoreCase("Existe")) {
                    if (x < frase.split(" ").length) {
                        if (frase.split(" ")[x].equalsIgnoreCase("um")) {
                            saida.add("∃x");
                            break;
                        } else if (frase.split(" ")[x].equalsIgnoreCase("algum")) {
                            saida.add("∃x");
                            break;
                        }
                    }
                } else if (palavra.equalsIgnoreCase("Há")) {
                    if (x < frase.split(" ").length) {
                        if (frase.split(" ")[x].equalsIgnoreCase("um")) {
                            saida.add("∃x");
                            break;
                        } else if (frase.split(" ")[x].equalsIgnoreCase("uma")) {
                            saida.add("∃x");
                            break;
                        }
                    }
                }
            }

            for (String imp : implica) {
                System.out.println("imp: " + palavra);
                if (palavra.equalsIgnoreCase(imp)) {

                    saida.add(frase.split(" ")[x - 2] + "(x)");

                    saida.add("->");

                    if (frase.split(" ")[0].equalsIgnoreCase("Nenhum")) {
                        saida.add("¬");
                    }

                    saida.add(frase.split(" ")[x] + "(x)");

                    System.out.println(frase.split(" ")[x - 2]);

                    break;
                }

            }

        }

        for (String palavra : saida) {
            if (palavra.equalsIgnoreCase("¬")) {
                saidaConcatenada.append(palavra);
            } else {
                saidaConcatenada.append(palavra).append(" ");
            }
        }


        return saidaConcatenada.toString();

    }
}
