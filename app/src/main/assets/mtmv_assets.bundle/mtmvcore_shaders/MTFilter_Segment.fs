#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
uniform sampler2D uTexture0;

uniform int uRow; // 行
uniform int uCol; // 列

void main()
{
    // 获取每个画面的占比
    float rowValue = 1.0 / float(uRow);
    float colValue = 1.0 / float(uCol);
    
//    float xPosition = floor(vTexCoord.x / colValue);
//    float yPosition = floor(vTexCoord.y / rowValue);
    float xPosition = floor(vTexCoord.x * float(uCol));
    float yPosition = floor(vTexCoord.y * float(uRow));
    
    vec2 tempTextureCoordinate = abs(vTexCoord - vec2(colValue * xPosition,rowValue * yPosition)) * vec2(uCol,uRow);
    
    gl_FragColor = texture2D(uTexture0, tempTextureCoordinate);
}
