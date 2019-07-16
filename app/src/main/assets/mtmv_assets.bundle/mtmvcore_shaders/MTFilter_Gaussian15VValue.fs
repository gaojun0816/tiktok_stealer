#ifdef GL_ES
precision mediump  float;
#else
#define highp
#define mediump
#define lowp
#endif
uniform sampler2D uTexture0;
varying vec2 vTexCoord;
uniform float uPercent;
vec4 gauss()
{
    vec4 sum = vec4(0.0);
    sum += texture2D(uTexture0, vTexCoord) * 0.040892;
    sum += texture2D(uTexture0, vTexCoord + vec2(0,0.0015621156111*uPercent)) * 0.080769;
    sum += texture2D(uTexture0, vTexCoord - vec2(0,0.0015621156111*uPercent)) * 0.080769;
    sum += texture2D(uTexture0, vTexCoord + vec2(0,0.0036449374676*uPercent)) * 0.076840;
    sum += texture2D(uTexture0, vTexCoord - vec2(0,0.0036449374676*uPercent)) * 0.076840;
    sum += texture2D(uTexture0, vTexCoord + vec2(0,0.0057277582824*uPercent)) * 0.070242;
    sum += texture2D(uTexture0, vTexCoord - vec2(0,0.0057277582824*uPercent)) * 0.070242;
    sum += texture2D(uTexture0, vTexCoord + vec2(0,0.0078105801389*uPercent)) * 0.061699;
    sum += texture2D(uTexture0, vTexCoord - vec2(0,0.0078105801389*uPercent)) * 0.061699;
    sum += texture2D(uTexture0, vTexCoord + vec2(0,0.0098934009537*uPercent)) * 0.052076;
    sum += texture2D(uTexture0, vTexCoord - vec2(0,0.0098934009537*uPercent)) * 0.052076;
    sum += texture2D(uTexture0, vTexCoord + vec2(0,0.0119762228102*uPercent)) * 0.042234;
    sum += texture2D(uTexture0, vTexCoord - vec2(0,0.0119762228102*uPercent)) * 0.042234;
    sum += texture2D(uTexture0, vTexCoord + vec2(0,0.0140590425833*uPercent)) * 0.032912;
    sum += texture2D(uTexture0, vTexCoord - vec2(0,0.0140590425833*uPercent)) * 0.032912;
    sum += texture2D(uTexture0, vTexCoord + vec2(0,0.0161060643359*uPercent)) * 0.024645;
    sum += texture2D(uTexture0, vTexCoord - vec2(0,0.0161060643359*uPercent)) * 0.024645;
    sum += texture2D(uTexture0, vTexCoord + vec2(0,0.0181842922954*uPercent)) * 0.017732;
    sum += texture2D(uTexture0, vTexCoord - vec2(0,0.0181842922954*uPercent)) * 0.017732;
    sum += texture2D(uTexture0, vTexCoord + vec2(0,0.0202625285885*uPercent)) * 0.012260;
    sum += texture2D(uTexture0, vTexCoord - vec2(0,0.0202625285885*uPercent)) * 0.012260;
    sum += texture2D(uTexture0, vTexCoord + vec2(0,0.0223407742569*uPercent)) * 0.008144;
    sum += texture2D(uTexture0, vTexCoord - vec2(0,0.0223407742569*uPercent)) * 0.008144;

    return sum;
}

void main()
{
    gl_FragColor = gauss();
}
