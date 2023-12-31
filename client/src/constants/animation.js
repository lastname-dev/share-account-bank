import { keyframes } from "styled-components";

export const slideDown = keyframes` 
  0%{
      transform: translate(0, -100%);
      opacity: 100;
  }
  100%{
      transform: translate(0, 0);
      opacity: 100%;
  }
`;

export const slideUp = keyframes` 
  0%{
      transform: translate(0, 100%);
      opacity: 0;
  }
  70%{
      opacity: 50%;
  }
  100%{
      transform: translate(0, 0);
      opacity: 100%;
  }
`;
export const slideRight = keyframes` 
  0%{
      transform: translate(-110%,0 );
      border-radius: 0;
  }
  100%{
      transform: translate(0, 0);
      border-radius: 20px;
  }
`;

export const vibration = keyframes` 
  0%{
    transform: rotate(2deg);
  }
  50%{
    transform: rotate(-2deg);
  }
  100%{
    transform: rotate(2deg);
  }
`;

export const float = keyframes`
	0% {
		transform: translatey(0px);
	}
	50% {
		transform: translatey(-20px);
	}
	100% {
		transform: translatey(0px);
	}
`;
