#ifdef GL_ES
precision highp  float;
#else
#define highp
#define mediump
#define lowp
#endif

attribute vec4 position;
attribute vec2 texcoord;

uniform float texelWidthOffset;
uniform float texelHeightOffset;

varying vec2 textureCoordinate;
varying vec2 blurCoord0;
varying vec2 blurCoord1;
varying vec2 blurCoord2;
varying vec2 blurCoord3;
varying vec2 blurCoord4;
varying vec2 blurCoord5;
varying vec2 blurCoord6;
varying vec2 blurCoord7;
varying vec2 blurCoord8;

void main()
{
    gl_Position = position;
    textureCoordinate = texcoord.xy;

    // Calculate the positions for the blur
    int multiplier = 0;
    vec2 blurStep;
    vec2 singleStepOffset = vec2(texelWidthOffset, texelHeightOffset);

    blurStep = -4.0 * singleStepOffset;
    blurCoord0 = texcoord.xy + blurStep;

    blurStep = -3.0 * singleStepOffset;
    blurCoord1 = texcoord.xy + blurStep;

    blurStep = -2.0 * singleStepOffset;
    blurCoord2 = texcoord.xy + blurStep;

    blurStep = -1.0 * singleStepOffset;
    blurCoord3 = texcoord.xy + blurStep;

    blurStep = 0.0 * singleStepOffset;
    blurCoord4 = texcoord.xy + blurStep;

    blurStep = 1.0 * singleStepOffset;
    blurCoord5 = texcoord.xy + blurStep;

    blurStep = 2.0 * singleStepOffset;
    blurCoord6 = texcoord.xy + blurStep;

    blurStep = 3.0 * singleStepOffset;
    blurCoord7 = texcoord.xy + blurStep;

    blurStep = 4.0 * singleStepOffset;
    blurCoord8 = texcoord.xy + blurStep;

}
