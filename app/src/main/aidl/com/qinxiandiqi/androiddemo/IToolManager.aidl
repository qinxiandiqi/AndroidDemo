// IToolManager.aidl
package com.qinxiandiqi.androiddemo;

// Declare any non-default types here with import statements

interface IToolManager {

    IBinder getToolService(int type);
}
