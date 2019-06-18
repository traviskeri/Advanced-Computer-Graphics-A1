#version 430
uniform float gradient;
in vec4 initialColor;
out vec4 finalColor;
void main(void){

	if(gradient == 0){
		finalColor = vec4(1.0, 0.0, 0.0, 1.0);
	}
	else{
		finalColor = initialColor;
	}
}