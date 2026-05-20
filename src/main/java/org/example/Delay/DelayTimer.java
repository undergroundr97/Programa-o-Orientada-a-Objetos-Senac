package org.example.Delay;

public class DelayTimer {

    public static void delay(Integer mili) {
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
