#ifdef GL_ES
precision mediump float;
#else
#define highp
#define mediump
#define lowp
#endif

attribute vec4 aPosition;
attribute vec2 aTexCoord0;
varying vec2 vTexCoord;

uniform float uWidth;
uniform float uHeight;
uniform float uSharpness;

varying vec2 vLeftTextureCoordinate;
varying vec2 vRightTextureCoordinate;
varying vec2 vTopTextureCoordinate;
varying vec2 vBottomTextureCoordinate;

varying float vCenterMultiplier;
varying float vEdgeMultiplier;

void main()
{
    gl_Position = aPosition;
    
    float imageWidthFactor = 1.0 / uWidth;
    float imageHeightFactor = 1.0 / uHeight;
    vec2 widthStep = vec2(imageWidthFactor, 0.0);
    vec2 heightStep = vec2(0.0, imageHeightFactor);
    
    vTexCoord = aTexCoord0.xy;
    vLeftTextureCoordinate = aTexCoord0.xy - widthStep;
    vRightTextureCoordinate = aTexCoord0.xy + widthStep;
    vTopTextureCoordinate = aTexCoord0.xy + heightStep;
    vBottomTextureCoordinate = aTexCoord0.xy - heightStep;
    vCenterMultiplier = 1.0 + 4.0 * uSharpness;
    vEdgeMultiplier = uSharpness;
}

