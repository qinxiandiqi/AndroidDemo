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
import android.os.IBinder;
import android.os.RemoteException;

import com.qinxiandiqi.androiddemo.IToolManager;

/**
 * Created by Jianan on 2016/07/08.
 */
public class ToolManager extends IToolManager.Stub {

    public static final int TOOL_MANAGER_STRING = 1;
    public static final int TOOL_MANAGER_INTEGER = 2;

    private Context mContext;

    public ToolManager(Context context){
        mContext = context;
    }

    @Override
    public IBinder getToolService(int type) throws RemoteException {
        switch (type){
            case TOOL_MANAGER_INTEGER:
                return IntegerTool.getInstance(mContext);
            case TOOL_MANAGER_STRING:
                return StringTool.getInstatnce(mContext);
            default:
                return null;
        }
    }
}
