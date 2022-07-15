package com.ll.exam;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.ll.exam.Util.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PhraseRepositoryTest {
    private PhraseRepository phraseRepository;

    @BeforeAll
    public void beforeAll() {
        App.MODE = "test";
        phraseRepository = new PhraseRepository();
    }

    @BeforeEach
    public void beforeEach() {
        Util.FileHandler.deleteDir(new File(App.getDataPath()));
        phraseRepository.checkDataFolder();
        phraseRepository.checkIndexFile();
        phraseRepository.registPhrase("나에게 불가능이란 없다.", "나폴레옹");
        phraseRepository.registPhrase("나의 죽음을 적들에게 알리지 마라.", "이순신");
    }

    @Test
    public void 저장() {
        int newId = phraseRepository.getLastId();
        phraseRepository.registPhrase("자유가 아니면 죽음을 달라!", "패트릭 헨리");

        assertTrue(new File("%s%d.json".formatted(App.getPhrasePath(), newId)).exists());
    }

    @Test
    public void 조회() {
        Phrase phrase = phraseRepository.findById(1);
        assertEquals(1, phrase.getId());
        assertEquals("나에게 불가능이란 없다.", phrase.getContent());
        assertEquals("나폴레옹", phrase.getAuthor());
    }

    @Test
    public void 전체조회() {
        ArrayList<Phrase> wiseSayings = phraseRepository.getPhrasesList();

        assertEquals(2, wiseSayings.size());
        assertEquals(1, wiseSayings.get(0).getId());
        assertEquals("나에게 불가능이란 없다.", wiseSayings.get(0).getContent());
        assertEquals("나폴레옹", wiseSayings.get(0).getAuthor());

        assertEquals(2, wiseSayings.get(1).getId());
        assertEquals("나의 죽음을 적들에게 알리지 마라.", wiseSayings.get(1).getContent());
        assertEquals("이순신", wiseSayings.get(1).getAuthor());
    }

    @Test
    public void 삭제() {
        phraseRepository.deletePhrase(1);

        Phrase phrase = phraseRepository.findById(1);

        assertEquals(null, phrase);
    }
}
