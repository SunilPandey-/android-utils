/*
 * glmath.h
 *
 *  Created on: Aug 14, 2013
 *      Author: jpm8888
 */

#ifndef GLMATH_H_
#define GLMATH_H_

#include <GLES2/gl2.h>
#include <cmath>


#ifndef M_PI
#  define M_PI  3.14159265358979323846
#endif

namespace GLMATHS {
//#define M_PI  3.14159265358979323846

const GLfloat DivideByZeroTolerance = GLfloat(1.0e-07);
const GLfloat DegreesToRadians = M_PI / 180.0;
}

#include "vec.h"
#include "mat.h"

using namespace GLMATHS;
#endif /* GLMATH_H_ */
