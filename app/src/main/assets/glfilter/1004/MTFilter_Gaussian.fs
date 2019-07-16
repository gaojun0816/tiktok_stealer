precision highp float;
uniform sampler2D inputImageTexture0;
varying highp vec2 textureCoordinate;
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
    highp vec4 sum = vec4(0.0);
    sum += texture2D(inputImageTexture0, blurCoord0) * 0.05;
    sum += texture2D(inputImageTexture0, blurCoord1) * 0.09;
    sum += texture2D(inputImageTexture0, blurCoord2) * 0.12;
    sum += texture2D(inputImageTexture0, blurCoord3) * 0.15;
    sum += texture2D(inputImageTexture0, blurCoord4) * 0.18;
    sum += texture2D(inputImageTexture0, blurCoord5) * 0.15;
    sum += texture2D(inputImageTexture0, blurCoord6) * 0.12;
    sum += texture2D(inputImageTexture0, blurCoord7) * 0.09;
    sum += texture2D(inputImageTexture0, blurCoord8) * 0.05;

    gl_FragColor = sum;
}
