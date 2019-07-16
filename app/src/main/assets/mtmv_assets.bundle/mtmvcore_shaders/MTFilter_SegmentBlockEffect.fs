#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
uniform sampler2D uTexture0;

uniform int uRow; // 行
uniform int uCol; // 列

uniform int uEffectType;  // 需要改变块的效果 1.黑白 2.隐藏
uniform int uBlockSize;   // 需要改变块的个数
const int MAX_BLOCK_SIZE = 8;
uniform int uBlockIndices[MAX_BLOCK_SIZE]; // 需要改变块的下标

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
    
    vec4 color = texture2D(uTexture0, tempTextureCoordinate);
    
    float blockIndex = yPosition * float(uCol) + xPosition;
    int isChanged = 0;
    for (int index = 0; index < MAX_BLOCK_SIZE; ++index) {
        if (index >= uBlockSize) {
            break;
        }
        if (blockIndex == float(uBlockIndices[index])) {
            if (uEffectType == 1) {
                vec3 lum = vec3(0.299, 0.587, 0.114);
                float bw = dot(color.rgb,lum);
                gl_FragColor = vec4(bw, bw, bw, color.a);
            } else if (uEffectType == 2) {
                gl_FragColor.a = 0.0;
            }
            isChanged = 1;
            break;
        }
    }
    if (isChanged != 1) {
        gl_FragColor = color;
    }
}
