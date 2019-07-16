#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
uniform sampler2D uTexture0;
uniform float uTopMove;//距离右边剩余距离的百分比
uniform float k;//斜率
uniform float c;//斜线c值

void main()
{
    vec2 texCoord = vec2(vTexCoord.x, vTexCoord.y + uTopMove);
    vec4 bottomColor = vec4(0.0, 0.0, 0.0, 0.0);
    if(texCoord.x >= 0.0 && texCoord.x <= 1.0)
    {
        bottomColor = texture2D(uTexture0,texCoord);
    }
    
    vec4 resultColor = vec4(0.0, 0.0, 0.0, 0.0);
    //求斜线 y = kx + c;
    float limitY = k * vTexCoord.x + c;
    if(vTexCoord.y >= limitY)
    {
        resultColor = bottomColor;
    }
    
    gl_FragColor = resultColor;
}

