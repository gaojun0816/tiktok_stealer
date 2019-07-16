#ifdef GL_ES
precision mediump float;
#endif
uniform sampler2D uTexture0;
uniform float uWidth;
uniform float uPercent;
varying vec2 vTexCoord;

vec4 gauss()
{
    float blur = uPercent / uWidth;
    vec4 sum = vec4(0.0);
    sum += texture2D(uTexture0, vTexCoord + vec2(-4.0*blur,0.0)) * 0.05;
    sum += texture2D(uTexture0, vTexCoord + vec2(-3.0*blur,0.0)) * 0.09;
    sum += texture2D(uTexture0, vTexCoord + vec2(-2.0*blur,0.0)) * 0.12;
    sum += texture2D(uTexture0, vTexCoord + vec2(-1.0*blur,0.0)) * 0.15;
    sum += texture2D(uTexture0, vTexCoord) * 0.18;
    sum += texture2D(uTexture0, vTexCoord + vec2(1.0*blur,0.0)) * 0.15;
    sum += texture2D(uTexture0, vTexCoord + vec2(2.0*blur,0.0)) * 0.12;
    sum += texture2D(uTexture0, vTexCoord + vec2(3.0*blur,0.0)) * 0.09;
    sum += texture2D(uTexture0, vTexCoord + vec2(4.0*blur,0.0)) * 0.05;
    return sum;
}

void main()
{
    gl_FragColor = gauss();
}

