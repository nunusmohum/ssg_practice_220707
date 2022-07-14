package com.ll.exam;

import java.util.ArrayList;

public class PhraseService {
    PhraseRepository phraseRepository;

    PhraseService(){
        phraseRepository = new PhraseRepository();
        phraseRepository.checkDataFolder();
    }

    public Phrase regist(String content, String author){
        return phraseRepository.registPhrase(content, author);
    }

    public ArrayList<Phrase> getPhraseList(){
        return phraseRepository.getPhrasesList();
    }

    public Phrase findById(int id){
        ArrayList<Phrase> phrasesList = phraseRepository.getPhrasesList();
        for(Phrase phrase : phrasesList){
            if(id == phrase.getIndex()){
                return phrase;
            }
        }
        return null;
    }

    public void update(String content, String author, int id){
        phraseRepository.updatePhrase(content, author, id);
    }

    public void delete(int id){
        phraseRepository.deletePhrase(id);
    }

    public void build(){
        ArrayList<Phrase> phrasesList = phraseRepository.getPhrasesList();
        JsonHandler jsonHandler = new JsonHandler();
        String dataJSON = jsonHandler.jsonDataBuilder(phrasesList);
        phraseRepository.createDataJson(dataJSON);
    }
}
