package edu.arhs.first1100.util;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.util.Routine;
import edu.wpi.first.wpilibj.Timer;
import java.util.Vector;

public abstract class Routine extends SystemBase {

    private boolean done = false;
    private boolean cancelled = false;

    private Timer timer;

    public Routine(int sleep) {
        super();
        addRoutine(this);
        setSleep(sleep);

        timer = new Timer();
    }

    public boolean isDone() {
        return done;
    }

    public synchronized void waitForDone() {
        try {
            if (!done) {
                wait();
            }
        } catch (InterruptedException e) {
            Log.defcon1(this, "Error: " + e.getMessage());
        }
    }

    public synchronized void setDone() {
        done = true;
        stop();
        notify();
    }

    public synchronized void execute() {
        start();
        timer.start();
        waitForDone();
        timer.stop();
    }

    public final void cancel() {
        Log.defcon2(this, "cancelling " + this.getClass().getName());
        cancelled = true;
        doCancel();
        setDone();
    }

    public boolean isCancelled() {
        return cancelled;
    }

    abstract protected void doCancel();

    private static Vector routines = new Vector();

    public static void addRoutine(Routine aRoutine) {
        routines.addElement(aRoutine);
    }

    public static void disableRoutines() {
        for (int i = 0; i < routines.size(); i++) {
            ((Routine) routines.elementAt(i)).cancel();
        }
    }

    /**
     * Returns the time the routine has been running in milliseconds
     * @return elapsed time milliseconds
     */
    public int getTimeElapsed(){
        return (int)(timer.get()/1000);
    }
}