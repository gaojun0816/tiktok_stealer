#ifdef GL_ES
precision lowp float;
#endif
varying highp vec2 textureCoordinate;
uniform sampler2D inputImageTexture0;
uniform sampler2D inputImageTexture1;
uniform highp float sharpen;
void main() {
    vec4 texel = texture2D(inputImageTexture0, textureCoordinate);
    vec3 blurredTexel = texture2D(inputImageTexture1, textureCoordinate).rgb;
    vec3 diff = texel.rgb - blurredTexel;
    // sharpen magnitude has a default value of 0.35 at input 0, and a maximum of 2.5 at input 1.0.
    float mag = mix(0.35, 2.5, sharpen);
    texel.rgb = clamp(texel.rgb + diff * mag, 0.0, 1.0);
    gl_FragColor = texel;
}
