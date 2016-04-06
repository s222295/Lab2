package it.polito.tdp.spellchecker.model;

import java.util.*;

import it.polito.tdp.spellchecker.db.DizionarioDAO;

public class Dictionary {
	
	protected List<String> dizionario;
	protected List<RichWord> parole;
	
	public Dictionary() {
		dizionario = new LinkedList<String>();
		parole =new LinkedList<RichWord>();
	}

	public void loadDictionary(){};
	
	
//	SpellCheck tramite query 
//	public List<RichWord> spellCheckText(HashSet<String> parole2){
//		RichWord r=null;
//		String sr=null;
//		DizionarioDAO dao = new DizionarioDAO();
//		
//		for(String s:parole2){
//			
//			sr=dao.spellCheckText(s);
//			if(sr!=null){
//				r= new RichWord(s, true);
//				parole.add(r);
//			}else{
//				r= new RichWord(s, false);
//				parole.add(r);
//			}
//		}
	
	
	
	
	public List<RichWord> spellCheckText(HashSet<String> parole2){
		
		// RICERCA DICOTOMICA
		
		for(String s:parole2){
			RichWord r=null;
			int fine = dizionario.size();
			int size = (fine)/2;
			int inizio = 0;
			String parolameta = dizionario.get(size);
		
			while(r==null && inizio!=fine){
				if((fine-inizio)==1){
					if(s.compareTo(dizionario.get(inizio))==0){
						r= new RichWord(s, true);
						parole.add(r);
					}else{
						inizio=fine;
					}
					
				}else{
					
					if(s.compareTo(parolameta)<0){
						fine=size;
						size=fine/2;
						parolameta = dizionario.get(size);
					}else if(s.compareTo(parolameta)==0){
						r= new RichWord(s, true);
						parole.add(r);
					}else{
						inizio=size;
						size=inizio+((fine-inizio)/2);
						parolameta = dizionario.get(size);
					}	
				}
			}
			if(r==null){
				r= new RichWord(s, false);
				parole.add(r);
			}
			
		}
		
		
		//RICERCA NON DICOTOMICA
		
		/*for(String s:parole2){
			if(dizionario.contains(s)){
				r= new RichWord(s, true);
				parole.add(r);
			}else{
				r= new RichWord(s, false);
				parole.add(r);
			}
		}*/
	
		return parole;
	}

}
