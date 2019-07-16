#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
uniform sampler2D uTexture0;
uniform sampler2D uTexture1;
uniform float uPercent;//缩放scale

void main()
{

    vec4 texColor = texture2D(uTexture0, vTexCoord);
    vec2 texCoord = vec2(0.0, 0.0);
    if(uPercent != 0.0) {
       texCoord = vec2((vTexCoord.x - 0.5) / uPercent + 0.5, vTexCoord.y);
    }

    vec4 maskColor = vec4(0.0, 0.0, 0.0, 0.0);
    if(texCoord.x >= 0.0 && texCoord.x <= 1.0)
    {
        maskColor = texture2D(uTexture1,texCoord);
    }
    
    gl_FragColor = vec4(texColor.rgb, min(maskColor.a, texColor.a));
}
