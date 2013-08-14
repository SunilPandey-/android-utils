LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := matrixjni

LOCAL_C_INCLUDES := $(LOCAL_PATH)/../libzip/ $(LOCAL_PATH)/../libpng/ $(LOCAL_PATH)/../libutils/
LOCAL_STATIC_LIBRARIES := libzip libpng libglutils

#-Wno-psabi to remove warning about GCC 4.4 va_list warning
LOCAL_CFLAGS := -DANDROID_NDK -Wno-psabi

LOCAL_DEFAULT_CPP_EXTENSION := cpp 

LOCAL_SRC_FILES := \
    matrixjni.cpp \
#  utils.cpp \

LOCAL_LDLIBS := -lm -llog -ljnigraphics -landroid -lEGL -lGLESv1_CM

LOCAL_LDLIBS+=-L$(SYSROOT)/usr/lib -ldl
LOCAL_LDLIBS+=-L$(SYSROOT)/usr/lib -lz

include $(BUILD_SHARED_LIBRARY)