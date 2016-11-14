//
// Created by Jianan on 2016/11/14.
//
#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_qinxiandiqi_androiddemo_lib_Str_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
