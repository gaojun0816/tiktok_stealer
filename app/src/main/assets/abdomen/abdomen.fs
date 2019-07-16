#ifdef GL_ES//for discriminate GLES & GL
#ifdef GL_FRAGMENT_PRECISION_HIGH
precision highp float;
#else
precision mediump float;
#endif
#else
#define highp
#define mediump
#define lowp
#endif
varying vec2 texcoordOut;
uniform sampler2D srctexture;         //原图
uniform sampler2D materialtexture;    //素材图           腹部图
uniform sampler2D blendtexture;       //正片叠底blend图   PSMultiply100.png


void main()
{
    vec4 orgColor = texture2D(srctexture, texcoordOut);
    vec4 tempColor = texture2D(materialtexture, texcoordOut);
    vec4 resultColor = orgColor;

    resultColor.r = texture2D( blendtexture, vec2(tempColor.r,orgColor.r)).r;
    resultColor.g = texture2D( blendtexture, vec2(tempColor.g,orgColor.g)).g;
    resultColor.b = texture2D( blendtexture, vec2(tempColor.b,orgColor.b)).b;

    resultColor = mix(orgColor,resultColor,tempColor.a);
	gl_FragColor = resultColor;
}
