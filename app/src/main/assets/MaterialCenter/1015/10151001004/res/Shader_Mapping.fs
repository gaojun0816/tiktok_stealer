precision highp float;
varying vec2 v_texCoord;
uniform sampler2D inputTexture;
uniform sampler2D mt_mask_0;
uniform vec3 color;
uniform float size;

void main(void)
{
    float lineWidth = (0.07 / 20.0) * size + 0.01;
    vec4 leftMaskColor = texture2D(mt_mask_0, v_texCoord + vec2(lineWidth,0.0));
    vec4 maskColor = texture2D(mt_mask_0, v_texCoord + vec2(0.01,0.0));
    vec4 srcColor = texture2D(inputTexture, v_texCoord);
    leftMaskColor = step(0.5, leftMaskColor);
    maskColor = step(0.5, maskColor);
    vec4 strokeColor = vec4(color,1.0);
    vec4 leftStrokeColor = mix(srcColor, strokeColor, leftMaskColor.r);
    gl_FragColor =   mix(leftStrokeColor, srcColor, maskColor.r);
}
