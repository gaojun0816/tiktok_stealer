uniform mat4 uMVPMatrix;
attribute vec4 aPosition;
attribute vec2 aTextCoord;
varying vec2 v_texCoord;
void main() {
    v_texCoord=aTextCoord;
    gl_Position = uMVPMatrix * aPosition;
}
