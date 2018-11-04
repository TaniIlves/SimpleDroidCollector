package ivs.ilves.droidsmartcollector.tools;

import android.util.Log;

public class DebugTool {

    /**
     * Write log to Logcat
     *
     * @param logLevel    Debug level chair
     * @param messageText Your message
     */
    public static void writeLog(char logLevel, String messageText) {
        switch (logLevel) {
            case 'v':
                Log.v("MyLOG.VERBOSE", messageText);
                break;
            case 'd':
                Log.d("MyLOG.DEBUG", messageText);
                break;
            case 'i':
                Log.i("MyLOG.INFO", messageText);
                break;
            case 'w':
                Log.w("MyLOG.WARNING", messageText);
                break;
            case 'e':
                Log.e("MyLOG.ERROR", messageText);
                break;
        }
    }
}
