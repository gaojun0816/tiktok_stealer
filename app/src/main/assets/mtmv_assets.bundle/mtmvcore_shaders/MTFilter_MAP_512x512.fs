#ifdef GL_ES
precision mediump float;
#endif

uniform  sampler2D uTexture0;
uniform  sampler2D uTexture1;
varying  vec2 vTexCoord;
uniform  float uPercent;

lowp vec4 lut3d(highp vec4 textureColor)
{
    highp float blueColor = textureColor.b * 63.0;
    
    highp vec2 quad1;
    quad1.y = max(min(8.0,floor(floor(blueColor) / 8.0)),0.0);
    quad1.x = max(min(8.0,floor(blueColor) - (quad1.y * 8.0)),0.0);
    
    mediump vec2 quad2;
    quad2.y = max(min(floor(ceil(blueColor) / 8.0),8.0),0.0);
    quad2.x = max(min(ceil(blueColor) - (quad2.y * 8.0),8.0),0.0);
    
    highp vec2 texPos1;
    texPos1.x = (quad1.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.r);
    texPos1.y = (quad1.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.g);
    
    highp vec2 texPos2;
    texPos2.x = (quad2.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.r);
    texPos2.y = (quad2.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.g);
    
    lowp vec4 newColor1 = texture2D(uTexture1, texPos1);
    lowp vec4 newColor2 = texture2D(uTexture1, texPos2);
    
    mediump vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
    return newColor;
}
void main()
{
    highp vec4 orgColor =texture2D(uTexture0, vTexCoord);
    vec4 tempColor = lut3d(orgColor);
    gl_FragColor = mix(orgColor,vec4(tempColor.rgb, orgColor.w),uPercent);
}
