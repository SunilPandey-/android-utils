LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CFLAGS := 

LOCAL_MODULE    := libglutils
LOCAL_SRC_FILES :=\
	utils.cpp \
	
	
LOCAL_LDLIBS := -lz

#include $(BUILD_SHARED_LIBRARY)
include $(BUILD_STATIC_LIBRARY)
