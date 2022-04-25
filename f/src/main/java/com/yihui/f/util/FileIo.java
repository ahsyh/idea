package com.yihui.f.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File io operation.
 */
public final class FileIo {
    private FileIo() {
    }

    /**
     * ..
     */
    public static final String LINE_END = System.lineSeparator();
    /**
     * ..
     */
    public static final String FILE_SEPARATOR = File.separator;

    /**
     * Read string from file.
     */
    public static class StringReader {
        private BufferedReader in;

        /**
         * set file name.
         * @param name ...
         * @throws FileNotFoundException ...
         */
        public void init(final String name) throws FileNotFoundException {
            in = new BufferedReader(new FileReader(name));
        }

        /**
         * Read line.
         * @return ..
         * @throws IOException ...
         */
        public String readLine() throws IOException {
            String r = in.readLine();
            if (r == null) {
                in.close();
            }
            return r;
        }
    }

    /**
     * Class to support write string to file.
     */
    public static class StringWriter {
        private BufferedWriter out;
        private FileWriter fw;

        /**
         * set file name.
         * @param name ...
         * @throws IOException ...
         */
        public void init(final String name) throws IOException {
            File file = new File(name);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile());
            out = new BufferedWriter(fw);
        }

        /**
         * write one line.
         * @param data ...
         * @throws IOException ...
         */
        public void writeLine(final String data) throws IOException {
            out.write(data + LINE_END);
        }

        /**
         * close file opertion writers.
         * @throws IOException ...
         */
        public void close() throws IOException {
            out.close();
            fw.close();
        }
    }

    /**
     * test for file io.
     */
    public static void test() {
        try {
            String fileName = ""
                    + "src" + FILE_SEPARATOR
                    + "main" + FILE_SEPARATOR
                    + "resources" + FILE_SEPARATOR
                    + "test.txt";

            StringWriter sw = new StringWriter();

            sw.init(fileName);
            sw.writeLine("First Line");
            sw.writeLine("Second Line");
            sw.writeLine("Third Line");
            sw.close();

            StringReader sr = new StringReader();
            sr.init(fileName);
            String str;
            while ((str = sr.readLine()) != null) {
                Log.e("get content: [" + str + "]");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
