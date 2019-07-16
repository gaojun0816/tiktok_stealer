#ifdef GL_ES
precision highp float;
#endif
varying vec2 vTexCoord;
//uniform sampler2D mt_tempData1;//美颜
uniform sampler2D uTexture0;//原图
uniform sampler2D uTexture1;//高斯图
uniform vec2 uCenter;
uniform vec2 uEllipse;
uniform vec3 uLineValues;
uniform vec3 uLineValues2;
uniform float uInner;
uniform float uOuter;
#define Blend_pLitf(base,blend)   (min(1.0,max(0.0,((base)+2.0*(blend)-1.0))))
#define Blend_hLitf(base,blend) ((blend)<=0.5?(blend)*(base)/0.5:1.0-(1.0-(blend))*(1.0-(base))/0.5)
void main(void)
{
	vec4 oral = texture2D(uTexture0, vTexCoord);
	vec4 gauss = texture2D(uTexture1, vTexCoord);
    
    // beauty
//	vec4 curve = oral;
//	curve.r = texture2D(mt_tempData1,vec2(curve.r,0.5)).b;
//	curve.g = texture2D(mt_tempData1,vec2(curve.g,0.5)).b;
//	curve.b = texture2D(mt_tempData1,vec2(curve.b,0.5)).b;
//	float G = oral.g;
//	float G1 = 1.0 - gauss.g;
//	G1 = Blend_pLitf(G, G1);
//	float G2=mix(G,G1,0.5);
//	G2=Blend_hLitf(G2, G2);
//	G2=Blend_hLitf(G2, G2);
//	G2=Blend_hLitf(G2, G2);
//	oral=mix(curve,oral,G2);
//
//	gauss.r = texture2D(mt_tempData1,vec2(gauss.r,0.5)).b;
//	gauss.g = texture2D(mt_tempData1,vec2(gauss.g,0.5)).b;
//	gauss.b = texture2D(mt_tempData1,vec2(gauss.b,0.5)).b;


	float fx = vTexCoord.x - uCenter.x;
	float fy = vTexCoord.y - uCenter.y;
	float dist = sqrt(fx*fx*uEllipse.x + fy*fy*uEllipse.y);

	vec4 v1=vec4(0, 0, 0, 0);
	vec4 result = oral;
	if (dist < uInner)
	{
		result = oral;
	}
	else
	{
		vec3 point = vec3(vTexCoord.x,vTexCoord.y,1.0);
		float value1 = dot(uLineValues, point);
		float value2 = dot(uLineValues2, point);
		if (value1 >= 0.0 && value2 >= 0.0)
		{
			result = oral;
		}
		else
		{
			float lineAlpha = max(-value1 / 0.15, -value2 / 0.15);
			float alpha = (dist - uInner)/uOuter;
			alpha = min(lineAlpha, alpha);
			alpha = min(alpha,1.0);
			alpha = 0.15 + (0.85) * (1.0 - alpha);
			result = mix(gauss,oral,alpha);
		}
	}
	gl_FragColor = result;
}
