#ifdef GL_ES
precision mediump float;
#endif
varying vec2 vTexCoord;
varying vec2 vMaskCoord;
uniform sampler2D uTexture0;
uniform sampler2D uTexture1;
void main() {
   vec4 src = texture2D(uTexture0,vTexCoord);
   vec4 mask = texture2D(uTexture1,vMaskCoord);
   if(src.a >= 0.5 && mask.a >= 0.5)
   {
       gl_FragColor = mask * 0.8 + src * 0.2;
   }
   else
   {
       gl_FragColor = src;
   }
}
