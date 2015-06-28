package com.foodroulette.asynctasks;

import android.os.AsyncTask;

import com.foodroulette.appstate.FoodRouletteApplication;
import com.foodroulette.callbacks.BusinessRunnable;
import com.google.gson.Gson;

import YelpAPI.YelpAPI;
import YelpData.BusinessData;

/**
 * Created by Sam on 6/27/2015.
 */
public class YelpSearchAsyncTask extends AsyncTask<String, Void, Object>
{
    private BusinessRunnable _callback;

    public YelpSearchAsyncTask(BusinessRunnable callback)
    {
        _callback = callback;
    }


    @Override
    protected Object doInBackground(String... params)
    {
        //android.os.Debug.waitForDebugger();
        String term = params[0];
        String location = params[1];

        YelpAPI api = YelpAPI.YelpInit();
        String response = api.searchForBusinessesByLocation(term, location);
        BusinessData business;
        business = new Gson().fromJson(response, BusinessData.class);
        return business;
    }

    @Override
    protected void onPostExecute(Object result)
    {
        _callback.runWithBusiness((BusinessData)result);
    }

}
