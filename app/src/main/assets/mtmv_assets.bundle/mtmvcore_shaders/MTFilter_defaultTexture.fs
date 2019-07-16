#ifdef GL_ES
precision mediump float;
#else
#define highp
#endif
varying highp vec2 vTexCoord;                           
uniform sampler2D uTexture0;                             
uniform float uUseColor;                                
uniform vec3 uColor;                                    
uniform float uAlpha;                                   
void main(){                                            
    vec4 src = texture2D(uTexture0, vTexCoord);          
    if (uUseColor < 5.0) {                              
        gl_FragColor = vec4(src.rgb, src.a*uAlpha);     
    } else {                                            
        gl_FragColor = vec4(uColor, src.a*uAlpha);      
    }                                                   
}