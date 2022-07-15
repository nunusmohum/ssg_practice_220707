package com.ll.exam;

import java.util.ArrayList;

public class PhraseService {
    PhraseRepository phraseRepository;

    PhraseService(){
        phraseRepository = new PhraseRepository();
        phraseRepository.checkDataFolder();
        phraseRepository.checkIndexFile();
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
            if(id == phrase.getId()){
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
        String dataJSON = Util.JsonHandler.jsonDataBuilder(phrasesList);
        phraseRepository.createDataJson(dataJSON);
    }
}
