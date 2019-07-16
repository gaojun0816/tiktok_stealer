#ifdef GL_ES
precision highp float;
#else
#define highp
#define mediump
#define lowp
#endif

varying  vec2 vTexCoord;

uniform sampler2D uTexture0;
uniform sampler2D uTexture1;
uniform sampler2D uTexture2;
uniform sampler2D uTexture3;
uniform lowp float uPercent;

vec4 lut3d(vec4 textureColor)
{
    float blueColor = textureColor.b * 15.0;
    vec2 quad1;
    quad1.y = max(min(4.0,floor(floor(blueColor) / 4.0)),0.0);
    quad1.x = max(min(4.0,floor(blueColor) - (quad1.y * 4.0)),0.0);

    vec2 quad2;
    quad2.y = max(min(floor(ceil(blueColor) / 4.0),4.0),0.0);
    quad2.x = max(min(ceil(blueColor) - (quad2.y * 4.0),4.0),0.0);

    vec2 texPos1;
    texPos1.x = (quad1.x * 0.25) + 0.5/64.0 + ((0.25 - 1.0/64.0) * textureColor.r);
    texPos1.y = (quad1.y * 0.25) + 0.5/64.0 + ((0.25 - 1.0/64.0) * textureColor.g);

    vec2 texPos2;
    texPos2.x = (quad2.x * 0.25) + 0.5/64.0 + ((0.25 - 1.0/64.0) * textureColor.r);
    texPos2.y = (quad2.y * 0.25) + 0.5/64.0 + ((0.25 - 1.0/64.0) * textureColor.g);

    vec4 newColor1 = texture2D(uTexture3, texPos1);
    vec4 newColor2 = texture2D(uTexture3, texPos2);

    vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
    return newColor;
}

void main(void)
{
    vec4 oralData =texture2D(uTexture0, vTexCoord).rgba;
    vec4 anjiaoData =texture2D(uTexture2, vTexCoord).rgba;
    vec4 tempData = oralData;
    tempData.r = texture2D(uTexture1,vec2(anjiaoData.r, oralData.r)).r;
    tempData.g = texture2D(uTexture1,vec2(anjiaoData.g, oralData.g)).g;
    tempData.b = texture2D(uTexture1,vec2(anjiaoData.b, oralData.b)).b;
    /*
     genType mix (genType x, genType y, genType a)、genType mix (genType x, genType y, float a)
     返回线性混合的x和y，如：x⋅(1−a)+y⋅a
     */
    tempData = lut3d(tempData);
    oralData = mix(oralData,tempData,uPercent);

    gl_FragColor = oralData;
}
