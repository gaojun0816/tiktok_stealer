precision highp float;
varying vec2 v_texCoord;
varying vec2 v_maskTexCoord;
uniform sampler2D inputTexture;
uniform sampler2D mt_mask_0;
uniform vec3 color;
uniform int isBlend;
void main(void)
{
    vec4 result = vec4(0.0, 0.0, 0.0, 1.0);
    if (isBlend == 0) {
        vec4 pen = texture2D(mt_mask_0, v_maskTexCoord);
        result = vec4(color, pen.r);
    } else if (isBlend == 1) {
        vec4 inputColor = texture2D(inputTexture, v_texCoord);
        vec4 pen = texture2D(mt_mask_0, v_texCoord);
        result = mix(inputColor, vec4(color, 1.0), pen.r);
    }
    gl_FragColor = result;
}

