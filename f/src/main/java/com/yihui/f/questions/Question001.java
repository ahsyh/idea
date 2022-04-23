package com.yihui.f.questions;

import com.yihui.f.util.FileIo;
import com.yihui.f.util.Log;
import lombok.Data;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.yihui.f.util.FileIo.FILE_SEPARATOR;

public class Question001 {

    public static void test() {
        Question001 q = new Question001();
        String fileName = ""
                + "src" + FILE_SEPARATOR
                + "main" + FILE_SEPARATOR
                + "resources" + FILE_SEPARATOR
                + "Q001-data.txt";
        q.setDataFile(fileName);
        q.readAllEvents();
        q.testAllEvents();
    }

    @Data
    private static class Event {
        private int numberRange;
        private int guessCount;
        private int numberInOneGuess;

        private ArrayList<int[]> allGuess;
    }

    @Data
    private static class PositionStatus {
        int numberRange;
        boolean isConfirmed;
        int confirm;
        Set<Integer> exclude;
    }

    @Setter
    private String dataFile;

    private final List<Event> allEvents = new ArrayList<>();

    public void readAllEvents () {
        FileIo.StringReader sr = new FileIo.StringReader();
        allEvents.clear();
        try {
            sr.init(dataFile);
            int eventNumber = Integer.parseInt(sr.readLine());
            for (int i = 0; i < eventNumber; i++) {
                Event e = readOneEvent(sr);
                if (e != null) {
                    allEvents.add(e);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("get all events");
    }

    private Event readOneEvent(FileIo.StringReader sr) {
        Event e = new Event();
        try {
            String[] head = sr.readLine().split(" ");
            e.numberRange = Integer.parseInt(head[0]);
            e.numberInOneGuess = Integer.parseInt(head[1]);
            e.guessCount = Integer.parseInt(head[2]);
            e.allGuess = new ArrayList<>();

            for (int i = 0; i < e.guessCount; i++) {
                String[] oneLine = sr.readLine().split(" ");
                int[] guess = new int[e.numberInOneGuess + 1];
                for (int j = 0; j < e.numberInOneGuess + 1; j++) {
                    guess[j] = Integer.parseInt(oneLine[j]);
                }
                e.allGuess.add(guess);
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
            e = null;
        }
        return e;
    }

    public void testAllEvents() {
        for (Event e: allEvents) {
            if (testOneEvent(e)) {
                Log.e("Yes");
            } else {
                Log.e("No");
            }
        }
    }

    private void generateAllPossibles(
            List<boolean[]> possibles,
            boolean[] onePossible,
            int numberInOneGuess,
            int correctNumber,
            int usedTrue,
            int curr) {
        if (curr >= numberInOneGuess) {
            boolean[] oneResult = new boolean[numberInOneGuess];
            System.arraycopy(onePossible, 0, oneResult, 0, numberInOneGuess);
            possibles.add(oneResult);
            return;
        }

        if (correctNumber > usedTrue) {
            onePossible[curr] = true;
            generateAllPossibles(possibles, onePossible, numberInOneGuess, correctNumber, usedTrue+1, curr+1);
        }

        if ((correctNumber - usedTrue) < (numberInOneGuess - curr)) {
            onePossible[curr] = false;
            generateAllPossibles(possibles, onePossible, numberInOneGuess, correctNumber, usedTrue, curr+1);
        }
    }

    private void generateAllPossibles(
            List<boolean[]> possibles,
            int numberInOneGuess,
            int correctNumber) {
        boolean[] onePossible = new boolean[numberInOneGuess];
        generateAllPossibles(possibles, onePossible, numberInOneGuess, correctNumber, 0, 0);
    }

    private ArrayList<PositionStatus> copyPositionStatus(ArrayList<PositionStatus> origin) {
        ArrayList<PositionStatus> result = new ArrayList<PositionStatus>();

        for (PositionStatus old : origin) {
            PositionStatus one = new PositionStatus();
            one.isConfirmed = old.isConfirmed;
            one.numberRange = old.numberRange;
            one.confirm = old.confirm;
            one.exclude = new HashSet<>();
            one.exclude.addAll(old.exclude);
            result.add(one);
        }
        return result;
    }

    private boolean isValid(boolean[] possible, ArrayList<PositionStatus> allStatus, int[] guess, int length, int numberRange) {
        for (int i = 0; i < length; i++) {
            int curr = guess[i];
            PositionStatus status = allStatus.get(i);

            if (possible[i]) {
                // we already assume current position should be another int
                if (status.isConfirmed && status.confirm != curr) {
                    return false;
                }

                // we already assume current position cannot be this int
                if (status.exclude.contains(curr)) {
                    return false;
                }

                // all possible int has been excluded, this cannot happen
                if (!status.exclude.contains(curr) && status.exclude.size() == (numberRange - 1)) {
                    return false;
                }
            } else {
                // we already assume the current position is this int, now we try to exclude it
                if (status.isConfirmed && status.confirm == curr) {
                    return false;
                }
            }
        }

        return true;
    }

    private void merge(ArrayList<PositionStatus> allStatus, boolean[] possible, int[] guess) {
        for (int i = 0; i < guess.length - 1; i++) {
            PositionStatus status = allStatus.get(i);
            int curr = guess[i];
            if (possible[i]) {
                status.isConfirmed = true;
                status.confirm = curr;
            } else {
                status.exclude.add(curr);
            }
        }
    }

    private boolean testGuess(
            int guessIndex,
            ArrayList<PositionStatus> positions,
            Event e) {
        if (guessIndex >= e.guessCount) {
            return true;
        }

        ArrayList<boolean[]> possibles = new ArrayList<>();
        int[] guess = e.allGuess.get(guessIndex);

        generateAllPossibles(possibles, e.numberInOneGuess, guess[e.numberInOneGuess]);
        for (boolean[] possible : possibles) {
            ArrayList<PositionStatus> newStatus = copyPositionStatus(positions);
            boolean r = false;
            if (isValid(possible, newStatus, guess, e.numberInOneGuess, e.numberRange)) {
                merge(newStatus, possible, guess);
                r = testGuess( guessIndex + 1, newStatus, e);
            }
            if (r) {
                StringBuilder sb = new StringBuilder();

                for (int k = 0; k < (guess.length - 1); k++) {
                    sb.append(String.format("%2d", guess[k]));
                    if (possible[k]) {
                        sb.append(" * ");
                    } else {
                        sb.append(" x ");
                    }
                }
                sb.append(" - ").append(guess[guess.length - 1]);
                Log.i("[]: " + sb);
                return true;
            }

        }

        return false;
    }

    private boolean testOneEvent(Event e) {
        ArrayList<PositionStatus> positions = new ArrayList<>();

        for (int i = 0; i < e.numberInOneGuess; i++) {
            PositionStatus position = new PositionStatus();
            position.numberRange = e.numberRange;
            position.isConfirmed = false;
            position.exclude = new HashSet<>();
            positions.add(position);
        }

        Log.i("Start test one event");
        boolean r = testGuess(0, positions, e);
        Log.i("Start test one complete, user guessed " + e.guessCount + " times.");

        return r;
    }
}
