precision mediump float;
uniform sampler2D inputImageTexture0;
uniform highp float texelWidthOffset;
uniform highp float texelHeightOffset;
varying vec2 textureCoordinate;
const float pi = 3.14159265;
const float numBlurPixelsPerSide = 4.0;
const float kernelSize = 2.0;
void main() {
    vec4 texel = texture2D(inputImageTexture0, textureCoordinate);
    vec4 inputTexel = texel;
    vec3 incrementalGaussian = vec3(0.5,0.5,0.5);
    
    vec4 avgValue = vec4(0.0);
    float coefficientSum = 0.0;
    vec2 blurVector = vec2(texelWidthOffset,texelHeightOffset);
    
    // center pixel
    avgValue += texel * incrementalGaussian.x;
    coefficientSum += incrementalGaussian.x;
    incrementalGaussian.xy *= incrementalGaussian.yz;
    
    // Go through the remaining 8 vertical samples (4 on each side of the center)
    for (float i = 1.0; i < kernelSize + 1.0; i++) {
        avgValue += texture2D(inputImageTexture0, textureCoordinate - i * blurVector) * incrementalGaussian.x;
        avgValue += texture2D(inputImageTexture0, textureCoordinate + i * blurVector) * incrementalGaussian.x;
        coefficientSum += 2.0 * incrementalGaussian.x;
        incrementalGaussian.xy *= incrementalGaussian.yz;
    }
    
    texel = avgValue / coefficientSum;
    gl_FragColor = texel;
}

