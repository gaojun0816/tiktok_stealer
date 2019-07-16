precision highp float;

varying vec2 texcoordOut;
uniform sampler2D srctexture;         //原图
uniform sampler2D maskTexture;         //mask
uniform float alpha;                  //透明度 0-1.0

void main()
{
    vec4 originColor = texture2D(srctexture,texcoordOut);
    vec4 maskColor = texture2D(maskTexture,texcoordOut);
    float resultAlpha = 1.0 - maskColor.r;
    resultAlpha = min(resultAlpha, originColor.a);
    resultAlpha *= alpha;

    gl_FragColor = vec4(originColor.rgb, resultAlpha);
}
