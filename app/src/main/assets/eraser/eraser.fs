precision highp float;
varying vec2 texcoordOut;
varying vec2 v_texcoordInput;
uniform sampler2D srctexture;
uniform sampler2D masktexture;

uniform float opacity;


 void main(){
      vec4 maskColor = vec4(1.0, 0.0, 0.0, 1.0);
      vec4 textureColor = texture2D(masktexture,texcoordOut);

      gl_FragColor = maskColor * textureColor.r * opacity;

 }