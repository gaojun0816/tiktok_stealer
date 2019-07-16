attribute vec4 aPosition;
attribute vec2 aTexCoord0;
attribute vec2 aTexCoord1;
varying vec2 vTexCoord0;
varying vec2 vTexCoord1;
void main() {
gl_Position = aPosition;
vTexCoord0 = aTexCoord0;
vTexCoord1 = aTexCoord1;
}
