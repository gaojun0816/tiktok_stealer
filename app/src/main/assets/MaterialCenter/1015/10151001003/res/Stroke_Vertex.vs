uniform mat4 uMVPMatrix;
attribute vec4 aPosition;
attribute vec2 aTextCoord;
varying vec2 v_texCoord;
varying vec2 v_maskTexCoord;
void main() {
    gl_Position = uMVPMatrix * aPosition;
    v_texCoord = gl_Position.xy * 0.5 + 0.5;
	v_maskTexCoord = aTextCoord;
}
