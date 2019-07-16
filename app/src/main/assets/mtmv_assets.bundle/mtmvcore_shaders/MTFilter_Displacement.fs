#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
uniform sampler2D uTexture0;
uniform vec2 uOutputSize;
uniform float uMove[48];

void main()
{
    int nIndex = int(floor(vTexCoord.y * uOutputSize.y));
    nIndex = nIndex - (nIndex/48)*48;
    
    vec2 tempCoord = vTexCoord;
    tempCoord.x = tempCoord.x + uMove[nIndex];
    
    vec4 resColor = texture2D(uTexture0,tempCoord);
    
    if (tempCoord.x < 0.0 || tempCoord.x > 1.0)
    {
        resColor = vec4(0.0);
    }
    
    gl_FragColor = resColor;
}
