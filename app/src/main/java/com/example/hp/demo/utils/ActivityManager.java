package com.example.hp.demo.utils;

import android.app.Activity;
import android.os.Build;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ActivityManager {

    private static HashMap<String, Activity> manager = new HashMap<String, Activity>();

    public static void addActivity(Activity activity) {
        manager.put(activity.getClass().getName(), activity);
    }

    public static Activity getActivity(String activityName) {
        return manager.get(activityName);
    }

    public static void cleanActivities() {
        Collection<Activity> cs = manager.values();
        Iterator<Activity> it = cs.iterator();
        while (it.hasNext()) {
            Activity activity = it.next();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.finishAfterTransition();
            } else {
                activity.finish();
            }
        }
        manager.clear();
    }
    public static void cleanActivitiesExcept(Activity target) {
        Collection<Activity> cs = manager.values();
        Iterator<Activity> it = cs.iterator();
        while (it.hasNext()) {
            Activity activity = it.next();
            if (activity == target)
                continue;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.finishAfterTransition();
            } else {
                activity.finish();
            }
        }
        manager.clear();
    }

    public static void finishActivity(Activity activity) {
        removeActivity(activity);
        activity.finish();
    }

    public static void finishActivity(String activityClassName) {
        if (manager.containsKey(activityClassName)) {
            Activity activity = manager.get(activityClassName);
            manager.remove(activityClassName);
            activity.finish();
        }
    }

    public static void removeActivity(Activity activity) {
        String key = activity.getClass().getName();
        if (manager.containsKey(key)) {
            manager.remove(key);
        }
    }

}
