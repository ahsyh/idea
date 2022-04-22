package com.yihui.f;

import com.yihui.f.questions.Question001;

import static com.yihui.f.util.FileIo.FILE_SEPARATOR;

public class AppMain {
    public static void main(String[] args) {
        //test();
        Question001 q = new Question001();
        String fileName = ""
                + "src" + FILE_SEPARATOR
                + "main" + FILE_SEPARATOR
                + "resources" + FILE_SEPARATOR
                + "data.txt";
        q.setDataFile(fileName);
        q.readAllEvents();
        q.testAllEvents();
    }
}
