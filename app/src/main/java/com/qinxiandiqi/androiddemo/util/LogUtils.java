/*
   Copyright 2016 Jianan - qinxiandiqi@foxmail.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.qinxiandiqi.androiddemo.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Jianan on 2016/07/08.
 */
public class LogUtils {

    private static final String TAG = "Jianan";

    public static void logProcessName(Context ctx){
        String processName = SystemUtils.getProcessName(ctx);
        if(TextUtils.isEmpty(processName)){
            logException("Can't find out the process");
        }else {
            logStr("The Current process name is " + processName);
        }
    }

    public static void logStr(String str){
        Log.i(TAG, str);
    }

    public static void logException(String error){
        Log.e(TAG, error);
    }

    public static void logException(String error, Throwable ex){
        Log.e(TAG, error, ex);
    }
}
