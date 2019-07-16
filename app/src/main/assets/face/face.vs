attribute vec2 posCoord;
attribute vec2 texCoord;
uniform mat4 u_Matrix;

varying vec2 textureCoordinate;


void main()
{
    gl_Position =  u_Matrix * vec4( posCoord.x, posCoord.y, 0.0, 1.0);
    textureCoordinate = texCoord;
}
