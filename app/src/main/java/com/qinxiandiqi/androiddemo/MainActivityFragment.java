package com.qinxiandiqi.androiddemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qinxiandiqi.androiddemo.services.ToolManager;
import com.qinxiandiqi.androiddemo.services.ToolService;
import com.qinxiandiqi.androiddemo.util.LogUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = new Intent(getActivity(), ToolService.class);
        getActivity().bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IToolManager toolManager = ToolManager.Stub.asInterface(service);
                try {
                    IIntegerTool integerTool = IIntegerTool.Stub.asInterface(toolManager.getToolService(ToolManager.TOOL_MANAGER_INTEGER));
                    LogUtils.logStr("3 + 8 = " + integerTool.add(3, 8));
                    LogUtils.logStr("10 - 5 = " + integerTool.sub(10, 5));
                    IStringTool stringTool = IStringTool.Stub.asInterface(toolManager.getToolService(ToolManager.TOOL_MANAGER_STRING));
                    LogUtils.logStr(stringTool.getToolName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                    LogUtils.logException("get remote error", e);
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
