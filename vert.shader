#version 430
uniform float offsetY;
uniform float offsetX;
uniform float sizeOffset;
out vec4 initialColor;

void main(void){
	if (gl_VertexID == 0){
		gl_Position = vec4(0.0+offsetX, 0.25+offsetY+sizeOffset, 0.0, 1.0);
		initialColor = vec4(1.0, 0.0, 0.0, 1.0);
	}
	else if (gl_VertexID == 1){
		gl_Position = vec4(-0.25+offsetX-sizeOffset, -0.25+offsetY-sizeOffset, 0.0, 1.0);
		initialColor = vec4(0.0, 1.0, 0.0, 1.0);
	}
	else{
		gl_Position = vec4(0.25+offsetX+sizeOffset, -0.25+offsetY-sizeOffset, 0.0, 1.0);
		initialColor = vec4(0.0, 0.0, 1.0, 1.0);
	}
	
}