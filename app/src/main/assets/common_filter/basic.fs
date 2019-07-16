precision highp float;

varying vec2 texcoordOut;
uniform sampler2D srctexture;         //原图
uniform sampler2D mt_tempData1;

void main()
{
   vec4 srcColor = texture2D(srctexture, texcoordOut);
   vec4 color = texture2D(mt_tempData1, texcoordOut);

   gl_FragColor = mix(srcColor, color, color.a);
}