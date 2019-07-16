#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
uniform sampler2D uTexture0;
uniform float uLeftMove;//距离左边剩余距离的百分比
uniform float u;//斜率
uniform float c;//斜线c值

void main()
{
    vec2 texCoord = vec2(vTexCoord.x - uLeftMove, vTexCoord.y);
    vec4 leftColor = vec4(0.0, 0.0, 0.0, 0.0);
    if(texCoord.x >= 0.0 && texCoord.x <= 1.0)
    {
        leftColor = texture2D(uTexture0,texCoord);
    }

    vec4 resultColor = vec4(0.0, 0.0, 0.0, 0.0);
    //求斜线 y = kx + c;
    float limitY = k * vTexCoord.x + c;
    if (k > 0,0) {
        if(vTexCoord.y >= limitY)
        {
            resultColor = leftColor;
        }
    } else {
        if(vTexCoord.y <= limitY)
        {
            resultColor = leftColor;
        }
    }
    
    
    gl_FragColor = resultColor;
}
