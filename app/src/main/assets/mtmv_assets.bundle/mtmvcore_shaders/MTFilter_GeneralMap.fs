#ifdef GL_ES
precision mediump float;
#endif

varying vec2 vTexCoord;
uniform sampler2D uTexture0;
uniform sampler2D uTexture1;
uniform float uPercent;

void main()
{
    vec4 oralData = texture2D(uTexture0, vTexCoord);
    vec4 tmp = oralData;
    oralData.r = texture2D( uTexture1, vec2(oralData.r,0.5)).r;
    oralData.g = texture2D( uTexture1, vec2(oralData.g,0.5)).g;
    oralData.b = texture2D( uTexture1, vec2(oralData.b,0.5)).b;
    gl_FragColor = mix(tmp,oralData,uPercent);
}
