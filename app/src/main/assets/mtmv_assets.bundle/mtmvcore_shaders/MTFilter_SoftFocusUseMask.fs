#ifdef GL_ES
precision highp float;
#else
#define highp
#define mediump
#define lowp
#endif
uniform sampler2D uTexture0;
uniform sampler2D uTexture1;       // softfocus mask
uniform sampler2D uGaussTexture;    // gauss
varying vec2 vTexCoord;
void main()
{
    vec4 sum =  texture2D(uTexture0, vTexCoord);
    lowp vec4 sum2 = texture2D(uGaussTexture, vTexCoord);
    float rat = texture2D(uTexture1, vTexCoord).r;
    sum = mix(sum2, sum, rat);
    gl_FragColor = sum;
}
