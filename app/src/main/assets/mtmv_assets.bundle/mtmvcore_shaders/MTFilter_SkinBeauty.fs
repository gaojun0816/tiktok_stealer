#ifdef GL_ES
precision mediump float;
#else
#define highp
#define mediump
#define lowp
#endif
uniform sampler2D uTexture0;
uniform sampler2D uTexture1;    // gauss
uniform sampler2D uTexture2;    // 美颜
varying vec2 vTexCoord;
#define Blend_pLitf(base,blend)   (min(1.0,max(0.0,((base)+2.0*(blend)-1.0))))
#define Blend_hLitf(base,blend) ((blend)<=0.5?(blend)*(base)/0.5:1.0-(1.0-(blend))*(1.0-(base))/0.5)
void main()
{
    vec4 oral = texture2D(uTexture0, vTexCoord);
    vec4 gauss = texture2D(uTexture1, vTexCoord);
    
    // beauty
    vec4 curve = oral;
    curve.r = texture2D(uTexture2,vec2(curve.r,0.5)).b;
    curve.g = texture2D(uTexture2,vec2(curve.g,0.5)).b;
    curve.b = texture2D(uTexture2,vec2(curve.b,0.5)).b;
    float G = oral.g;
    float G1 = 1.0 - gauss.g;
    G1 = Blend_pLitf(G, G1);
    float G2=mix(G,G1,0.5);
    G2=Blend_hLitf(G2, G2);
    G2=Blend_hLitf(G2, G2);
    G2=Blend_hLitf(G2, G2);
    oral=mix(curve,oral,G2);

    gl_FragColor = oral;
}