package Clock;

import Main.Game;

public class Clock {
    //private int day;
    private int hour;
    private int minute;

    public Clock() {
        this.resetClock();
    }

    public void resetClock() {
        this.hour = 6;
        this.minute = 0;
        changeDay();
    }

    public void increaseTime() {
        if (hour == 2) {
            Game.triggerPassOut();
        }
        else if (minute == 50) {
            hour += 1;
            minute = 0;
        }
        else {
            minute += 10;
        }
    }

    public void changeDay() {
    }

    public int getHour() { return hour; }
    public int getMinute() { return minute; }
}