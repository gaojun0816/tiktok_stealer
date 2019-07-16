#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
uniform sampler2D uTexture0;
uniform vec2 uRemainx0;//remainPartX1 和 remainPartX2 是或的关系
uniform vec2 uRemainx1;
uniform vec2 uRemainy0;//remainPartY1 和 remainPartY2 是或的关系
uniform vec2 uRemainy1;
//uniform vec4 color;

void main()
{
    
    vec4 resultColor = vec4(0.0, 0.0, 0.0, 0.0);
    if ((vTexCoord.x >= uRemainx0.x && vTexCoord.x <= uRemainx0.y) || (vTexCoord.x >= uRemainx1.x && vTexCoord.x <= uRemainx1.y))
    {
        resultColor = texture2D(uTexture0,vTexCoord);
    }
//    else if ((vTexCoord.x == uRemainy0.x && vTexCoord.x == uRemainy0.y) || (vTexCoord.x == uRemainy1.x && vTexCoord.x == uRemainy1.y)) {
//        resultColor = vec4(1.0, 1.0, 1.0, 0.5);
//    }
    
    if ((vTexCoord.y >= uRemainy0.x && vTexCoord.y <= uRemainy0.y) || (vTexCoord.y >= uRemainy1.x && vTexCoord.y <= uRemainy1.y))
    {
        resultColor = texture2D(uTexture0,vTexCoord);
    }
//    else if (((vTexCoord.y - uRemainy0.x) < 0.1 && (vTexCoord.y - uRemainy0.y) < 0.1) || ((vTexCoord.y - uRemainy1.x) < 0.1 && (vTexCoord.y - uRemainy1.y) < 0.1)) {
//        resultColor = vec4(1.0, 1.0, 1.0, 0.5);
//    }
//    resultColor = vec4(color.rgb, resultColor.a);

    gl_FragColor = resultColor;
}

