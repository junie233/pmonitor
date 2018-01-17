
MY_ROOT_PATH := $(call my-dir)/src/main/jni

include $(MY_ROOT_PATH)/google-breakpad/Android.mk

LOCAL_PATH := $(MY_ROOT_PATH)
NDK_APP_DST_DIR := libs/$(TARGET_ARCH_ABI)



include $(CLEAR_VARS)

LOCAL_MODULE    := HelloBreakPad
LOCAL_SRC_FILES := nativecrashhandler.cpp

LOCAL_LDFLAGS := -latomic
LOCAL_LALIBS += -llog
LOCAL_STATIC_LIBRARIES += breakpad_client

include $(BUILD_SHARED_LIBRARY)