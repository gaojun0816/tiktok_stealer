#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
uniform sampler2D uTexture0;
uniform sampler2D uTexture1;//暗角
uniform sampler2D uTexture2;//叠加方式
uniform float uPercent;

void main()
{
    vec4 orgColor = texture2D(uTexture0, vTexCoord);
    vec4 tempColor = orgColor;
    vec4 temp = texture2D(uTexture1, vTexCoord);
    orgColor.r = texture2D(uTexture2, vec2(temp.r,orgColor.r)).r;
    orgColor.g = texture2D(uTexture2, vec2(temp.g,orgColor.g)).g;
    orgColor.b = texture2D(uTexture2, vec2(temp.b,orgColor.b)).b;
  
    orgColor = mix(tempColor, orgColor, uPercent);
    gl_FragColor = orgColor;
}
