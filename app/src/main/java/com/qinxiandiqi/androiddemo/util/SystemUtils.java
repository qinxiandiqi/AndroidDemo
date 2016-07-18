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
import android.util.Log;

/**
 * Created by Jianan on 2016/07/18.
 */
public class SystemUtils {

    /**
     * Get the current process's name
     * @param context the Context
     * @return the name of process
     */
    public static String getProcessName(Context context){
        int pid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningAppProcessInfo info : activityManager.getRunningAppProcesses()){
            if(info.pid == pid){
                return info.processName;
            }
        }
        return null;
    }
}
