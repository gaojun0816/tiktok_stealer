varying highp vec2 textureCoordinate;
uniform sampler2D inputImageTexture0;
uniform sampler2D inputImageTexture1;
uniform highp float intensity;
void main() {
    lowp vec4 sharpImageColor = texture2D(inputImageTexture0, textureCoordinate);
    lowp vec3 blurredImageColor = texture2D(inputImageTexture1, textureCoordinate).rgb;
    gl_FragColor = vec4(sharpImageColor.rgb * intensity + blurredImageColor * (1.0 - intensity), sharpImageColor.a);
}
