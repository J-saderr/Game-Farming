package Clock;

import Main.GamePanel;

import java.awt.*;

public class Clock {
    private static int day;
    private int hour;
    private int minute;

    private GamePanel gp;
    private Graphics2D g2;

    public Clock(GamePanel gp) {
        this.resetClock();
    }

    public void resetClock() {
        this.hour = 6;
        this.minute = 0;
        day = 1;
    }

    public void increaseTime() {
        if (hour == 23 & minute == 00) {
            day += 1;
            hour = 6;
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

    public static int getDay() {
        return day;
    }

    public void drawTime(Graphics2D g2){
        String text = "Day " + this.day + "Hour " + this.hour + "Minute " +this.minute;
        g2.drawString(text,0,48);
    }
}