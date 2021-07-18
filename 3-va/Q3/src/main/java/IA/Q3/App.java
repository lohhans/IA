package IA.Q3;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntologyManager;

/**
 * Hello world!
 *
 */
public class App 
{
	//Create OWLOntologyManager
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
	  OWLOntologyManager man = OWLManager.createOWLOntologyManager();
	  System.out.println(man.getOntologies().size());
	}
}
