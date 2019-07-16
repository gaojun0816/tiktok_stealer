#ifdef GL_ES
precision highp  float;
#else
#define highp
#define mediump
#define lowp
#endif

varying vec2 vTexCoord;

uniform sampler2D uTexture0;
//blowout
uniform sampler2D uTexture1;
//overlay
uniform sampler2D uTexture2;
//map
uniform sampler2D uTexture3;
// percent
uniform float uPercent;

void main()
{
    vec4 texel = texture2D(uTexture0, vTexCoord);
    vec4 tmp = texel;
    vec3 bbTexel = texture2D(uTexture1, vTexCoord).rgb;

    texel.r = texture2D(uTexture2, vec2(bbTexel.r, texel.r)).r;
    texel.g = texture2D(uTexture2, vec2(bbTexel.g, texel.g)).g;
    texel.b = texture2D(uTexture2, vec2(bbTexel.b, texel.b)).b;

    vec4 mapped;
    mapped.r = texture2D(uTexture3, vec2(texel.r, .16666)).r;
    mapped.g = texture2D(uTexture3, vec2(texel.g, .5)).g;
    mapped.b = texture2D(uTexture3, vec2(texel.b, .83333)).b;
    mapped.a = 1.0;

    gl_FragColor = mix(tmp,mapped,uPercent);
}
