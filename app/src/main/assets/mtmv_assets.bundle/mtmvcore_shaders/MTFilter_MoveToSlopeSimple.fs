#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
uniform sampler2D uTexture0;
uniform float k;//斜率
uniform float c;//斜线c值
uniform float uDisplayTopOrBottom;//0代表显示斜线的上部分，1.0代表显示斜线的下部分

void main()
{
    vec4 resultColor = vec4(0.0, 0.0, 0.0, 0.0);
    //求斜线 y = kx + c;
    float limitY = k * vTexCoord.x + c;
    if(vTexCoord.y >= limitY && uDisplayTopOrBottom == 1.0) {
        resultColor = texture2D(uTexture0, vTexCoord);
    } else if (vTexCoord.y <= limitY && uDisplayTopOrBottom == 0.0) {
        resultColor = texture2D(uTexture0, vTexCoord);
    }
    gl_FragColor = resultColor;
}
