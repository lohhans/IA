package IA.Q4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.DefaultOntologyFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;


/**
* This example demonstrates how HermiT can be used to check the consistency of the Pizza ontology
*/
public class App {

	public static void main(String[] args) throws Exception {
	    try {
	    	
	    	// First, we create an OWLOntologyManager object. The manager will load and save ontologies.
	        OWLOntologyManager m=OWLManager.createOWLOntologyManager();
	        // We use the OWL API to load the Pizza ontology.
	        OWLOntology o=m.loadOntologyFromOntologyDocument(IRI.create("https://protege.stanford.edu/ontologies/pizza/pizza.owl"));
	        // Now, we instantiate HermiT by creating an instance of the Reasoner class in the package org.semanticweb.HermiT.
	        Reasoner hermit=new Reasoner(o);
	        // Finally, we output whether the ontology is consistent.
	        System.out.println(hermit.isConsistent());
	    	
	    	/*
	        // First, we create an OWLOntologyManager object. The manager will load and
	        // save ontologies.
	        OWLOntologyManager manager=OWLManager.createOWLOntologyManager();
	        // Now, we create the file from which the ontology will be loaded.
	        File inputOntologyFile = new File("SchoolESTGExample.owl");
	        // We use the OWL API to load the ontology.
	        OWLOntology ontology=manager.loadOntologyFromOntologyDocument(inputOntologyFile);
	        // Now we can start and create the reasoner. Here we create an instance of HermiT
	        // OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();        
	        //OWLReasoner owlReasoner = reasonerFactory.createReasoner(ontology);
	        Reasoner reasoner = new Reasoner(ontology);
	        reasoner.precomputeInferences();
	        */
	        
	        // We can determine if the ontology is actually consistent
	        if (hermit.isConsistent()) {
	        	System.out.println("OK");
	        		        	
	        	
	            // Now we create an output stream that HermiT can use to write the axioms. The output stream is
	            // a wrapper around the file into which the axioms are written.
	            File prettyPrintHierarchyFile=new File("prettyPrint.owl");
	            if (!prettyPrintHierarchyFile.exists())
	                prettyPrintHierarchyFile.createNewFile();
	            // turn to an absolute file, so that we can write to it
	            prettyPrintHierarchyFile=prettyPrintHierarchyFile.getAbsoluteFile();
	            
	            System.out.println(prettyPrintHierarchyFile);
	            
	            
	            BufferedOutputStream prettyPrintHierarchyStreamOut=new BufferedOutputStream(new FileOutputStream(prettyPrintHierarchyFile));
	            // The output stream is wrapped into a print write with autoflush.
	            PrintWriter output=new PrintWriter(prettyPrintHierarchyStreamOut,true);
	            // Now we let HermiT pretty print the hierarchies. Since all parameters are set to true,
	            // HermiT will print the class and the object property and the data property hierarchy.
	            long t=System.currentTimeMillis();
	            t=System.currentTimeMillis()-t;
	            hermit.printHierarchies(output, true, true, true);
	            
	            
	            OWLOntology cbdOnt = m.createOntology();


	            // Now save a copy in OWL/XML format
	            File f = new File("example.xml");                         
	            IRI documentIRI2 = IRI.create(f);
	            m.saveOntology(cbdOnt, new DefaultOntologyFormat(), documentIRI2);
	            System.out.println("Ontology saved in XML format.");
	        } else {
	            System.out.println("Ontology malformed.");
	        }

	        // Remove the ontology from the manager
	        //manager.removeOntology(ontology);

	    } catch (OWLOntologyCreationException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		
 	
	}
}