package com.foodroulette.appstate;

import android.app.Application;
import android.location.LocationManager;

import com.foodroulette.callbacks.LocationRunnable;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Sam on 6/27/2015.
 */
public class FoodRouletteApplication extends Application
{
    //This is the app state (tumor) file where all application data can be stored, until the app is terminated
    // all non-primitives must be thread-safe

    //Create a concurrent list of location callbacks
    Queue<LocationRunnable> _locationChangedCallbacks = new ConcurrentLinkedQueue<LocationRunnable>();

    public boolean addLocationChangedCallback(LocationRunnable callback)
    {
        return _locationChangedCallbacks.add(callback);
    }

    public boolean removeLocationChangedCallback(LocationRunnable callback)
    {
        return _locationChangedCallbacks.remove(callback);
    }

    public void onLocationChange(double latitude, double longitude)
    {
        for(LocationRunnable runnable : _locationChangedCallbacks)
        {
            runnable.runWithLocation(latitude, longitude);
        }

    }

    public Thread locationServicesThread;


}



