package com.yihui.f.questions;

import com.yihui.f.util.FileIo;
import com.yihui.f.util.Log;
import lombok.Data;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Question001 {
    @Data
    private static class Event {
        private int numberRange;
        private int guessCount;
        private int numberInOneGuess;

        private ArrayList<int[]> allGuess;
    }

    @Setter
    private String dataFile;
    private List<Event> allEvents = new ArrayList<>();

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
        Log.e("get all events");
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
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    @Data
    private static class PositionStatus {
        int numberRange;
        boolean isConfirmed;
        int confirm;
        Set<Integer> exclude;
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

        if ((correctNumber - usedTrue) < (numberInOneGuess - curr)) {
            onePossible[curr] = false;
            generateAllPossibles(possibles, onePossible, numberInOneGuess, correctNumber, usedTrue, curr+1);
        }
        if (correctNumber > usedTrue) {
            onePossible[curr] = true;
            generateAllPossibles(possibles, onePossible, numberInOneGuess, correctNumber, usedTrue+1, curr+1);
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
        Set<Integer> samePossible = new HashSet<>();
        Map<Integer, Integer> confirmed = new HashMap<>();

        for (int i = 0; i < length; i++) {
            PositionStatus status = allStatus.get(i);

            if (status.isConfirmed) {
                confirmed.put(status.confirm, i);
            }
        }

        for (int i = 0; i < length; i++) {
            if (possible[i]) {
                int curr = guess[i];
                PositionStatus status = allStatus.get(i);

                if (samePossible.contains(curr)) {
                    return false;
                }
                samePossible.add(curr);

                if (confirmed.containsKey(curr) && confirmed.get(curr) != i) {
                    return false;
                }

                if (status.exclude.contains(curr)) {
                    return false;
                }

                if (!status.exclude.contains(curr) && status.exclude.size() == (numberRange - 1)) {
                    return false;
                }
            }
        }

        return true;
    }

    private void merge(boolean[] possible, ArrayList<PositionStatus> allStatus, int[] guess, int length) {
        for (int i = 0; i < length; i++) {
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
            ArrayList<int[]> allGuess,
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
            if (isValid(possible, newStatus, allGuess.get(guessIndex), e.numberInOneGuess, e.numberRange)) {
                merge(possible, newStatus, allGuess.get(guessIndex), e.numberInOneGuess);
                r = testGuess(allGuess, guessIndex + 1, newStatus, e);
            }
            if (r) {
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

        if (testGuess(e.allGuess, 0, positions, e)) {
            return true;
        }

        return false;
    }


}
