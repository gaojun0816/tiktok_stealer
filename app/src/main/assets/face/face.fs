#ifdef GL_ES
precision highp  float;
#else
#define highp
#define mediump
#define lowp
#endif

varying vec2 textureCoordinate;
uniform sampler2D srctexture;


void main()
{
    gl_FragColor.rgb = texture2D(srctexture, textureCoordinate).rgb;
    gl_FragColor.a = 1.0;
}


