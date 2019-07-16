attribute vec2 posCoord;
attribute vec2 texCoord;
varying vec2 texcoordOut;
varying vec2 v_texcoordInput;
uniform mat4 u_Matrix;

void main()
{
	texcoordOut = texCoord;
    v_texcoordInput = (posCoord.xy + 1.0) / 2.0;
	gl_Position = vec4(posCoord.x, posCoord.y, 0.0, 1.0);
}