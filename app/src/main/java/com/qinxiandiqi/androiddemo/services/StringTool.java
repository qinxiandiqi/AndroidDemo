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
package com.qinxiandiqi.androiddemo.services;

import android.content.Context;
import android.os.RemoteException;

import com.qinxiandiqi.androiddemo.IStringTool;
import com.qinxiandiqi.androiddemo.util.LogUtils;

/**
 * Created by Jianan on 2016/07/08.
 */
public class StringTool extends IStringTool.Stub {

    private static StringTool sStringTool;
    private Context mContext;

    public static StringTool getInstatnce(Context context){
        if(sStringTool == null){
            synchronized (StringTool.class){
                if(sStringTool == null){
                    sStringTool = new StringTool(context);
                }
            }
        }
        return sStringTool;
    }

    private StringTool(Context context){
        mContext = context;
    }

    @Override
    public String getToolName() throws RemoteException {
        LogUtils.logProcessName(mContext);
        return getClass().getSimpleName();
    }
}
