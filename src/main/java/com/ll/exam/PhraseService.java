package com.ll.exam;

import java.util.ArrayList;

public class PhraseService {
    PhraseRepository phraseRepository;

    PhraseService(){
        phraseRepository = new PhraseRepository();
    }

    public Phrase regist(String content, String author){
        return phraseRepository.registPhrase(content, author);
    }

    public ArrayList<Phrase> getPhraseList(){
        return phraseRepository.getPhrasesList();
    }

    public Phrase findById(int id){
        return phraseRepository.findById(id);
    }

    public void update(String content, String author, int id){
        phraseRepository.updatePhrase(content, author, id);
    }

    public boolean delete(int id){
        return phraseRepository.deletePhrase(id);
    }

    public void build(){
        ArrayList<Phrase> phrasesList = phraseRepository.getPhrasesList();
        String dataJSON = Util.JsonHandler.jsonDataBuilder(phrasesList);
        phraseRepository.createDataJson(dataJSON);
    }
}
