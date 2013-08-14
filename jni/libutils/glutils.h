/*
 * glutils.h
 *
 *  Created on: Aug 14, 2013
 *      Author: jpm8888
 */

#ifndef GLUTILS_H_
#define GLUTILS_H_


#include <android/log.h>
#include <../libzip/zip.h>

extern zip* APKArchive;

#define  TAG    "GL_UTILS"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)


#include "../libmaths/glmath.h"
#include <GLES2/gl2ext.h>

static const char* ErrorString(GLenum error) {
	const char* msg;
	switch (error) {
#define Case( Token )  case Token: msg = #Token; break;
	Case(GL_NO_ERROR)
		;
	Case(GL_INVALID_VALUE)
		;
	Case(GL_INVALID_ENUM)
		;
	Case(GL_INVALID_OPERATION)
		;
	Case(GL_OUT_OF_MEMORY)
		;
#undef Case
	}

	return msg;
}

static void _CheckError(int line) {
	GLenum error = glGetError();

	do {
		LOGE("%d %s\n", line, ErrorString(error));
	} while ((error = glGetError()) != GL_NO_ERROR);

}

#define CheckError()  _CheckError(__LINE__ )

GLuint loadTextureFromPNG (const char* filename, int &width, int &height);

#endif /* GLUTILS_H_ */
