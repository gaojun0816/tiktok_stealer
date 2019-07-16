precision highp float;
varying vec2 v_texCoord;
uniform sampler2D inputTexture;
uniform sampler2D mt_mask_0;
uniform vec3 color;
uniform float size;
uniform float textureHeight;
uniform float textureWidth;
vec4 imgWithDilateProcess(float size)
{
    float radio = textureHeight / textureWidth;
    float radioX = 1.0;
    float radioY = 1.0;
    if (radio > 1.0) {
        radioX = radio;
    } else {
        radioY = 1.0 / radio;
    }
    vec4 maxValue = vec4(0.0);
    int coreSize = 3;//卷积核的尺寸  3x3
    int halfCoreSize = coreSize / 2;
    float texelOffset = 1.0 / 100.0 * size;//纹理坐标偏移量
    for(int y=0; y < coreSize; y++)
    {
        for(int x=0; x < coreSize; x++)
        {
            //计算卷积核覆盖区域像素点的最大值
            vec4 color = texture2D(mt_mask_0, v_texCoord + vec2( float(-halfCoreSize+x) * texelOffset * radioX, float(-halfCoreSize+y) * texelOffset * radioY));
            maxValue = max(maxValue, color);
        }
    }
    return maxValue;
}

void main(void)
{
    vec4 maskColor = texture2D(mt_mask_0, v_texCoord);
    vec4 scaleMaskColor = imgWithDilateProcess(size/16.0);
    vec4 srcColor = texture2D(inputTexture, v_texCoord);
    vec4 strokeColor = vec4(color,1.0);
    vec4 scaleStrokeColor = mix(srcColor, strokeColor, scaleMaskColor.r);
    gl_FragColor = mix(scaleStrokeColor, srcColor, maskColor.r);
}
