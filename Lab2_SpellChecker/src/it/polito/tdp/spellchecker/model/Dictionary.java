package it.polito.tdp.spellchecker.model;

import java.util.*;

public class Dictionary {
	
	protected List<String> dizionario;
	protected List<RichWord> parole;
	
	public Dictionary() {
		dizionario = new LinkedList<String>();
		parole =new LinkedList<RichWord>();
	}

	public void loadDictionary(){};
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		RichWord r;
		for(String s:inputTextList){
			if(dizionario.contains(s)){
				r= new RichWord(s, true);
				parole.add(r);
			}else{
				r= new RichWord(s, false);
				parole.add(r);
			}
		}
		
		return parole;
	}

}
