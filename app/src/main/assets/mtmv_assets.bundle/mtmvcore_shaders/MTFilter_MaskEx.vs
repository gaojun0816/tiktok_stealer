attribute vec4 aPosition;
attribute vec2 aTexCoord0;
attribute vec2 aMaskCoord;
varying vec2 vTexCoord;
varying vec2 vMaskCoord;
void main() {
   gl_Position = aPosition;
   vTexCoord = aTexCoord0;
   vMaskCoord = aMaskCoord;
}
