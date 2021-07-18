package IA.Q2;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.dictionary.Dictionary;

import javax.transaction.xa.XAException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;



public class Analyzer {

    private final Dictionary dictionary;
    private IndexWord token;
    private static final String USAGE = "Usage: Examples [properties file]";
    private static final Set<String> HELP_KEYS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            "--help", "-help", "/help", "--?", "-?", "?", "/?"
    )));

    public Analyzer(Dictionary dictionary) throws JWNLException {
        this.dictionary = dictionary;
    }


    public void go() throws JWNLException, CloneNotSupportedException {
        int verbs = 0;
        int subs = 0;
        Scanner input = new Scanner(System.in);
        String inputString = input.nextLine();
        String[] inputList = inputString.split(" ");
        System.out.println(inputString);
        for(int i=0; i<inputList.length; i++){
            try{
                token = dictionary.lookupIndexWord(POS.VERB, inputList[i]);
                if(token.getPOS().toString().contains("verb")){
                    verbs+=1;
                }
                demonstrateListOperation(token, inputList[i]);
            }catch(NullPointerException e){
                try {
                    token = dictionary.lookupIndexWord(POS.NOUN, inputList[i]);
                    if (token.getPOS().toString().contains("noun")) {
                        subs += 1;
                    }
                    demonstrateListOperation(token, inputList[i]);
                }catch(NullPointerException f){

                }
            }
        }
        System.out.println("# Qntd de substantivos/verbos: "+subs+"/"+verbs);

    }

    private void demonstrateListOperation(IndexWord word, String token) throws JWNLException {

        if(word.getPOS().toString().contains("noun") || word.getPOS().toString().contains("verb") ) {
            if(word.getPOS().toString().contains("noun")){
                System.out.println("# Substantivo: "+token);
            }else{
                System.out.println("# Verbo: "+token);
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
            System.out.println("Sinônimos: "+synonyms.toString());
        }

    }


    public static void main(String[] args) throws FileNotFoundException, JWNLException, CloneNotSupportedException {
        Dictionary dictionary = null;
        if (args.length != 1) {
            dictionary = Dictionary.getDefaultResourceInstance();
        } else {
            if (HELP_KEYS.contains(args[0])) {
                System.out.println(USAGE);
            } else {
                FileInputStream inputStream = new FileInputStream(args[0]);
                dictionary = Dictionary.getInstance(inputStream);
            }
        }

        if (null != dictionary) {
            new Analyzer(dictionary).go();
        }
    }




}