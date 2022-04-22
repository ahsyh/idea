package com.yihui.f.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIo {
    public static final String LINE_END = System.lineSeparator();
    public static final String FILE_SEPARATOR = File.separator;

    public static class StringReader {
        private BufferedReader in;

        public void init(String name) throws FileNotFoundException {
            in=new BufferedReader(new FileReader(name));
        }

        public String readLine() throws IOException {
            String r = in.readLine();
            if (r == null) {
                in.close();
            }
            return r;
        }
    }

    public static class StringWriter {
        private BufferedWriter out;
        private FileWriter fw;

        public void init(String name) throws IOException {
            File file = new File(name);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile());
            out = new BufferedWriter(fw);
        }

        public void writeLine(String data) throws IOException {
            out.write(data + LINE_END);
        }

        public void close() throws IOException {
            out.close();
            fw.close();
        }
    }

    public static void test() {
        try {
//            StringWriter sw = new StringWriter();
//
//            sw.init("test.txt");
//            sw.writeLine("First Line");
//            sw.writeLine("Second Line");
//            sw.writeLine("Third Line");
//            sw.close();

            StringReader sr = new StringReader();
            String fileName = ""
                    + "src" + FILE_SEPARATOR
                    + "main" + FILE_SEPARATOR
                    + "resources" + FILE_SEPARATOR
                    + "data.txt";
            sr.init(fileName);
            String str;
            while ((str = sr.readLine())!= null) {
                Log.e("get content: [" + str + "]");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
