package edu.arhs.first1100.log;

import java.util.Vector;
import edu.arhs.first1100.opctl.DriverStationDataFeeder;

/**
 * @author team1100
 */
class AdvLogger
{
    private Class aClass;
    private int defcon;

    AdvLogger(Class aClass, int defcon)
    {
        this.aClass = aClass;
        this.defcon = defcon;
    }
    public int getDefcon()
    {
        return defcon;
    }
    public Class getaClass()
    {
        return aClass;
    }
}

/**
 * @author Raymond Mead
 */
public class Log
{
    DriverStationDataFeeder dsdf;
    private static Vector records = new Vector();

    public static void dsLog(int line, String message)
    {

    }
    
    public static void defcon3(Object obj, String message)
    {
        if(checkClass(obj, 3))
        {
            log(obj, message, 3);
        }
    }

    public static void defcon2(Object obj, String message)
    {
        if(checkClass(obj, 2)) log(obj, message, 2);
    }
    
    public static void defcon1(Object obj, String message)
    {
        if(checkClass(obj, 1))
        {
            log(obj, message, 1);
        }
    }

    public static void addClass(Class aClass, int defcon)
    {
        records.addElement(new AdvLogger(aClass, defcon));
    }

    private static boolean checkClass(Object obj, int defcon)
    {
        boolean record = false;
        for(int i = 0; i < records.size() && !record; i++)
        {
            if(obj.getClass().equals(((AdvLogger)records.elementAt(i)).getaClass()) &&
                    defcon >= ((AdvLogger) records.elementAt(i)).getDefcon())
            {
                record = true;
            }
        }
        return record;
    }
    
    private static void log(Object obj, String message, int defcon)
    {
        String name = obj.getClass().getName();
        System.out.println("[" + defcon + "] " + name.substring(name.lastIndexOf('.')+1)+": "+message);
    }
}