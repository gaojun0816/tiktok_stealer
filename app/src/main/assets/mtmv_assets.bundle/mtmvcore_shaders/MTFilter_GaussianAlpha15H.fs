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
    float total = 0.0;
    vec4 temp = texture2D(uTexture0, vTexCoord);
    temp.a *= 0.040892;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(0.0015621156111*uPercent,0));
    temp.a *= 0.080769;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord - vec2(0.0015621156111*uPercent,0));
    temp.a *= 0.080769;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(0.0036449374676*uPercent,0));
    temp.a *= 0.076840;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord - vec2(0.0036449374676*uPercent,0));
    temp.a *= 0.076840;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;

    temp = texture2D(uTexture0, vTexCoord + vec2(0.0057277582824*uPercent,0));
    temp.a *= 0.070242;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord - vec2(0.0057277582824*uPercent,0));
    temp.a *= 0.070242;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(0.0078105801389*uPercent,0));
    temp.a *= 0.061699;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord - vec2(0.0078105801389*uPercent,0));
    temp.a *= 0.061699;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(0.0098934009537*uPercent,0));
    temp.a *= 0.052076;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord - vec2(0.0098934009537*uPercent,0));
    temp.a *= 0.052076;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(0.0119762228102*uPercent,0));
    temp.a *= 0.042234;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord - vec2(0.0119762228102*uPercent,0));
    temp.a *= 0.042234;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(0.0140590425833*uPercent,0));
    temp.a *= 0.032912;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord - vec2(0.0140590425833*uPercent,0));
    temp.a *= 0.032912;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(0.0161060643359*uPercent,0));
    temp.a *= 0.024645;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord - vec2(0.0161060643359*uPercent,0));
    temp.a *= 0.024645;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(0.0181842922954*uPercent,0));
    temp.a *= 0.017732;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord - vec2(0.0181842922954*uPercent,0));
    temp.a *= 0.017732;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    
    temp = texture2D(uTexture0, vTexCoord + vec2(0.0202625285885*uPercent,0));
    temp.a *= 0.012260;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord - vec2(0.0202625285885*uPercent,0));
    temp.a *= 0.012260;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;

    temp = texture2D(uTexture0, vTexCoord + vec2(0.0223407742569*uPercent,0));
    temp.a *= 0.008144;
    total += temp.a;
    temp.rgb *= temp.a;
    sum += temp;
    temp = texture2D(uTexture0, vTexCoord - vec2(0.0223407742569*uPercent,0));
    temp.a *= 0.008144;
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
