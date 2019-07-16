#ifdef GL_ES
precision mediump float;
#else
#define highp
#define mediump
#define lowp
#endif

varying vec2 vTexCoord;
uniform sampler2D uTexture0;
uniform sampler2D uTexture1;
uniform float uContrast;

void main()
{
    vec4 oralColor = texture2D(uTexture0, vTexCoord);
    oralColor.r = mix(oralColor.r,texture2D(uTexture1,vec2(oralColor.r,0.5)).r,uContrast);
    oralColor.g = mix(oralColor.g,texture2D(uTexture1,vec2(oralColor.g,0.5)).g,uContrast);
    oralColor.b = mix(oralColor.b,texture2D(uTexture1,vec2(oralColor.b,0.5)).b,uContrast);
    gl_FragColor = oralColor;

}