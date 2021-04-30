package sample;

import java.util.Arrays;
import java.util.Vector;

public class Tradutor {

    static String[] quantificadorUniversal = {"Nenhum", "Para todo", "Qualquer", "Qualquer um", "Nada", "Tudo", "Todo"};
    static String[] quantificadorExistencial = {"Existe um", "Existe algum", "Há um", "Há uma"};
    static String[] condicional = {"Então", "É", "Implica"};
    static String[] conjuncao = {"E", "Com um", "que"};
    static String[] disjuncao = {"ou", "Ou um"};
    static String[] bicondicional = {"somente se", "só se"};
    static String[] negacao = {"não"};

    static String[] total = {"Nenhum", "Para todo", "Qualquer", "Qualquer um", "Nada", "Tudo", "Todo", "Existe um", "Existe algum", "Há um", "Há uma", "Então", "É", "Implica", "E", "Com um", "ou", "Ou um", "somente se", "só se", "não"};

    public static String tradutor(String frase) {

        Vector<String> saida = new Vector<>(999);
        StringBuilder saidaConcatenada = new StringBuilder();

        int x = 0;
        String variavel = "";
        boolean flagAnterior = false;
        boolean flagPosterior = false;
        boolean freio = false;

        for (String palavra : frase.split(" ")) {

            x++;

            /*if(palavra.equalsIgnoreCase("que")){
                String palavraAnterior = frase.split(" ")[x-2];
                String palavraPosterior = frase.split(" ")[x];

                for(String tot : total){
                    if(palavraAnterior.equalsIgnoreCase(tot)){
                        flagAnterior = true;
                    }
                }

                for(String tot : total){
                    if(palavraPosterior.equalsIgnoreCase(tot)){
                        flagPosterior = true;
                    }
                }

                if(!flagAnterior){
                    saida.add(palavraAnterior + "(x)");
                }

                if(!flagPosterior){
                    saida.add(palavraPosterior + "(y)");
                }
            }*/

            for (String qtdUni : quantificadorUniversal) {
                System.out.println("qtdUni: " + palavra);
                if (palavra.equalsIgnoreCase(qtdUni)) {
                    saida.add(0,"∀x");
                    break;
                } else if (palavra.equalsIgnoreCase("Para")) {
                    if (x < frase.split(" ").length) {
                        if (frase.split(" ")[x].equalsIgnoreCase("todo")) {
                            saida.add(0,"∀x");
                            break;
                        }
                    }
                } else if (palavra.equalsIgnoreCase("Qualquer")) {
                    if (x < frase.split(" ").length) {
                        if (frase.split(" ")[x].equalsIgnoreCase("um")) {
                            saida.add(0,"∀x");
                            break;
                        }
                    }
                }

            }

            for (String qtdExi : quantificadorExistencial) {
                System.out.println("qtdExi: " + palavra);
                if (palavra.equalsIgnoreCase(qtdExi)) {
                    saida.add(0,"∃x");
                    break;
                } else if (palavra.equalsIgnoreCase("Existe")) {
                    if (x < frase.split(" ").length) {
                        if (frase.split(" ")[x].equalsIgnoreCase("um")) {
                            saida.add(0,"∃x");
                            break;
                        } else if (frase.split(" ")[x].equalsIgnoreCase("algum")) {
                            saida.add(0,"∃x");
                            break;
                        }
                    }
                } else if (palavra.equalsIgnoreCase("Há")) {
                    if (x < frase.split(" ").length) {
                        if (frase.split(" ")[x].equalsIgnoreCase("um")) {
                            saida.add(0,"∃x");
                            break;
                        } else if (frase.split(" ")[x].equalsIgnoreCase("uma")) {
                            saida.add(0,"∃x");
                            break;
                        }
                    }
                }
            }

            for (String imp : condicional) {
                System.out.println("imp: " + palavra);
                if (palavra.equalsIgnoreCase(imp)) {

                    if(frase.split(" ")[x].equalsIgnoreCase("um") || frase.split(" ")[x].equalsIgnoreCase("uma")){
                        saida.add(frase.split(" ")[x - 2] + "(x)");

                        saida.add("->");

                        if (frase.split(" ")[0].equalsIgnoreCase("Nenhum")) {
                            saida.add("¬");
                        }

                        saida.add(frase.split(" ")[x + 1] + "(x)");

                        break;
                    }

                    saida.add(frase.split(" ")[x - 2] + "(x)");

                    saida.add("->");

                    if (frase.split(" ")[0].equalsIgnoreCase("Nenhum")) {
                        saida.add("¬");
                    }

                    saida.add(frase.split(" ")[x] + "(x)");

                    break;


                }

            }

            for (String conj : conjuncao) {
                System.out.println("conj: " + palavra);

                if (palavra.equalsIgnoreCase(conj)) {
                    saida.add(frase.split(" ")[x - 2] + "(x)");

                    saida.add("∧");

                    for (int i = x; i < frase.split(" ").length; i++) {
                        variavel = variavel + frase.split(" ")[i];
                        for(String tot : total){
                            System.out.println("entrou aq");
                            if(frase.split(" ")[i+1].equalsIgnoreCase(tot)){
                                System.out.println("BREAAAAAAAAAAAAAAAAAAAAAKKK");
                                freio = true;
                            }
                        }

                        if(freio){
                            break;
                        }
                    }



                    saida.add(variavel + "(x)");

                } else if (palavra.equalsIgnoreCase("Com")) {
                    if (x < frase.split(" ").length) {
                        if (frase.split(" ")[x].equalsIgnoreCase("um")) {
                            saida.add(frase.split(" ")[x - 2] + "(x)");

                            saida.add("∧");

                            for (int i = x + 1; i < frase.split(" ").length; i++) {
                                variavel = variavel + frase.split(" ")[i];
                                for(String tot : total){
                                    System.out.println("entrou aq");
                                    if(frase.split(" ")[i].equalsIgnoreCase(tot)){
                                        System.out.println("BREAAAAAAAAAAAAAAAAAAAAAKKK");
                                        break;
                                    }
                                }
                            }

                            saida.add(variavel + "(x)");
                            break;
                        }
                    }
                }
            }

            for (String disj : disjuncao) {
                System.out.println("disj: " + palavra);

                if (palavra.equalsIgnoreCase(disj)) {
                    saida.add(frase.split(" ")[x - 2] + "(x)");

                    saida.add("∨");

                    if (frase.split(" ")[x].equalsIgnoreCase("um")) {
                        for (int i = x + 1; i < frase.split(" ").length; i++) {
                            variavel = variavel + frase.split(" ")[i];
                        }
                    } else if (frase.split(" ")[x].equalsIgnoreCase("uma")) {
                        for (int i = x + 1; i < frase.split(" ").length; i++) {
                            variavel = variavel + frase.split(" ")[i];
                        }
                    } else {
                        for (int i = x; i < frase.split(" ").length; i++) {
                            variavel = variavel + frase.split(" ")[i];
                        }
                    }

                    saida.add(variavel + "(x)");

                }
            }

            //Scrooge não ama nada que é estranho
            //Saída: ∀ t estranho (t) → ¬ ama (Scrooge, t)

            for (String bic : bicondicional) {
                System.out.println("bic: " + palavra);

                if (palavra.equalsIgnoreCase(bic)) {
                    System.out.println(":(");
                }
            }

            for (String neg : negacao) {
                System.out.println("neg: " + palavra);

                if (palavra.equalsIgnoreCase(neg)) {
                    saida.add("¬");

                    saida.add(frase.split(" ")[x] + "(" + frase.split(" ")[x-2] + ", x)");

//                    TODO: Scrooge

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
