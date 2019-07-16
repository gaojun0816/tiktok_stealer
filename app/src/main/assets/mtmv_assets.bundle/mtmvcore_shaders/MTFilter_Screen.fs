precision highp float;    // specify float precision.

varying highp vec2 vTexCoord0;
varying highp vec2 vTexCoord1;
uniform sampler2D uTexture0;
uniform sampler2D uTexture1;
uniform sampler2D uTexture2;

uniform lowp float uPercent;

vec4 mixmode(sampler2D sample,vec4 bottomcolor,vec4 topcolor)
{
vec4 res=vec4(0.0,0.0,0.0,topcolor.a);
vec2 lookup =vec2(topcolor.r, bottomcolor.r);
res.r = texture2D(sample, lookup).r;
lookup.x = topcolor.g;
lookup.y =bottomcolor.g;
res.g = texture2D(sample, lookup).g;
lookup.x = topcolor.b;
lookup.y = bottomcolor.b;
res.b= texture2D(sample, lookup).b;
return res;
}

void main()
{
   vec4 top = texture2D(uTexture0, vTexCoord0);
   if (vTexCoord1.x <= 1.0 && vTexCoord1.y <= 1.0 && vTexCoord1.x >= 0.0 && vTexCoord1.y >= 0.0) {
       vec4 bottom = texture2D(uTexture1, vTexCoord1);
       float percent = uPercent * bottom.a;
       bottom *= percent;
       gl_FragColor = mixmode(uTexture2,bottom,top);
   } else {
       gl_FragColor = top;
   }
}
