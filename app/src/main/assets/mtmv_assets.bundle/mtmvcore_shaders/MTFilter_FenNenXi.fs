#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D uTexture0;//原片
uniform sampler2D uTexture1;//sucai6.jpg
uniform sampler2D uTexture2;//PSScreen50.png
uniform sampler2D uTexture3;//MTSoftLight.png
uniform sampler2D uTexture4;//MTMultiple50.png
uniform sampler2D uTexture5;//120data.png

varying vec2 vTexCoord;
uniform float uPercent;

vec4 PSBlendEx(vec4 blend, vec4 base,sampler2D mask)
{
    vec4 retn;
    retn.a = blend.a;
    vec2 arbr=vec2(base.r,blend.r);
    retn.r = texture2D( mask, arbr).r;
    arbr=vec2(base.g,blend.g);
    retn.g = texture2D( mask, arbr).r;
    arbr=vec2(base.b,blend.b);
    retn.b = texture2D( mask, arbr).r;
    return retn;
}
void main(void)
{
    vec4 oldData = texture2D(uTexture0,vTexCoord);
    vec4 srcData = oldData;
    vec4 oralData = PSBlendEx(oldData,oldData,uTexture2);
    vec4 temp1 = texture2D(uTexture1, vTexCoord);
    oralData = PSBlendEx(oralData,temp1,uTexture3);
    vec2 arbr=vec2(oralData.r,0.5);
    oralData.r = texture2D(uTexture5,arbr).r;
    arbr=vec2(oralData.g,0.5);
    oralData.g = texture2D(uTexture5,arbr).g;
    arbr=vec2(oralData.b,0.5);
    oralData.b = texture2D(uTexture5,arbr).b;
    
    oralData = PSBlendEx(oralData,oldData,uTexture4);
    oralData = mix(srcData,oralData, uPercent);
    gl_FragColor = oralData;
}
