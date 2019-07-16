#ifdef GL_ES
precision highp  float;
#else
#define highp
#define mediump
#define lowp
#endif
attribute vec3 position;
attribute vec2 texcoord;
varying vec2 textureCoordinate;

void main()
{
    textureCoordinate = texcoord;
    gl_Position = vec4(position,1.0);
}
