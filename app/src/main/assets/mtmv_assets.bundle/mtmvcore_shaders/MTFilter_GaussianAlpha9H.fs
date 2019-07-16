#ifdef GL_ES
precision mediump float;
#endif
uniform sampler2D uTexture0;
uniform float uPercent;
varying vec2 vTexCoord;

vec4 gauss()
{
    vec4 sum = vec4(0.0);
    float total = 0.0;
    vec4 temp = texture2D(uTexture0, vTexCoord);
    temp.a *= 0.18;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(1.0*uPercent,0.0));
    temp.a *= 0.15;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord + vec2(-1.0*uPercent,0.0));
    temp.a *= 0.15;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(2.0*uPercent,0.0));
    temp.a *= 0.12;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord + vec2(-2.0*uPercent,0.0));
    temp.a *= 0.12;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(3.0*uPercent,0.0));
    temp.a *= 0.09;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord + vec2(-3.0*uPercent,0.0));
    temp.a *= 0.09;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(4.0*uPercent,0.0));
    temp.a *= 0.05;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord + vec2(-4.0*uPercent,0.0));
    temp.a *= 0.05;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    sum.rgb /= total;
    return sum;
}


void main()
{
    gl_FragColor = gauss();
}

