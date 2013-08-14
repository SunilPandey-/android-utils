/*
 * matrixjni.cpp
 *
 *  Created on: Aug 15, 2013
 *      Author: jpm8888
 */

#include "com_test_matrixexample_MatrixJniStarter.h"
#include "glutils.h"


JNIEXPORT void JNICALL Java_com_test_matrixexample_MatrixJniStarter_draw(JNIEnv *env, jobject obj){
	glClearColor(0.5f, 0.5f,0.5f,0.5f);


}

JNIEXPORT void JNICALL Java_com_test_matrixexample_MatrixJniStarter_reshape(JNIEnv *env, jobject obj, jint w, jint h){

}

JNIEXPORT void JNICALL Java_com_test_matrixexample_MatrixJniStarter_create(JNIEnv *env, jobject obj){

}




