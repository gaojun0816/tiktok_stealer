#ifdef GL_ES
precision mediump float;
#else
#define highp
#define mediump
#define lowp
#endif

varying vec2 vTexCoord;
uniform sampler2D uTexture0;

varying vec2 vLeftTextureCoordinate;
varying vec2 vRightTextureCoordinate;
varying vec2 vTopTextureCoordinate;
varying vec2 vBottomTextureCoordinate;

varying float vCenterMultiplier;
varying float vEdgeMultiplier;

void main()
{
    vec3 textureColor = texture2D(uTexture0, vTexCoord).rgb;
    vec3 leftTextureColor = texture2D(uTexture0, vLeftTextureCoordinate).rgb;
    vec3 rightTextureColor = texture2D(uTexture0, vRightTextureCoordinate).rgb;
    vec3 topTextureColor = texture2D(uTexture0, vTopTextureCoordinate).rgb;
    vec3 bottomTextureColor = texture2D(uTexture0, vBottomTextureCoordinate).rgb;
    
    gl_FragColor = vec4((textureColor * vCenterMultiplier - (leftTextureColor * vEdgeMultiplier + rightTextureColor * vEdgeMultiplier + topTextureColor * vEdgeMultiplier + bottomTextureColor * vEdgeMultiplier)), texture2D(uTexture0, vBottomTextureCoordinate).w);
}
