attribute vec4 aPosition;
attribute vec2 aTexCoord0;
varying vec2 vTexCoord;
void main()
{
    gl_Position = aPosition;
    vTexCoord = aTexCoord0;
}
