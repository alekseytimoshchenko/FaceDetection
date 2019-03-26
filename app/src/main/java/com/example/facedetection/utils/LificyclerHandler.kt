package com.example.facedetection.utils

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle


/**
 * Created with care by Alexey.T on 26/03/2019.
 *
 * https://stackoverflow.com/a/13809991/5709159
 */
class LifecycleHandler : ActivityLifecycleCallbacks {
    companion object {
        private var resumed: Int = 0
        private var paused: Int = 0
        private var started: Int = 0
        private var stopped: Int = 0

        fun isApplicationVisible(): Boolean {
            return started > stopped
        }

        fun isApplicationInForeground(): Boolean {
            return resumed > paused
        }
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {}

    override fun onActivityDestroyed(activity: Activity?) {}

    override fun onActivityResumed(activity: Activity?) {
        ++resumed
    }

    override fun onActivityPaused(activity: Activity?) {
        ++paused
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

    override fun onActivityStarted(activity: Activity?) {
        ++started
    }

    override fun onActivityStopped(activity: Activity?) {
        ++stopped
    }

    // If you want a static function you can use to check if your application is
    // foreground/background, you can use the following:
    /*
    // Replace the four variables above with these four
    private static int resumed;
    private static int paused;
    private static int started;
    private static int stopped;

    // And these two public static functions
    public static boolean isApplicationVisible() {
        return started > stopped;
    }

    public static boolean isApplicationInForeground() {
        return resumed > paused;
    }
    */
}