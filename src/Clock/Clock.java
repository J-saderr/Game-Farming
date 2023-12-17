package Clock;

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
    }

    public void increaseTime() {
        if (hour == 2) {
        }
        else if (minute == 50) {
            hour += 1;
            minute = 0;
        }
        else {
            minute += 10;
        }
    }

    public int getHour() { return hour; }
    public int getMinute() { return minute; }
}