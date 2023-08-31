import {
  ButtonWrapper,
  IntroPageWrapper,
  LoginButton,
  LogoContainer,
  LogoImage,
  SigninButton,
} from "./IntroPage.style";

const IntroPage = () => {
  return (
    <IntroPageWrapper>
      <LogoContainer>
        <LogoImage src={process.env.PUBLIC_URL + "/image/logo.png"} alt="logo" />
      </LogoContainer>
      <ButtonWrapper>
        <LoginButton>로그인</LoginButton>
        <SigninButton>회원가입</SigninButton>
      </ButtonWrapper>
    </IntroPageWrapper>
  );
};
export default IntroPage;
