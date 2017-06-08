/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.deepampatel.aajkhanemeinkyahai;

import android.annotation.SuppressLint;
import android.net.Uri;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class NetworkUtils {

    final static String MENU_BASE_URL =
            "https://aajkhanemeinkyahai.firebaseio.com/148vqOGRjOS5sscrTA0ZJTqdyhF3/Mess1.json";



    public static URL buildUrl() {

        Uri builtUri = Uri.parse(MENU_BASE_URL).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    @SuppressLint("LongLogTag")
    public static Menu readdatfromjson(String output) {
        try {
            JSONObject baseJsonResponse = new JSONObject(output);
            String item1=baseJsonResponse.getString("item1");
            String item2=baseJsonResponse.getString("item2");
            String item3=baseJsonResponse.getString("item3");
            return new Menu(item1,item2,item3);
        } catch (JSONException e) {
            return null;
        }

    }
}