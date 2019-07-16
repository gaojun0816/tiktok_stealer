#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
uniform sampler2D uTexture0;
uniform float k;//斜率
uniform float c;//斜线c值
uniform float uDisplayTopOrBottom;//0代表显示垂直线的左部分，1.0代表显示垂直线的右部分

void main()
{
    vec4 resultColor = vec4(0.0, 0.0, 0.0, 0.0);

    float limitX = c;
    if(vTexCoord.x >= limitX && uDisplayTopOrBottom == 1.0) {
        resultColor = texture2D(uTexture0, vTexCoord);
    } else if (vTexCoord.x <= limitX && uDisplayTopOrBottom == 0.0) {
        resultColor = texture2D(uTexture0, vTexCoord);
    }
    
    gl_FragColor = resultColor;
}
