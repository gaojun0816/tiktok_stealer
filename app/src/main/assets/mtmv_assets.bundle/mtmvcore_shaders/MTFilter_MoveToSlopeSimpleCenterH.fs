#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
uniform sampler2D uTexture0;
uniform float c;//斜线c值


void main()
{
    vec4 resultColor = vec4(0.0, 0.0, 0.0, 0.0);

    float offset = c/2.0;
    if(vTexCoord.x >= 0.5-offset && vTexCoord.x <= 0.5+offset) {
        resultColor = texture2D(uTexture0, vTexCoord);
    } else {
    }
    
    gl_FragColor = resultColor;
}
