
#include <jni.h>
#include <string>
#include <google-breakpad/src/client/linux/handler/minidump_descriptor.h>
#include <google_breakpad/processor/minidump_processor.h>
#include <google_breakpad/processor/process_state.h>
#include "android/log.h"

#include "google-breakpad/src/client/linux/handler/exception_handler.h"

#ifdef __cplusplus
extern "C"
{
#endif


bool DumpCallback(const google_breakpad::MinidumpDescriptor &descriptor,
                                                void *context, bool succeeded) {
        __android_log_print(ANDROID_LOG_INFO, "breakpad", "Dump path: %s\n", descriptor.path());
//    google_breakpad::MinidumpProcessor minidump_processor ;
//    google_breakpad::ProcessState process_state;
//    google_breakpad::ProcessResult ret = minidump_processor.Process(descriptor.path(), &process_state);
//    __android_log_print(ANDROID_LOG_INFO, "breakpad", "process_state: %s\n", process_state.crash_reason());
    return succeeded;
}



static bool callback(const google_breakpad::Minidump &dump,
                     void *context,
                     bool succeeded) {
    if (!succeeded) {
        return false;
    }
}


    JNIEXPORT void JNICALL
Java_com_junie_monitorlib_crash_NativeCrashHandler_nativeCrashHandlerInit(JNIEnv *env, jclass type) {

    // TODO
    google_breakpad::MinidumpDescriptor descriptor("/storage/emulated/0/hellobreakpad");
    google_breakpad::ExceptionHandler eh(descriptor, NULL,DumpCallback, NULL, true, -1);

    volatile int *a = (int*)(NULL);
    *a = 1;
    std::string hello = "Hello from C++";
    env->NewStringUTF(hello.c_str());

}

#ifdef __cplusplus
}
#endif
